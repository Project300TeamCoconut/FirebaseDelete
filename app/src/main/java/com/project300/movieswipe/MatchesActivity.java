package com.project300.movieswipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mMatchesAdapter;

    private RecyclerView.LayoutManager mMatchesLayoutManager;

   private String currentUserID, currentMovie;

    TextView matchName;

    Context context;

    FirebaseUser currentFirebaseUser;

    Button btnDelete;

    Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches2);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        context = this.context;
        btnDelete = findViewById(R.id.btnDelete);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            currentUserID = user.getUid();
        }

        matchName = findViewById(R.id.MatchName);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

//        mRecyclerView.setFocusable(false);

//        mRecyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(context, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                    }
//
//                    @Override public void onLongItemClick(View view, int position) {
//                        currentMovie = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.MatchName)).getText().toString();
//                        Toast.makeText(getApplicationContext(), currentMovie, Toast.LENGTH_SHORT).show();
//                        counter = counter + 1;
//
//                    }
//                })
//        );

        //this will allow us to scroll freely through the recycler view with no hicups
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);


        //set a linear layout manager
        mMatchesLayoutManager = new LinearLayoutManager(MatchesActivity.this);

        //pass this layout manager to the recycler view
        mRecyclerView.setLayoutManager(mMatchesLayoutManager);


        //adapter
        mMatchesAdapter = new MatchesAdapter(getDataSetMatches(), MatchesActivity.this);


        //set the adapter to the recycler view
        mRecyclerView.setAdapter(mMatchesAdapter);

        getUserMatchId();


        for(int i=0; i< 100; i++)
        {

        }
        
    }

    private void getUserMatchId() {

        DatabaseReference matchDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("connections").child("matches");

        //if the user wants to get the most updated matchlist then they must go back and re enter this activity
        matchDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    //it is looking through //will pass the first match to the "match" variable
                    //use get key to get value
                    for(DataSnapshot match : snapshot.getChildren()){

                        //key is movie name in this case
                        FetchMatchInformation(match.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void FetchMatchInformation(String key) {

        String userID = "YR0YNOCdvBVWOLVtKuY9tdQCU8c2";


        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentFirebaseUser.getUid()).child("connections").child("matches");

        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                   // String userIDD = snapshot.getKey();
                    String name = "";
                    name = key;


                 //  if(snapshot.child("name").getValue()!= null){
                     //
                    //   name = snapshot.child("Users").child(userID).child("connections").child("matches").toString();
                 //   }

                    MatchesObject obj = new MatchesObject(name);
                    resultsMatches.add(obj);

                    mMatchesAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void deleteFirebase(View view) {
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        currentMovie = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.MatchName)).getText().toString();
                        Toast.makeText(getApplicationContext(), currentMovie, Toast.LENGTH_SHORT).show();
                        counter = counter + 1;
                        DatabaseReference usersDb;

                        Toast.makeText(MatchesActivity.this, "working",
                                Toast.LENGTH_LONG).show();


                        // Hannah UserID
                        String userID = "MrdUgrlSthVgF2qaMXGljrWD00w2";
                        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("connections").child("matches");

//        DatabaseReference currentUserConnectionsDB = usersDb.child(userID).child("connections").child("yep").child(FilmName);

                        DatabaseReference driverRef = userDb.child(currentMovie);

                            driverRef.removeValue();

                    }

                    @Override public void onLongItemClick(View view, int position) {

                    }
                })
        );

//        DatabaseReference usersDb;
//
//        Toast.makeText(MatchesActivity.this, "working",
//                Toast.LENGTH_LONG).show();
//
//
//        String userID = "MrdUgrlSthVgF2qaMXGljrWD00w2";
//        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("connections").child("matches");
//
////        DatabaseReference currentUserConnectionsDB = usersDb.child(userID).child("connections").child("yep").child(FilmName);
//
//        DatabaseReference driverRef = userDb.child(currentMovie);
//
//        if(counter > 0) {
//            driverRef.removeValue();
//        }

    }
    private ArrayList<MatchesObject> resultsMatches = new ArrayList<MatchesObject>();

    private List<MatchesObject> getDataSetMatches() {

        return resultsMatches;

    }
}