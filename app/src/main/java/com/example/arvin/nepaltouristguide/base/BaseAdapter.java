package com.example.arvin.nepaltouristguide.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.Result;
import com.example.arvin.nepaltouristguide.model.api.ApiList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_places_layout, parent, false);
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
        private int mIndex;

        public BaseRecyclerViewAdapterViewHolder(View itemView) {
            super(itemView);
            mIndex = itemView.getId();
            place_image = itemView.findViewById(R.id.place_picture);
            place_name = itemView.findViewById(R.id.place_name);

            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {

            try {
                mIndex = position;
                String photoReference = mApiResponse.getResults().get(position).getPhotos().get(0).getPhotoReference();
                String imageURL = ApiList.IMAGE_URL + photoReference + "&key=" + ApiList.API_KEY;

                Picasso.with(place_image.getContext()).load(imageURL).into(place_image);
                place_name.setText(mApiResponse.getResults().get(position).getName());
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
