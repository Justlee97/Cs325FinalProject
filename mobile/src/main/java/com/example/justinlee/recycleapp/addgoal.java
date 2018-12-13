package com.example.justinlee.recycleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;
import android.widget.Spinner;

public class addgoal extends AppCompatActivity {

    private Spinner goalSpinner;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoal);

        backButton = (ImageButton) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(addgoal.this, itemlist.class);
                startActivity(intentLoadActivity);
                finish();
            }
        });

        goalSpinner = (Spinner) findViewById(R.id.spinner);
        String[] categoriesSpinner = new String[]{"Category", "Any", "Plastic", "Glass", "Paper", "Electronics"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(addgoal.this, R.layout.spinner_item, categoriesSpinner) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };
        goalSpinner.setAdapter(adapter);
    }
}
