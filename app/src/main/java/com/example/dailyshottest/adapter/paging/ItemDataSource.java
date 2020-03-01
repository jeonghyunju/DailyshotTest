package com.example.dailyshottest.adapter.paging;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dailyshottest.R;
import com.example.dailyshottest.data.ItemVO;
import com.example.dailyshottest.retrofit.ItemGetConfig;
import com.example.dailyshottest.retrofit.RetrofitCreator;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, ItemVO> {
    private static final String TAG = "ItemDataSource";
    public static int PAGE_NUM = 0;
    public static int PAGE_SIZE = 10;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ItemVO> callback) {
        RetrofitCreator.getInstance()
                .createApiService(ItemGetConfig.class)
                .getListApi(PAGE_NUM)
                .enqueue(new Callback<ObservableArrayList<ItemVO>>() {
                    @Override
                    public void onResponse(Call<ObservableArrayList<ItemVO>> call, Response<ObservableArrayList<ItemVO>> response) {
                        if(response.body() != null) {
                            callback.onResult(response.body(), null, PAGE_NUM + 1);
                            PAGE_NUM ++;
                            PAGE_SIZE += 10;
                        }
                    }

                    @Override
                    public void onFailure(Call<ObservableArrayList<ItemVO>> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ItemVO> callback) {
        RetrofitCreator.getInstance()
                .createApiService(ItemGetConfig.class)
                .getListApi(PAGE_NUM)
                .enqueue(new Callback<ObservableArrayList<ItemVO>>() {
                    @Override
                    public void onResponse(Call<ObservableArrayList<ItemVO>> call, Response<ObservableArrayList<ItemVO>> response) {
                        Integer beforeKey = (params.key > 0) ? params.key - 1 : null;
                        if(response.body() != null) {
                            callback.onResult(response.body(), beforeKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<ObservableArrayList<ItemVO>> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ItemVO> callback) {
        RetrofitCreator.getInstance()
                .createApiService(ItemGetConfig.class)
                .getListApi(PAGE_NUM)
                .enqueue(new Callback<ObservableArrayList<ItemVO>>() {
                    @Override
                    public void onResponse(Call<ObservableArrayList<ItemVO>> call, Response<ObservableArrayList<ItemVO>> response) {
                        if(response.body() != null) {
                            Integer afterKey = (PAGE_SIZE < 30) ? params.key + 1 : null;
                            callback.onResult(response.body(), afterKey);
                        }
                        PAGE_NUM ++;
                        PAGE_SIZE += 10;
                    }

                    @Override
                    public void onFailure(Call<ObservableArrayList<ItemVO>> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });

    }

}
