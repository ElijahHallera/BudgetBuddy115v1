package com.example.budgetbuddy115v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomPersonalBudget extends AppCompatActivity {

    EditText savingsInput, necessitiesInput, freeSpendingInput;
    private Button returnHome;
    private Button generateBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_personal_budget);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        String chosen = extra.getString("income");

        returnHome = findViewById(R.id.returnHome);
        generateBudget = findViewById(R.id.generateBudget);
        savingsInput = findViewById(R.id.customSavings);
        necessitiesInput = findViewById(R.id.customNecessities);
        freeSpendingInput = findViewById(R.id.customFreeSpending);


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomPersonalBudget.this, MainActivity.class);
                startActivity(intent);
            }
        });

        generateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if input ratio values are equal to 100
                if (checkRatio() == true) {

                    String tempSavings = savingsInput.getText().toString();
                    String tempNecessities = necessitiesInput.getText().toString();
                    String tempFreeSpending = freeSpendingInput.getText().toString();
                    int savingsInt = Integer.parseInt(tempSavings);
                    int necessitiesInt = Integer.parseInt(tempNecessities);
                    int freeSpendingInt = Integer.parseInt(tempFreeSpending);

                    Intent intent = new Intent(CustomPersonalBudget.this, personalBudget.class);
                    String s = "";
                    s += "40000";

                    intent.putExtra("CUSTOM_SAVINGS", savingsInt);
                    intent.putExtra("CUSTOM_NECESSITIES", necessitiesInt);
                    intent.putExtra("CUSTOM_FREESPENDING", freeSpendingInt);
                    intent.putExtra("RADIO_CHOICE", "custom");
                    intent.putExtra("income", s);
                    startActivity(intent);
                } else {
                    return;
                }
            }
        });

    }

    private boolean checkRatio(){
        String tempSavings = savingsInput.getText().toString();
        String tempNecessities = necessitiesInput.getText().toString();
        String tempFreeSpending = freeSpendingInput.getText().toString();
        int savingsInt = Integer.parseInt(tempSavings);
        int necessitiesInt = Integer.parseInt(tempNecessities);
        int freeSpendingInt = Integer.parseInt(tempFreeSpending);

        if((savingsInt + necessitiesInt + freeSpendingInt) == 100){
            return true;
        } else {
            Toast.makeText(CustomPersonalBudget.this, "Error! Make sure it equals 100!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

}