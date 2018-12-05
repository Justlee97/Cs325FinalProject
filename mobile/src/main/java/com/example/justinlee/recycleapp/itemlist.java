package com.example.justinlee.recycleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;

public class itemlist extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        backButton = (ImageButton) findViewById(R.id.back_button) ;

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, MainActivity.class);
                startActivity(intentLoadActivity);
            }
        });

    }
}
