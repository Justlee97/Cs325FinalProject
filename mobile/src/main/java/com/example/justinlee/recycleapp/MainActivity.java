package com.example.justinlee.recycleapp;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {


    ImageButton profileButton;
    ImageButton hamburgerButton;
    ImageButton circleButton;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileButton = (ImageButton) findViewById(R.id.profile_button) ;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //Saving progress bar values
        SharedPreferences mprefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mprefs.edit();
        mprefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        int currentLevel = mprefs.getInt("level", 1);

        int currentprogress = mprefs.getInt("Pbar",0);
        progressBar.setProgress(currentprogress);

        //Finished loading previous progressbar
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(MainActivity.this, profile.class);
                startActivity(intentLoadActivity);

                //Copy and paste this code for where the actual addition of recycling bin stuff needs to go. It should do all of the progress bar stuff and saving of the data for you.
                if(currentprogress >= 8){
                    progressBar.setProgress(0);
                    editor.putInt("level", currentLevel+1);
                    editor.putInt("Pbar", 0);
                    editor.apply();
                    editor.commit();
                }
                else {
                    progressBar.setProgress(currentprogress + 1);
                    editor.putInt("Pbar", currentprogress + 1);
                    editor.apply();
                    editor.commit();
                }
            }
        });

        hamburgerButton = (ImageButton) findViewById(R.id.hamburger_button);

        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(MainActivity.this, itemlist.class);
                startActivity(intentLoadActivity);
            }
        });

        circleButton = (ImageButton) findViewById(R.id.circle_button) ;

        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(MainActivity.this, itemlist.class);
                startActivity(intentLoadActivity);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
