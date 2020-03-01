package com.example.dailyshottest.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {
    private static final String SERVER_URL = "https://dailyshot.co/v1/";

    private static RetrofitCreator creatorInstance;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private Gson gson;

    private RetrofitCreator() {
        okHttpClient = new OkHttpClient.Builder()
                .build();
        gson = new GsonBuilder()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    public static synchronized RetrofitCreator getInstance() {
        if(creatorInstance == null) {
            creatorInstance = new RetrofitCreator();
        }
        return creatorInstance;
    }

    public <S> S createApiService(Class<S> serviceInterface) {
        return retrofit.create(serviceInterface);
    }

}
