package com.hitanshudhawan.popcorn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hitanshudhawan.popcorn.R;
import com.hitanshudhawan.popcorn.movies.CastDetailActivity;
import com.hitanshudhawan.popcorn.network.movies.CastBrief;

import java.util.List;

/**
 * Created by hitanshu on 2/8/17.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context mContext;
    List<CastBrief> mCasts;

    public CastAdapter(Context mContext, List<CastBrief> mCasts) {
        this.mContext = mContext;
        this.mCasts = mCasts;
    }

    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CastViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cast_item,parent,false));
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, final int position) {

        holder.castImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CastDetailActivity.class);
                intent.putExtra("cast_id",mCasts.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        Glide.with(mContext.getApplicationContext()).load("https://image.tmdb.org/t/p/w185/" + mCasts.get(position).getProfilePath())
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.castImageView);

        holder.nameTextView.setText(mCasts.get(position).getName());
        holder.characterTextView.setText(mCasts.get(position).getCharacter());

    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }


    public class CastViewHolder extends RecyclerView.ViewHolder {

        public ImageView castImageView;
        public TextView nameTextView;
        public TextView characterTextView;

        public CastViewHolder(View itemView) {
            super(itemView);
            castImageView = (ImageView) itemView.findViewById(R.id.image_view_cast);
            nameTextView = (TextView) itemView.findViewById(R.id.text_view_cast_name);
            characterTextView = (TextView) itemView.findViewById(R.id.text_view_cast_as);
        }
    }

}