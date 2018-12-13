package com.example.justinlee.recycleapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class profile extends AppCompatActivity implements NewDialog.DialogListener {

    ArrayList<String> goalArray;
    EditText userInput;
    ListView showGoal;

    private TextView profileName;

    ImageButton backButton;
    ImageButton editButton;
    carbon.widget.FloatingActionButton plusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadData();

        profileName = findViewById(R.id.profile_name);

        //store user input into a list, and then display it with most recent on top.
        userInput = (EditText) findViewById(R.id.goalname);
        showGoal = (ListView) findViewById(R.id.goallist);

        plusButton = (carbon.widget.FloatingActionButton) findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = userInput.getText().toString();
                if (goalArray.contains(getInput)) {
                    Toast.makeText(getBaseContext(), "You already added this Goal!", Toast.LENGTH_LONG);
                } else if (getInput == null || getInput.trim().equals("")) {
                    //I have no idea what Toast does tbh.
                    Toast.makeText(getBaseContext(), "Enter the name of your Goal", Toast.LENGTH_LONG);
                } else {
                    goalArray.add(getInput);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(profile.this, android.R.layout.simple_list_item_1, goalArray);
                    showGoal.setAdapter(adapter);
                    ((EditText) findViewById(R.id.goalname)).setText(" ");
                }
                saveData();
            }
        });

        backButton = (ImageButton) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadActivity = new Intent(profile.this, MainActivity.class);
                startActivity(intentLoadActivity);
            }
        });

        editButton = (ImageButton) findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                //Intent intentLoadActivity = new Intent(profile.this, MainActivity.class); //NEED TO CHANGE REDIRECTION  OF EDIT BUTTON TO EDIT PAGE (DO WE NEED AN EDIT PAGE?)
                //startActivity(intentLoadActivity);
            }
        });
    }

    public void openDialog() {
        NewDialog exampleDialog = new NewDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //method that will change the profile name on profile page from the dialog popup
    @Override
    public void applyText(String name) {
        profileName.setText(name);
    }

    //save and load the goals whenever the app is used.
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(goalArray);
        editor.putString("goal list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("goal list", null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        goalArray = gson.fromJson(json, type);

        if (goalArray == null) {
            goalArray = new ArrayList<>();
        }
    }
}
