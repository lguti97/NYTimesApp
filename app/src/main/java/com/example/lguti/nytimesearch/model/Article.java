package com.example.lguti.nytimesearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lguti on 6/20/16.
 */
public class Article implements Parcelable {
    public String getWebUurl() {
        return webUurl;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public String getHeadline() {
        return headline;
    }

    String webUurl;
    String headline;
    String thumbNail;

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(webUurl);
        out.writeString(headline);
        out.writeString(thumbNail);

    }

    private Article(Parcel in) {
        webUurl = in.readString();
        headline = in.readString();
        thumbNail = in.readString();

    }


    public Article(){

    }

    public Article(JSONObject jsonObject){
        try{
            this.webUurl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");

            //Extract multimedia
            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            //This is needed because there might not be any multimedia!
            if (multimedia.length() > 0){
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbNail = "http://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbNail = "";
            }
        } catch (JSONException e){

        }
    }
    //this is to parse all the JSONobjects into things into ArrayList using Article object as the type
    public static ArrayList<Article> fromJSONArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x ++){
            try{
                results.add(new Article(array.getJSONObject(x)));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

        return results;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<Article> CREATOR
            = new Parcelable.Creator<Article>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };


}
