package com.example.justinlee.recycleapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.content.Context;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Gravity;
import android.os.Build;
import android.view.ViewGroup.LayoutParams;
import android.widget.Spinner;

public class additems extends AppCompatActivity {
    private Spinner spinner;
    ImageButton backButton;
    Button button;
    Context mContext;
    Activity mActivity;
    RelativeLayout mRelativeLayout;
    carbon.widget.Button mButton;
    PopupWindow mPopupWindow;
    EditText amount;
    EditText item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);

        button = (Button)findViewById(R.id.button); //yeet button
        amount = (EditText)findViewById(R.id.edittext);//user input
        item = (EditText)findViewById(R.id.itemname); //Item name

        SharedPreferences mprefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mprefs.edit();
        mprefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        int currentLevel = mprefs.getInt("level", 1);
        editor.apply();
        editor.commit();
//        int currentprogress = mprefs.getInt("Pbar",0);
//        EditText das = (EditText)findViewById(R.id.edittext);
//        das.setText(currentprogress);

        backButton = (ImageButton) findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(additems.this, itemlist.class);
                startActivity(intentLoadActivity);
                finish();
            }
        });


        spinner = (Spinner)findViewById(R.id.spinner);
        String[] categoriesSpinner = new String[] {"Plastic", "Glass","Paper"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(additems.this, android.R.layout.simple_spinner_item,categoriesSpinner);

//        ProgressBar progressbar = (ProgressBar) findViewById(R.id.progressBar);
//        EditText numadded = (EditText)findViewById(R.id.edittext);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentLoadActivity = new Intent(additems.this, itemlist.class);
//                startActivity(intentLoadActivity);
//                finish();
//            }
//        });
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(additems.this, itemlist.class);
                startActivity(intentLoadActivity);



                SharedPreferences mprefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                String amountadded = amount.getText().toString();
                int finalamount = Integer.parseInt(amountadded);
                editor.putInt("Pbar", mprefs.getInt("Pbar" , 0) + finalamount);
                editor.putString("itemadded",amount.toString());
                editor.apply();
                editor.commit();
                finish();
            }
        });
    }

}
