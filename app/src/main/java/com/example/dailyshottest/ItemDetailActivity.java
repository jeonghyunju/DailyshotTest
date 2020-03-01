package com.example.dailyshottest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.dailyshottest.adapter.ImgPagerAdapter;
import com.example.dailyshottest.data.ItemVO;
import com.example.dailyshottest.databinding.ActivityItemDetailBinding;

import java.util.ArrayList;

public class ItemDetailActivity extends AppCompatActivity {
    private static final String TAG = "ItemDetailActivity";

    private Intent intent;
    private ItemVO itemVO;
    private ActivityItemDetailBinding itemDetailBinding;
    private ClickEvent clickEvent = new ClickEvent();
    private ArrayList<String> images = new ArrayList<>();
    private ImgPagerAdapter imgPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        itemDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail);
        itemDetailBinding.setClickEvent(clickEvent);

        if(intent == null) {
            intent = getIntent();
        }

        itemVO = (ItemVO)intent.getSerializableExtra("item");
        itemDetailBinding.itemDetailTitle.setText(itemVO.getName());
        itemDetailBinding.itemDetailDescript.setText(itemVO.getDescription());
        setDetailImg(itemVO.getProcessedImage());


        if(imgPagerAdapter == null) {
            imgPagerAdapter = new ImgPagerAdapter(this, images);
            itemDetailBinding.detailImg.setAdapter(imgPagerAdapter);
        }

        itemDetailBinding.imgSlideCount.setText((itemDetailBinding.detailImg.getCurrentItem() + 1) + " / " + images.size());
        itemDetailBinding.detailImg.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                itemDetailBinding.imgSlideCount.setText((position + 1) + " / " + images.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ClickEvent {
        public void goBack(View view) {
            finish();
        }
    }

    private void setDetailImg(String imgUrl) {
        for(int i=0 ; i<3 ; i++) {
            images.add(imgUrl);
        }
    }
}
