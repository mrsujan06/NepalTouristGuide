package com.example.arvin.nepaltouristguide.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arvin.nepaltouristguide.R;
import com.example.arvin.nepaltouristguide.model.ApiResponse;
import com.example.arvin.nepaltouristguide.model.api.ApiList;
import com.squareup.picasso.Picasso;


public abstract class BaseVisitNepalAdapter extends RecyclerView.Adapter<BaseVisitNepalAdapter.BaseRecyclerViewAdapterViewHolder> {

    ApiResponse mApiResponse;

    public BaseVisitNepalAdapter(ApiResponse mApiResponse) {
        this.mApiResponse = mApiResponse;
    }

    @Override
    public BaseVisitNepalAdapter.BaseRecyclerViewAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_places_layout, parent, false);
        return new BaseVisitNepalAdapter.BaseRecyclerViewAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseVisitNepalAdapter.BaseRecyclerViewAdapterViewHolder holder, int position) {
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
    protected abstract void onListItemSelected(int index, ApiResponse mApiResponse);

}
