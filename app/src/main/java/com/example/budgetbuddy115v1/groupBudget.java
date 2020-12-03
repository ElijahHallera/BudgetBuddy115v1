package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class groupBudget extends AppCompatActivity {

    private TextView text;
    private Button goHome;
    //in order to add a way to go to groubbudget page
    //private Button goToBudgets;

    //for the pie chart
    float budget;
    String salaryText;
    float expenses;
    float leftOver;
    float savings;
    //float budgetSalary[] = {expenses, leftOver, savings};
    //String budgetNames[] = {"expenses", "leftOver", "savings"};
    String mainName;
    int totalAmount;
    int totalSaved;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_budget);
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            mainName = extra.getString("name");

        }



        goHome = findViewById(R.id.Home);
        text = findViewById(R.id.budgetName);
        text.setText(mainName);

        setupPieChart();

        Toast.makeText(groupBudget.this, "All Credentials: " + mainName, Toast.LENGTH_SHORT).show();


        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(groupBudget.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setupPieChart(){

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            budget = extra.getFloat("total");
            savings = extra.getFloat("savings");
            expenses = extra.getFloat("expenses");

        }
        leftOver = budget - (expenses + savings);
        salaryText = String.format("%.2f", budget);
        float budgetSalary[] = {expenses, leftOver, savings};
        String budgetNames[] = {"expenses", "leftOver", "savings"};
        //budgetSalary[] = {expenses, leftOver, savings};

        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; i < budgetSalary.length; i++){
            pieEntries.add(new PieEntry(budgetSalary[i], budgetNames[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueTextSize(20f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = findViewById(R.id.piechart);
        chart.setData(data);
        chart.setCenterText("$" + salaryText);
        chart.setCenterTextSize(25);
        chart.animateY(1000);
        chart.invalidate();
        chart.setEntryLabelTextSize(14);
        chart.getDescription().setEnabled(false);

    }

}

