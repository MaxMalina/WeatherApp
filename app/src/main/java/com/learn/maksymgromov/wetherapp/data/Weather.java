package com.learn.maksymgromov.wetherapp.data;

public class Weather {
    private String mDescription;
    private String mLocation;
    private String mTemperature;
    private String mHumidity;
    private String mPicture;
    private String mImgLink;

    public Weather() {
    }

    public Weather(String mTemperature, String mDescription, String mLocation, String mHumidity, String mPicture, String mImgLink) {
        this.mTemperature = mTemperature;
        this.mDescription = mDescription;
        this.mLocation = mLocation;
        this.mHumidity = mHumidity;
        this.mPicture = mPicture;
        this.mImgLink = mImgLink;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String mHumidity) {
        this.mHumidity = mHumidity;
    }

    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String mPicture) {
        this.mPicture = mPicture;
    }

    public String getImgLink() {
        return mImgLink;
    }

    public void setImgLink(String mImgLink) {
        this.mImgLink = mImgLink;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
