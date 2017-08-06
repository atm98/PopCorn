package com.hitanshudhawan.popcorn.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hitanshudhawan.popcorn.R;
import com.hitanshudhawan.popcorn.activities.MovieDetailActivity;
import com.hitanshudhawan.popcorn.network.movies.MovieCastOfPerson;

import java.util.List;

/**
 * Created by hitanshu on 6/8/17.
 */

public class MovieCastsOfPersonAdapter extends RecyclerView.Adapter<MovieCastsOfPersonAdapter.MoviesViewHolder> {

    private Context mContext;
    private List<MovieCastOfPerson> mMovieCasts;

    public MovieCastsOfPersonAdapter(Context context, List<MovieCastOfPerson> movies) {
        mContext = context;
        mMovieCasts = movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movies_of_cast,parent,false));
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, final int position) {

        holder.moviePosterImageView.getLayoutParams().width = (int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.31);
        holder.moviePosterImageView.getLayoutParams().height = (int) ((mContext.getResources().getDisplayMetrics().widthPixels * 0.31)/0.66);

        holder.movieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("movie_id",mMovieCasts.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        Glide.with(mContext.getApplicationContext()).load("https://image.tmdb.org/t/p/w1000/" + mMovieCasts.get(position).getPosterPath())
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.moviePosterImageView);
        holder.movieTitleTextView.setText(mMovieCasts.get(position).getTitle());
        if(mMovieCasts.get(position).getCharacter() != null && !mMovieCasts.get(position).getCharacter().trim().isEmpty())
            holder.castCharacterTextView.setText("as " + mMovieCasts.get(position).getCharacter());
//        holder.movieFavImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
//                //TODO
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mMovieCasts.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        public CardView movieCard;
        public ImageView moviePosterImageView;
        public TextView movieTitleTextView;
        public TextView castCharacterTextView;
        //public ImageButton movieFavImageButton;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            movieCard = (CardView) itemView.findViewById(R.id.card_view_movie_cast);
            moviePosterImageView = (ImageView) itemView.findViewById(R.id.image_view_movie_cast);
            movieTitleTextView = (TextView) itemView.findViewById(R.id.text_view_title_movie_cast);
            castCharacterTextView = (TextView) itemView.findViewById(R.id.text_view_cast_character_movie_cast);
            //movieFavImageButton = (ImageButton) itemView.findViewById(R.id.image_button_fav_movie_cast);
        }
    }

}