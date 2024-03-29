//package com.example.quoteapp;
//
//import android.content.ContentResolver;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
//
//    private Context mContext;
//    private List<Upload> mUploads;
//
//    public ImageAdapter(Context context,List<Upload> uploads){
//
//        mContext = context;
//        mUploads = uploads;
//
//    }
//
//    @Override
//    public ImageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
//
//        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_motivation,parent,false);
//        return new ImageViewHolder(v);
//
//    }
//
//    @Override
//    public void onBindViewHolder( ImageViewHolder holder, int position) {
//
//        Upload uploadCurrent = mUploads.get(position);
//        Picasso.get()
//                .load(uploadCurrent.getImageUrl())
//                .resize(425, 255)
//                .centerCrop()
//                .into(recycleViewHolder.mThumbnail);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mUploads.size();
//    }
//
//    public class ImageViewHolder extends RecyclerView.ViewHolder{
//        public ImageView imageView;
//
//        public ImageViewHolder(View itemView) {
//            super(itemView);
//
//            imageView = itemView.findViewById(R.id.uploaded);
//
//
//        }
//    }
//
//
//}
