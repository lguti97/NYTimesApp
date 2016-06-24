package com.example.lguti.nytimesearch.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lguti.nytimesearch.model.Article;
import com.example.lguti.nytimesearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lguti on 6/20/16.
 */
public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public ArticleArrayAdapter (Context context, List<Article> articles){
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        Article article = this.getItem(position);


        //check to see if current view is being reused or recycled
        //not using a recycle view then inflate layout
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            //telling convertView to use the format that I created! which was item_article_result!
            convertView = inflater.inflate(R.layout.item_article_result, parent, false);
        }

        // find imageview
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivImage);

        //clear out recycled image from ConvertView from last time. this is to clear it out
        imageView.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(article.getHeadline());

        //populate thumbnail image
        //remote download image in background
        String thumbnail = article.getThumbNail();
        //checks if there is a thumbnail


        //TextUtils is the text and this is basically checking for an empty string
        if (!TextUtils.isEmpty(thumbnail)) {
            Picasso.with(getContext()).load(thumbnail).placeholder(R.drawable.ic_action_name).into(imageView);
        }
        else {
            imageView.setImageResource(R.drawable.ic_action_name);
        }


        return convertView;

    }
}
