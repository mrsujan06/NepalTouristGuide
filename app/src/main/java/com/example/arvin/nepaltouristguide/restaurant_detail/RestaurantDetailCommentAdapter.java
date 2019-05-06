package com.example.arvin.nepaltouristguide.restaurant_detail;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.model.placeDetailResponse.PlaceDetailResponse;

public class RestaurantDetailCommentAdapter extends RecyclerView.Adapter<RestaurantDetailCommentAdapter.RestaurantDetailAdapterViewHolder> {

    private PlaceDetailResponse mResponse;

    public RestaurantDetailCommentAdapter(PlaceDetailResponse mResponse) {
        this.mResponse = mResponse;
    }

    @Override
    public RestaurantDetailCommentAdapter.RestaurantDetailAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_review_layout, parent, false);
        return new RestaurantDetailCommentAdapter.RestaurantDetailAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailCommentAdapter.RestaurantDetailAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mResponse.getResult().getReviews().size();
    }

    class RestaurantDetailAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView date;
        RatingBar userRatingBar;
        TextView comments;

        public RestaurantDetailAdapterViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            date = itemView.findViewById(R.id.date);
            userRatingBar = itemView.findViewById(R.id.userRatingBar);
            comments = itemView.findViewById(R.id.user_comments);
        }

        public void bindView(int position) {

            try {
                username.setText(mResponse.getResult().getReviews().get(position).getAuthorName());
                date.setText(mResponse.getResult().getReviews().get(position).getRelativeTimeDescription());
                userRatingBar.setRating(mResponse.getResult().getReviews().get(position).getRating());
                comments.setText(mResponse.getResult().getReviews().get(position).getText());
            } catch (Exception e) {
                Log.e("Error message is ", e.getMessage());
            }
        }
    }
}

