package com.project300.movieswipe;

import android.content.Context;
import android.graphics.Movie;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.List;

public class arrayAdapter extends ArrayAdapter {


   Context context;
   //Change back to cards**********List<cards>
    public arrayAdapter(Context context, int resourceId, List<cards>items){
        super(context, resourceId, items);
    }


    public View getView(int position, View convertView, ViewGroup parent){

        cards card_item = (cards) getItem(position);

       //MovieModelClass movie_item = (MovieModelClass)getItem(position);

        if(convertView == null)
        {
            convertView  = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }





        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);

       //re comment********************
       name.setText(card_item.getName());
        image.setImageResource(R.mipmap.ic_launcher);

        name.setText(card_item.getName());
        image.setImageResource(R.mipmap.ic_launcher);


        //dont forget to return this
        return convertView;


    }




}
