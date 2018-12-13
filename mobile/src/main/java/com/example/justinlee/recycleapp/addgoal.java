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
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

//THIS PAGE IS DEFUNCT FOR NOW
public class addgoal extends AppCompatActivity {

    private Spinner goalSpinner;
    ImageButton backButton;
    Button addGoal;
    ArrayList<String> addArray = new ArrayList<String>();
    EditText userInput;
    ListView showGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoal);

        userInput = (EditText)findViewById(R.id.goalname);
        showGoal = (ListView)findViewById(R.id.goallist);

        addGoal = (Button)findViewById(R.id.addbutton);
        addGoal.setOnClickListener(new View.OnClickListener() {
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(addgoal.this, android.R.layout.simple_list_item_1, addArray);
                    showGoal.setAdapter(adapter);
                    ((EditText)findViewById(R.id.goalname)).setText(" ");
                }

            }
        });

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
