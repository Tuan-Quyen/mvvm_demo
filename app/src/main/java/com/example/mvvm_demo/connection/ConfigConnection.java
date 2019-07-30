package com.example.mvvm_demo.connection;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigConnection {
    private static String BASE_URL = "https://androidwebsv.000webhostapp.com/hope/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                    client(client).
                    build();
        }
        return retrofit;
    }
}
