package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class groupBudget extends AppCompatActivity {

    private Button goHome;
    //in order to add a way to go to groubbudget page
    //private Button goToBudgets;

    //for the pie chart
    float budget = 1000;
    String salaryText = String.format(".2f", budget);
    float expenses = 500;
    float leftOver = 200;
    float savings = 300;
    float budgetSalary[] = {expenses, leftOver, savings};
    String budgetNames[] = {"expenses", "leftOver", "savings"};


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_budget);
        goHome = findViewById(R.id.Home);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(groupBudget.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setupPieChart(){

    }

}

