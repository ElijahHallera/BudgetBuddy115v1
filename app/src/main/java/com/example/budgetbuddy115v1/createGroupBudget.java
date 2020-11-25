package com.example.budgetbuddy115v1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class budgetSetUp extends AppCompatActivity {
    EditText budgetName, tAmount, sAmount;
    Button creating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupbugetsetup);

        budgetName = findViewById(R.id.budgName);
        tAmount = findViewById(R.id.totalAmount);
        sAmount = findViewById(R.id.startingAmount);
        creating = findViewById(R.id.create);
    }

}
