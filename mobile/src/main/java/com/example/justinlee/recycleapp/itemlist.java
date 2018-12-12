package com.example.justinlee.recycleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import java.util.*;


public class itemlist extends AppCompatActivity {

    ImageButton backButton;
    carbon.widget.Button plasticButton;
    carbon.widget.Button paperButton;
    carbon.widget.Button glassButton;
    carbon.widget.Button electronicsButton;
    carbon.widget.FloatingActionButton plusButton;
    ListView bin;
    Adapter adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        String[] dude = {"Plastic Bottles: 1"};
        bin = (ListView)findViewById(R.id.bin);
        List<String> currlist = new ArrayList<String>(Arrays.asList(dude));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(itemlist.this, android.R.layout.simple_list_item_1,currlist);
        bin.setAdapter(arrayAdapter);

        backButton = (ImageButton) findViewById(R.id.back_button) ;
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, MainActivity.class);
                startActivity(intentLoadActivity);
            }
        });
        //plastic guide page
        plasticButton = (carbon.widget.Button) findViewById(R.id.plastic_button) ;
        plasticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, plastic.class);
                startActivity(intentLoadActivity);
            }
        });
        //paper guide page
        paperButton = (carbon.widget.Button) findViewById(R.id.paper_button) ;
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, paper.class);
                startActivity(intentLoadActivity);
            }
        });
        //glass guide page
        glassButton = (carbon.widget.Button) findViewById(R.id.glass_button) ;
        glassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, glass.class);
                startActivity(intentLoadActivity);
            }
        });
        //electronics guide page
        electronicsButton = (carbon.widget.Button) findViewById(R.id.electronics_button) ;
        electronicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, electronics.class);
                startActivity(intentLoadActivity);
            }
        });


        plusButton = (carbon.widget.FloatingActionButton) findViewById(R.id.plus_button) ;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(itemlist.this, additems.class);
                startActivity(intentLoadActivity);
            }
        });

    }
}
