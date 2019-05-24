package com.example.recyclerviewjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<ExampleItems> mExampleItems;

    public ExampleAdapter(Context mContext, ArrayList<ExampleItems> mExampleItems) {
        this.mContext = mContext;
        this.mExampleItems = mExampleItems;
    }

    public static final class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView mCreator;
        TextView mLikes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            mCreator = itemView.findViewById(R.id.tv_creatorName);
            mLikes = itemView.findViewById(R.id.tv_likes);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_layout, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        ExampleItems mCurrentItem = mExampleItems.get(i);

        String image = mCurrentItem.getmImageURL();
        String creator = mCurrentItem.getmCreator();
        int likes = mCurrentItem.getmLikes();

        myViewHolder.mCreator.setText(creator);
        myViewHolder.mLikes.setText("Likes: "+likes);

        Picasso.with(mContext).load(image).fit().centerInside().into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mExampleItems.size();
    }

}
