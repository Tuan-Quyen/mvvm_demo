package com.example.mvvm_demo.fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.mvvm_demo.R;
import com.example.mvvm_demo.base.BaseFragment;
import com.example.mvvm_demo.interfaces.ChooseImageListener;
import com.example.mvvm_demo.other.Constant;
import com.example.mvvm_demo.other.KeyboardUtils;
import com.example.mvvm_demo.other.SelectImageDialog;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends BaseFragment implements ChooseImageListener {
    @BindView(R.id.fragMain_ivReview)
    ImageView ivReview;
    @BindView(R.id.fragMain_etDataReview)
    EditText etReview;
    private Uri image_uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        KeyboardUtils.setupUI(view, getActivity());
        return view;
    }

    @OnClick({R.id.fragMain_btnChooseImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragMain_btnChooseImage:
                SelectImageDialog selectImageDialog = new SelectImageDialog(Objects.requireNonNull(getContext()), this);
                selectImageDialog.show();
                break;
        }
    }

    @Override
    public void onClickOpenCamera() {
        requestCameraGalleryPermissions();
    }

    @Override
    public void onClickChooseImage() {
        requestGalleryPermissions();
    }

    private void openCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Speech text");
        image_uri = Objects.requireNonNull(getActivity()).getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, Constant.OPEN_CAMERA);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Constant.OPEN_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.OPEN_CAMERA)
                CropImage.activity(image_uri).setGuidelines(CropImageView.Guidelines.ON).start(Objects.requireNonNull(getActivity()));
            if (requestCode == Constant.OPEN_GALLERY)
                CropImage.activity(data.getData()).setGuidelines(CropImageView.Guidelines.ON).start(Objects.requireNonNull(getActivity()));
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //set image uri
                ivReview.setImageURI(result.getUri());
                //get drawable bitmap to text recognition
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ivReview.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                TextRecognizer recognizer = new TextRecognizer.Builder(getContext()).build();
                if (!recognizer.isOperational()) {
                    //text recognizer error
                    Toast.makeText(getContext(), "Speech text has error!", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sBuilder = new StringBuilder();
                    //get text until frame don't have any text
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock textBlock = items.valueAt(i);
                        sBuilder.append(textBlock.getValue());
                        sBuilder.append("\n");
                    }
                    //set text to edit text
                    etReview.setText(sBuilder.toString());
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestCameraGalleryPermissions() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), permissions, Constant.REQ_CODE_CAMERA_GALLERY);
        } else {
            openCamera();
        }
    }

    private void requestGalleryPermissions() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), permissions, Constant.REQ_CODE_GALLERY);
        } else {
            openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        requestResult(requestCode, grantResults);
    }

    private void requestResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case Constant.REQ_CODE_CAMERA_GALLERY: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                break;
            }
            case Constant.REQ_CODE_GALLERY:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                break;
        }
    }

}
