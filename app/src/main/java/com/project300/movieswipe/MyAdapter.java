package com.project300.movieswipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    private Context context;
    private List<MovieModelClass> objects;

    public MyAdapter(Context context, int resource, ArrayList<MovieModelClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent) {


        MovieModelClass movies = (MovieModelClass) getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

         ImageView img = (ImageView) convertView.findViewById(R.id.image);

        //   Glide.with(context)
        //       .load("https://image.tmdb.org/t/p/w500/"+ objects.get(position).getImg())
        //      .into(img);

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+ objects.get(position).getImg())
                .into(img);



        TextView name = (TextView) convertView.findViewById(R.id.name);

        name.setText(movies.getName());
        // img.setImageDrawable(movies.getImg());

        return convertView ;



    }

}
