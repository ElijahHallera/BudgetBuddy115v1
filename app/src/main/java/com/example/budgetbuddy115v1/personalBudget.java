package com.example.budgetbuddy115v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class personalBudget extends AppCompatActivity {

    private Button returnHome;
    private Button groupBudget;

    float Salary = 120000;
    String salaryText = String.format("%.2f", Salary);
    float Necessities = Salary/2;
    float freeSpending = Salary/4;
    float Saving = Salary/4;
    float budgetSalary[] = {Necessities, freeSpending, Saving};
    String budgetCategory[] = {"Necessities", "Free-Spending", "Saving"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_budget);
        setupPieChart();

        returnHome = findViewById(R.id.backToHome);
        groupBudget = findViewById(R.id.viewGroupBudget);

        groupBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalBudget.this, groupBudget.class);
                startActivity(intent);
            }
        });

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personalBudget.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupPieChart() {
        //Populate a list of PieEntries:
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; i < budgetSalary.length; i++){
            pieEntries.add(new PieEntry(budgetSalary[i], budgetCategory[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setValueTextSize(20f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the Chart:
        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);
        chart.setCenterText("$" + salaryText);
        chart.setCenterTextSize(25);
        chart.animateY(1000);
        chart.invalidate();
        chart.setEntryLabelTextSize(14);
        chart.getDescription().setEnabled(false);
    }
}