package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    String tempSalary;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    private Button viewPersonalBudget;
    private Button createGroupBudget;
    Button mUserProfileBtn;
    String userID;
    EditText income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        income = findViewById(R.id.salary_input_profile);
        viewPersonalBudget = findViewById(R.id.viewPersonalBudget);
        createGroupBudget = findViewById(R.id.createGroupBudget);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

//        DocumentReference documentReference = fStore.collection("users").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
//                tempSalary = (documentSnapshot.getString("Salary"));
//                int temp = Integer.parseInt(tempSalary);
//                salary.setText(temp);
//            }
//        });

        mUserProfileBtn = findViewById(R.id.profileButton);

        mUserProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewProfile.class);
                startActivity(intent);
            }
        });

        viewPersonalBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, personalBudget.class);
                // used to pass into the next activity
                String s = "";
                if (income.getText().toString().isEmpty()) {
                    // if no income was put we'll use default value of 40000
                    s += "40000";
                } else {
                    s += income.getText().toString();
                }
                // use the key 'income' to access salary in the next activity
                intent.putExtra("income", s);
                startActivity(intent);
            }
        });

        createGroupBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, createGroupBudget.class);
                startActivity(intent);
            }
        });

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut(); //logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
