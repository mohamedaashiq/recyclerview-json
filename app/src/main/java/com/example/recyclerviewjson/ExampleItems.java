package com.example.recyclerviewjson;

public class ExampleItems {

    String mImageURL;
    String mCreator;
    int mLikes;

    public ExampleItems(String mImageURL, String mCreator, int mLikes) {
        this.mImageURL = mImageURL;
        this.mCreator = mCreator;
        this.mLikes = mLikes;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }
}
