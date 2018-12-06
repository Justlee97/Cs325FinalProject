package com.example.justinlee.recycleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class profile extends AppCompatActivity {

    ImageButton backButton;
    ImageButton editButton;
    carbon.widget.FloatingActionButton plusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

            backButton = (ImageButton) findViewById(R.id.back_button) ;

            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLoadActivity = new Intent(profile.this, MainActivity.class);
                    startActivity(intentLoadActivity);
                }
            });

            editButton = (ImageButton) findViewById(R.id.edit_button) ;

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLoadActivity = new Intent(profile.this, MainActivity.class); //NEED TO CHANGE REDIRECTION  OF EDIT BUTTON TO EDIT PAGE (DO WE NEED AN EDIT PAGE?)
                    startActivity(intentLoadActivity);
                }
            });

        plusButton = (carbon.widget.FloatingActionButton) findViewById(R.id.plus_button) ;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(profile.this, MainActivity.class);
                startActivity(intentLoadActivity);
            }
        });
    }
}
