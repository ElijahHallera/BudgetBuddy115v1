package com.example.budgetbuddy115v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class createPersonalAnnualBudget extends AppCompatActivity {

    private RadioGroup rgb;
        private RadioButton presetButton;
        private RadioButton customButton;
        private String selectedType = "";

    private Button returnHome;
    private Button generateBudget;

    EditText income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal_annual_budget);

        income = findViewById(R.id.salary_input_profile);
        returnHome = findViewById(R.id.returnHome);
        generateBudget = findViewById(R.id.generateBudget);
        rgb = (RadioGroup) findViewById(R.id.radioGroup);
        presetButton = (RadioButton) findViewById(R.id.radioButton1);
        customButton = (RadioButton) findViewById(R.id.radioButton2);

        rgb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton1){
                    selectedType = "preset";
                }else if(checkedId == R.id.radioButton2) {
                    selectedType = "custom";
                }
            }
        });

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createPersonalAnnualBudget.this, MainActivity.class);
                startActivity(intent);
            }
        });

        generateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedType.equals("preset")){
                    Intent intent = new Intent(createPersonalAnnualBudget.this, personalBudget.class);
                    String s = "";
                    if (income.getText().toString().isEmpty()) {
                        // if no income was put we'll use default value of 40000
                        s += "40000";
                    } else {
                        s += income.getText().toString();
                    }
                    intent.putExtra("RADIO_CHOICE", selectedType);
                    intent.putExtra("income", s);
                    startActivity(intent);
                } else if (selectedType.equals("custom")){
                    Intent intent = new Intent(createPersonalAnnualBudget.this, CustomPersonalBudget.class);
                    String s = "";
                    if (income.getText().toString().isEmpty()) {
                        // if no income was put we'll use default value of 40000
                        s += "40000";
                    } else {
                        s += income.getText().toString();
                    }
                    intent.putExtra("RADIO_CHOICE", selectedType);
                    intent.putExtra("income", s);
                    startActivity(intent);
                }
            }
        });

    }

        public void onRadioButtonClicked(View view) {
            String passingParameter = "";
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.radioButton1:
                    if (checked) passingParameter = "preset";
                    break;
                case R.id.radioButton2:
                    if (checked) passingParameter = "custom";
                    break;
            }

            Intent intent = new Intent(createPersonalAnnualBudget.this, personalBudget.class);
            intent.putExtra("radioChosen", passingParameter);
        }
}