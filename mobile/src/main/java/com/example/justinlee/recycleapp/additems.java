package com.example.justinlee.recycleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.view.View;
import android.widget.PopupWindow;
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
    Context mContext;
    Activity mActivity;
    RelativeLayout mRelativeLayout;
    carbon.widget.Button mButton;
    PopupWindow mPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);

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

        mContext = getApplicationContext();
        mActivity = additems.this;
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mButton = (carbon.widget.Button) findViewById(R.id.glass);
        spinner.setAdapter(adapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                // Inflate the custom layout/view
                View customView = inflater.inflate(R.layout.add_item_popup, null);

                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );

                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }

                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.close_button);

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);


            }
        });
    }

}
