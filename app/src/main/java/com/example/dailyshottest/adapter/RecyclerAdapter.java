package com.example.dailyshottest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dailyshottest.ItemDetailActivity;
import com.example.dailyshottest.R;
import com.example.dailyshottest.data.ItemVO;
import com.example.dailyshottest.databinding.RecyclerItemBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends PagedListAdapter<ItemVO, RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";
    private Context context;

    public RecyclerAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemVO itemVO = getItem(position);
        holder.itemBinding.setDataModel(itemVO);

        if(itemVO != null) {
            Glide.with(context)
                    .load(itemVO.getProcessedImage())
                    .into(holder.itemBinding.itemImg);
        }

        if(itemVO.isNew()) {
            holder.itemBinding.newItem.setVisibility(View.VISIBLE);
        }else {
            holder.itemBinding.newItem.setVisibility(View.INVISIBLE);
        }

        if(position + 1 == 30) {
            Toast.makeText(context, context.getString(R.string.listEnd), Toast.LENGTH_SHORT).show();
        }

        holder.itemBinding.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ItemDetailActivity.class);
                intent.putExtra("item", itemVO);
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

    }

    private static DiffUtil.ItemCallback<ItemVO> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ItemVO>() {
                @Override
                public boolean areItemsTheSame(@NonNull ItemVO oldItem, @NonNull ItemVO newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull ItemVO oldItem, @NonNull ItemVO newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
