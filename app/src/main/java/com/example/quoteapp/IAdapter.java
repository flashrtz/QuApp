package com.example.quoteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.quoteapp.R.id.imageView;

//import com.squareup.picasso.Picasso;

public class IAdapter extends RecyclerView.Adapter<IAdapter.RecycleViewHolder> {

    Context mContext;
    List<Upload> mBands;
    private static final String TAG = "RecycleViewArrayAdapter";

    public IAdapter(Context context, List<Upload> bands) {
        mContext = context;
        mBands = bands;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_motivation, viewGroup, false);
        return new RecycleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int i) {
        final Upload currentBand = mBands.get(i);
//        recycleViewHolder.mTitle.setText(currentBand.getName());
        Picasso.get()
                .load(currentBand.getImageUrl())
                .resize(425, 255)
                .centerCrop()
                .placeholder(R.drawable.background_home)
                .into(recycleViewHolder.mThumbnail);
//        Log.d(TAG, "onBindViewHolder: title set -->" + currentBand.getName() + "In position -->" + i);
//        Picasso.get().load(currentBand.getImageUrl()).error(R.drawable.background_home).
//              placeholder(R.drawable.background_home).into(recycleViewHolder.mThumbnail);



    }

    @Override
    public int getItemCount() {
        return (mBands != null && mBands.size() != 0) ? mBands.size() : 0;
    }

    void loadNewData(List<Upload> bands) {
        mBands = bands;
        notifyDataSetChanged();
    }

    public Upload getBand(int position) {
        return (mBands != null && mBands.size() != 0 ? mBands.get(position) : null);
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder {
        ImageView mThumbnail;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
           // mTitle = (TextView) itemView.findViewById(R.id.bandTitle);
            mThumbnail = (ImageView) itemView.findViewById(R.id.uploaded);
        }
    }
}
