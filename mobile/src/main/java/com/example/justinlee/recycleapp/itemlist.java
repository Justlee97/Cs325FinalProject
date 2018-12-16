package com.example.justinlee.recycleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import java.util.*;
import android.graphics.Typeface;
import android.widget.TextView;


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
        SharedPreferences mprefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mprefs.edit();
        //Hashmap to ArrayList to ListView
        //We can try to save hashmap to internal memory
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("Paper", 3);
        //This is for test list
        int val = 0;
        boolean listvalexiss = mprefs.contains("ListValue");
        if (listvalexiss){val =  mprefs.getInt("ListValue", 1);}
        else{
            val = 1;
            editor.putInt("ListValue", 1);
            editor.apply();
            editor.commit();
        }

        map.put("Plastic bottles", val);
        map.put("Glass bottle", 1);
        map.put("Electronic phone",1);

        List<String> currlist = new ArrayList<String>();
        for(Map.Entry<String, Integer >e: map.entrySet()){
            currlist.add(e.getKey() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + e.getValue());
        }
        bin = (ListView)findViewById(R.id.bin);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(itemlist.this, android.R.layout.simple_list_item_1,currlist);
        bin.setAdapter(arrayAdapter);


        TextView daily = ((TextView)findViewById(R.id.textView7));
        TextView total = ((TextView)findViewById(R.id.textView8));


        boolean dailyexists = mprefs.contains("dailynum");
        if (dailyexists){
            int dnum = mprefs.getInt("dailynum", 0);
            String dailytxt = "" + dnum;
            daily.setText(dailytxt);}
        else{
            daily.setText("0");
            editor.putInt("dailynum", 0);
            editor.apply();
            editor.commit();
        }

        boolean totalexists = mprefs.contains("totalnum");
        if (totalexists){
            int tnum = mprefs.getInt("totalnum", 0);
            String totaltxt = "" + tnum;
            total.setText(totaltxt);}
        else {
            //Initilization
            editor.putInt("totalnum", 6);
            int testing = 5;
            editor.apply();
            editor.commit();
            String x = "" + (5 + val);
            total.setText(x);
        }
//        int dnum = mprefs.getInt("dailynum", 0);
//        int tnum = mprefs.getInt("totalnum", 0);
//
//        String totaltxt = "" + tnum;
//        String totalstr = ""+  mprefs.getInt("total", 0);
//        daily.setText("0");


        editor.apply();
        editor.commit();






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
//                int alteredDaily = (1);
//                int alteredTotal = (( 1));
//                editor.putString("daily", "" + alteredDaily);
//                editor.putString("total", ""+ alteredTotal);
//                editor.apply();
//                editor.commit();
            }
        });

    }
}
