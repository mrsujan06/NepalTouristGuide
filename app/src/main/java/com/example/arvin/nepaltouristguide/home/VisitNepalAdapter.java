package com.example.arvin.nepaltouristguide.home;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
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

public class VisitNepalAdapter extends RecyclerView.Adapter<VisitNepalAdapter.VisitNepalAdapterViewHolder> implements Filterable {

    private ApiResponse mApiResponse;
    private List<Result> filterResponse;
    OnCitySelectedInterface mListener;

    public VisitNepalAdapter(ApiResponse mApiResponse, OnCitySelectedInterface mListener) {
        this.mApiResponse = mApiResponse;
        this.mListener = mListener;
        filterResponse = new ArrayList<>(mApiResponse.getResults());
    }

    @Override
    public VisitNepalAdapter.VisitNepalAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_places_layout, parent, false);
        return new VisitNepalAdapter.VisitNepalAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitNepalAdapter.VisitNepalAdapterViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mApiResponse.getResults().size();
    }

    class VisitNepalAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView place_image;
        TextView place_name;
        Typeface custom_font;

        private int mIndex;

        public VisitNepalAdapterViewHolder(View itemView) {
            super(itemView);
            mIndex = itemView.getId();
            place_image = itemView.findViewById(R.id.place_picture);
            place_name = itemView.findViewById(R.id.place_name);
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

            } catch (Exception e) {
                Log.e("Error message is ", e.getMessage());
            }
        }

        @Override
        public void onClick(View v) {
            mListener.onCitySelected(mIndex, mApiResponse);
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

//    protected void onListItemSelected(int index, ApiResponse mApiResponse) {
//
//    }

}

