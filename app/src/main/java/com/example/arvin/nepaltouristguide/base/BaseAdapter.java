package com.example.arvin.nepaltouristguide.base;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.model.placeResponse.ApiResponse;
import com.example.arvin.nepaltouristguide.model.placeResponse.Result;
import com.example.arvin.nepaltouristguide.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseRecyclerViewAdapterViewHolder> implements Filterable {

    private ApiResponse mApiResponse;
    private List<Result> filterResponse;

    public BaseAdapter(ApiResponse mApiResponse) {
        this.mApiResponse = mApiResponse;
        filterResponse = new ArrayList<>(mApiResponse.getResults());
    }

    @Override
    public BaseAdapter.BaseRecyclerViewAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_layout_with_ratings, parent, false);
        return new BaseAdapter.BaseRecyclerViewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapter.BaseRecyclerViewAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mApiResponse.getResults().size();
    }

    class BaseRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView place_image;
        TextView place_name;
        TextView userRatingText;
        TextView userTotalReviews;
        RatingBar userRatingStars;
        Typeface custom_font;

        private int mIndex;

        public BaseRecyclerViewAdapterViewHolder(View itemView) {
            super(itemView);
            mIndex = itemView.getId();
            place_image = itemView.findViewById(R.id.place_photo);
            place_name = itemView.findViewById(R.id.place_image);
            userRatingText = itemView.findViewById(R.id.user_ratings);
            userTotalReviews = itemView.findViewById(R.id.user_total_reviews);
            userRatingStars = itemView.findViewById(R.id.user_rating_star);
            custom_font = Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/Montserrat_Regular.otf");

            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bindView(int position) {

            try {
                mIndex = position;
                String photoReference = mApiResponse.getResults().get(position).getPhotos().get(0).getPhotoReference();
                String imageURL = Constants.IMAGE_URL + photoReference + "&key=" + Constants.API_KEY;

                Picasso.get().load(imageURL).into(place_image);
                place_name.setText(mApiResponse.getResults().get(position).getName());
                place_name.setTypeface(custom_font);

                userRatingText.setText(mApiResponse.getResults().get(position).getRating().toString());
                userRatingStars.setRating(mApiResponse.getResults().get(position).getRating().intValue());
                userTotalReviews.setText(mApiResponse.getResults().get(position).getUserRatingsTotal().toString());
            } catch (Exception e) {
                Log.e("Error message is ", e.getMessage());
            }
        }

        @Override
        public void onClick(View v) {
            onListItemSelected(mIndex, mApiResponse);
        }
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }

    private Filter listFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Result> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterResponse);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Result item : filterResponse) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mApiResponse.getResults().clear();
            mApiResponse.getResults().addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    protected abstract void onListItemSelected(int index, ApiResponse mApiResponse);

}
