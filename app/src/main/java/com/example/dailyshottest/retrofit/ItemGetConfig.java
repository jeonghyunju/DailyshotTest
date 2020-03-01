package com.example.dailyshottest.retrofit;

import com.example.dailyshottest.data.ItemVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import androidx.databinding.ObservableArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ItemGetConfig {

    /* 제휴점 불러오기 API */
    @Headers({
            "Authorization: Token 4734badb3b008fbe97e535e68de38db4759bc3e7",
            "Content-Type: application/json"
    })
    @GET("bar_list")
    Call<ObservableArrayList<ItemVO>> getListApi(@Query("page") int page);


    /* 제휴점 검색하기 API */
    @Headers({
            "Authorization: Token 4734badb3b008fbe97e535e68de38db4759bc3e7",
            "Content-Type: application/json"
    })
    @GET("bar_search")
    Call<JsonObject> getSearchListApi(@Query("q") String searchStr);

}
