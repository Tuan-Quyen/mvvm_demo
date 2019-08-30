package com.example.mvvm_demo.other;

import com.example.mvvm_demo.models.AudioModel;

import java.util.ArrayList;
import java.util.List;

public class GenerateDataUtils {
    public static List<AudioModel> genListMusic() {
        List<AudioModel> list = new ArrayList<>();
        list.add(new AudioModel("Sóng Gió", "Jack-K-ICM", "https://vnso-zn-10-tf-mp3-s1-zmp3.zadn.vn/b800471a6d5d8403dd4c/1145597756150434004?authen=exp=1567159066~acl=/b800471a6d5d8403dd4c/*~hmac=d1320d6b0fc13a172753fd6cab4e68b9"));
        list.add(new AudioModel("Anh Đánh Rơi Người Yêu Này! (Thật Tuyệt Vời Khi Ở Bên Em OST)", "Andiez, AMEE", "https://mp3-s1-zmp3.zadn.vn/6ee044056f42861cdf53/5630113708470082055?authen=exp=1567224865~acl=/6ee044056f42861cdf53/*~hmac=4276b90d60aef1a7705347f911d05120"));
        list.add(new AudioModel("Anh Nhà Ở Đâu Thế?", "AMEE, B Ray", "https://mp3-s1-zmp3.zadn.vn/80da2ae704a0edfeb4b1/1229954897322357257?authen=exp=1567225488~acl=/80da2ae704a0edfeb4b1/*~hmac=8562ead8b0aa54e8477141a933881e32"));
        list.add(new AudioModel("Tướng Quân (Remix)", "Nhật Phong, DinhLong", "https://mp3-s1-zmp3.zadn.vn/48d052c9798e90d0c99f/7989622210197685984?authen=exp=1567224908~acl=/48d052c9798e90d0c99f/*~hmac=adf88bce1a54b8b4ddfbe2e32fbd4ca7"));
        return list;
    }
}
