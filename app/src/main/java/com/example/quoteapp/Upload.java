package com.example.quoteapp;

public class Upload {

    private String mName;
    private String mImageUrl;

    public Upload(){}
    public Upload(String imageUrl){
        mImageUrl = imageUrl;
    }
    public Upload(String name,String imageUrl){

        if(name.trim().equals("")){
            name = "NO Name";
        }



        mName = name;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
