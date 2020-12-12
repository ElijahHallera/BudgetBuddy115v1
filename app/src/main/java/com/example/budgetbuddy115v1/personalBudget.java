package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class personalBudget extends AppCompatActivity {

    private Button returnHome;
    private Button groupBudget;
    private Button saveBudget;
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_budget);
        setupPieChart();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        returnHome = findViewById(R.id.backToHome);
        groupBudget = findViewById(R.id.viewGroupBudget);
        saveBudget = (Button) findViewById(R.id.saveBudget);

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

        saveBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle extra = intent.getExtras();
                String chosen = extra.getString("RADIO_CHOICE");
                String passedIncome = extra.getString("income");

                //IN FIRESTORE WERE GOING TO SAVE THE SALARY AND PRESET OR CUSTOM RATIO.

                //IF USER CHOSE PRESET WE WILL SAVE BUDGET UNDER PRESET WITH PRESET RATIOS
                if(chosen.equals("preset")) {
                    int presetSavings = 20;
                    int presetNecessities = 50;
                    int presetFreeSpending = 30;

                    Map<String, Object> personalBudgets = new HashMap<>();
                    personalBudgets.put("userID", fAuth.getCurrentUser().getUid());
                    personalBudgets.put("userSalary", passedIncome);
                    personalBudgets.put("userSavingsRatio", presetSavings);
                    personalBudgets.put("userNecessitiesRatio", presetNecessities);
                    personalBudgets.put("userFreeSpendingRatio", presetFreeSpending);
                    fStore.collection("personalBudgets").add(personalBudgets).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(personalBudget.this, "PERSONAL BUDGET ADDED", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();

                            Toast.makeText(personalBudget.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });

                    //IF USER CHOSE CUSTOM WE WILL SAVE BUDGET UNDER CUSTOM WITH INPUTTED RATIOS
                } else if(chosen.equals("custom")){
                    int passSavings = intent.getIntExtra("CUSTOM_SAVINGS", 0);
                    int passNecessities = intent.getIntExtra("CUSTOM_NECESSITIES", 0);
                    int passFreeSpending = intent.getIntExtra("CUSTOM_FREESPENDING", 0);
                    Map<String, Object> personalBudgets = new HashMap<>();
                    personalBudgets.put("userID", fAuth.getCurrentUser().getUid());
                    personalBudgets.put("userSalary", passedIncome);
                    personalBudgets.put("userSavingsRatio", passSavings);
                    personalBudgets.put("userNecessitiesRatio", passNecessities);
                    personalBudgets.put("userFreeSpendingRatio", passFreeSpending);
                    fStore.collection("personalBudgets").add(personalBudgets).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(personalBudget.this, "PERSONAL BUDGET ADDED", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();

                            Toast.makeText(personalBudget.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //CREATES THE PIE CHART TO DISPLAY BUDGET AND RATIOS
    private void setupPieChart() {
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        String chosen = extra.getString("RADIO_CHOICE");
        int passSavings = intent.getIntExtra("CUSTOM_SAVINGS", 0);
        int passNecessities = intent.getIntExtra("CUSTOM_NECESSITIES", 0);
        int passFreeSpending = intent.getIntExtra("CUSTOM_FREESPENDING", 0);
        Log.d("HELLLLOOOOO", chosen);
        float Necessities = 0;
        float freeSpending = 0;
        float Saving = 0;
        String salaryText = "";
        float Salary = 0;

        if(chosen.equals("preset")){
            //Handles User input for income
            Salary += Float.parseFloat(extra.getString("income"));
            salaryText += String.format("%.2f", Salary);

            //Handles the budget for the graph at a split ratio
            Necessities += Salary/2; //50%
            Double freeSpending1 = Salary * .3;
            freeSpending += freeSpending1.floatValue(); //30%
            Saving += Salary/5; //20%

        } else if(chosen.equals("custom")){

            Salary += Float.parseFloat(extra.getString("income"));
            salaryText += String.format("%.2f", Salary);

            Necessities = (Salary * passNecessities)/100;
            float freeSpending1 = (Salary * passFreeSpending)/100;
            freeSpending = freeSpending1;
            Saving = (Salary * passSavings)/100;
        }

        float budgetSalary[] = {Necessities, freeSpending, Saving};
        String budgetCategory[] = {"Necessities", "Free-Spending", "Saving"};

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
        PieChart chart = (PieChart) findViewById(R.id.depositedMoney);
        chart.setData(data);
        chart.setCenterText("$" + salaryText);
        chart.setCenterTextSize(25);
        chart.animateY(1000);
        chart.invalidate();
        chart.setEntryLabelTextSize(14);
        chart.getDescription().setEnabled(false);
    }
}