package com.example.dailyshottest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.dailyshottest.adapter.RecyclerAdapter;
import com.example.dailyshottest.adapter.paging.ItemViewModel;
import com.example.dailyshottest.data.ItemVO;
import com.example.dailyshottest.databinding.ActivityMainBinding;
import com.example.dailyshottest.retrofit.ItemGetConfig;
import com.example.dailyshottest.retrofit.RetrofitCreator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private static final String TAG = "MainActivity";

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ItemViewModel itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<ItemVO>>() {
            @Override
            public void onChanged(PagedList<ItemVO> itemVOS) {
                recyclerAdapter.submitList(itemVOS);
            }
        });

        mainBinding.recyclerView.setAdapter(recyclerAdapter);

        mainBinding.searchET.addTextChangedListener(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.e(TAG, editable.toString());

        RetrofitCreator.getInstance()
                .createApiService(ItemGetConfig.class)
                .getSearchListApi(editable.toString())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
    }
}
