package com.example.dailyshottest.adapter.paging;

import com.example.dailyshottest.data.ItemVO;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends DataSource.Factory {
    private static final String TAG = "ItemDataSourceFactory";

    private MutableLiveData<PageKeyedDataSource<Integer, ItemVO>> itemLiveDataSource = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, ItemVO>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
