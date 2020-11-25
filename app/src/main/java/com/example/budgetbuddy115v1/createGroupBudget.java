package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class createGroupBudget extends AppCompatActivity {
    EditText budgetName, tAmount, sAmount;
    Button creating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_budget);

        budgetName = findViewById(R.id.budgName);
        tAmount = findViewById(R.id.totalAmount);
        sAmount = findViewById(R.id.startingAmount);
        creating = findViewById(R.id.create);

        creating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), groupBudget.class));

            }
        });

    }

}
