package com.example.justinlee.recycleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    ArrayList<String> addArray = new ArrayList<String>();
    EditText userInput;
    ListView showGoal;
    ImageButton backButton;
    ImageButton editButton;
    carbon.widget.FloatingActionButton plusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userInput = (EditText)findViewById(R.id.goalname);
        showGoal = (ListView)findViewById(R.id.goallist);

        plusButton = (carbon.widget.FloatingActionButton) findViewById(R.id.plus_button) ;
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = userInput.getText().toString();
                if(addArray.contains(getInput)) {
                    Toast.makeText(getBaseContext(), "You already added this Goal!", Toast.LENGTH_LONG);
                }
                else if(getInput == null || getInput.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Enter the name of your Goal", Toast.LENGTH_LONG);
                }
                else{
                    addArray.add(getInput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(profile.this, android.R.layout.simple_list_item_1, addArray);
                    showGoal.setAdapter(adapter);
                    ((EditText)findViewById(R.id.goalname)).setText(" ");
                }
            }
        });

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

            /*
        plusButton = (carbon.widget.FloatingActionButton) findViewById(R.id.plus_button) ;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(profile.this, addgoal.class);
                startActivity(intentLoadActivity);
            }
        }); */
    }
}
