package com.example.lguti.nytimesearch.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lguti on 6/22/16.
 */

public class SearchFilters implements Parcelable {
    public String begin_date;
    public String sort;
    public String news_desk;



    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(begin_date);
        out.writeString(sort);
        out.writeString(news_desk);
    }

    private SearchFilters(Parcel in){
        begin_date = in.readString();
        sort = in.readString();
        news_desk = in.readString();
    }

    public String getBegin_date() {
        return begin_date;
    }

    public String getSort(){
        return sort;
    }

    public String getNews_desk(){
        return news_desk;
    }




    public SearchFilters(String begin_date, String sort, String news_desk){
        this.begin_date = begin_date;
        this.sort = sort;
        this.news_desk = news_desk;

    }



    @Override
    public int describeContents() {
        return 0;
    }


    public static final Parcelable.Creator<SearchFilters> CREATOR
            = new Parcelable.Creator<SearchFilters>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public SearchFilters createFromParcel(Parcel in) {
            return new SearchFilters(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public SearchFilters[] newArray(int size) {
            return new SearchFilters[size];
        }
    };


}
