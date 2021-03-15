package com.project300.movieswipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private EditText mNameField;

    private TextView mUserID;

    private Button mBack, mConfirm;

    //get current user ID
    private FirebaseAuth mAuth;

    private DatabaseReference mCustomerDatabase;

    private String userID, name;

    private URI resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mNameField = findViewById(R.id.name);
        mUserID = findViewById(R.id.userID);

        mBack = findViewById(R.id.back);
        mConfirm = findViewById(R.id.confirm);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            userID = user.getUid();
        }

        mUserID.setText(userID);

        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        getUserInfo();

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });


    }

    private void getUserInfo() {

        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.getChildrenCount()> 0){
                    Map<String, Object> map = (Map<String, Object>) snapshot.getValue();

                    if(map.get("name")!=null){
                        name = map.get("name").toString();
                        mNameField.setText(name);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void saveUserInformation() {

        name = mNameField.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name", name);


        mCustomerDatabase.updateChildren(userInfo);




    }
}