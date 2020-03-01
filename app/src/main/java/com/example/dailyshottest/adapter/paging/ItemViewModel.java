package com.example.dailyshottest.adapter.paging;

import com.example.dailyshottest.data.ItemVO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class ItemViewModel extends ViewModel {
    public LiveData<PagedList<ItemVO>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, ItemVO>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        PagedList.Config pagedListConf =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE)
                .build();

        itemPagedList = (new LivePagedListBuilder<>(itemDataSourceFactory, pagedListConf))
                .build();
    }
}
