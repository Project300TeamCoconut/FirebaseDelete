package com.project300.movieswipe;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mMatchName;



    public MatchesViewHolders(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

       // mMatchId = (TextView) itemView.findViewById(R.id.Matchid);
        mMatchName = (TextView) itemView.findViewById(R.id.MatchName);

    }

    @Override
    public void onClick(View v) {

    }
}
