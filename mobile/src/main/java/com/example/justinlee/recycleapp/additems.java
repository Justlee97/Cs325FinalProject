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
import android.widget.TextView;

import org.w3c.dom.Text;

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


//        TextView daily = ((TextView) itemlist.
//                findViewById(R.layout.activity_itemlist));
//        TextView total = ((TextView)findViewById(R.id.textView8));
////        daily.setText(mprefs.getString("dailystr", "0"));
////        total.setText(mprefs.getString("totalstr", "0"));
//        String dailystr = daily.toString();
//        String totalstr = total.toString();
//        int dailynum = Integer.parseInt(dailystr);
//        int totalnum = Integer.parseInt(totalstr);
//        editor.putString("dailynum", ""+dailynum);
//        editor.putString("totalnum", ""+totalnum);
//        editor.apply();
//        editor.commit();

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
        String[] categoriesSpinner = new String[] {"Category", "Plastic", "Glass","Paper"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(additems.this, R.layout.spinner_item,categoriesSpinner){
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
        spinner.setAdapter(adapter);



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
                if ((finalamount + mprefs.getInt("Pbar" , 0))%8 == 0) editor.putInt("level", mprefs.getInt("level", 0) + 1);
                editor.putInt("Pbar", (mprefs.getInt("Pbar" , 0) + finalamount)%8);
                editor.putInt("ListValue",mprefs.getInt("ListValue", 0) + finalamount);
                //int dailynumval = mprefs.getInt("dailynum", 0);
                //dailynumval += finalamount;
                //int totalnumval = mprefs.getInt("totalnum", 0);
                //totalnumval += finalamount;
                //editor.putString("itemadded",amount.toString());
                //editor.putInt("dailynum",dailynumval);
                //editor.putInt("totalnum",totalnumval);

//                int alteredDaily = (dailynum + finalamount);
//                int alteredTotal = ((totalnum + finalamount));
//                editor.putString("daily", "" + alteredDaily);
//                editor.putString("total", ""+ alteredTotal);
//                daily.setText("" + alteredDaily);
//                total.setText("" + alteredTotal);
                editor.apply();
                editor.commit();
                finish();
            }
        });
    }

}
