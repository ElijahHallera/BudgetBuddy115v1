package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createGroupBudget extends AppCompatActivity {
    EditText budgetName, tAmount, sAmount;
    Button creating;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_budget);

        budgetName = findViewById(R.id.budgName);
        tAmount = findViewById(R.id.totalAmount);
        sAmount = findViewById(R.id.startingAmount);
        creating = findViewById(R.id.create);

        fstore = FirebaseFirestore.getInstance();

        creating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String budgetname = budgetName.getText().toString().trim();
                String dummytotal = tAmount.getText().toString().trim();
                String dummysaved = sAmount.getText().toString().trim();

                int total = Integer.parseInt(dummytotal);
                int saved = Integer.parseInt(dummysaved);

                Map<String, Object> docData = new HashMap<>();
                docData.put("budgetName", budgetname);
                docData.put("Total", total);
                docData.put("SavedsoFar", saved);

                Map<String, Object> participants = new HashMap<>();
                participants.put("1", "christian");
                participants.put("2", "Natalia");
                participants.put("3", "Elijah");
                participants.put("4", "Victor");

                docData.put("participants", participants);

                DocumentReference documentReference = fstore.collection("groupBudget").document(budgetname);
                documentReference.set(docData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "group budget created");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "group budget failed");
                    }
                });


                //Toast.makeText(createGroupBudget.this, "All Credentials: " + budgetName + " " + tAmount + " " + sAmount + " " + creating, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), groupBudget.class));

            }
        });

    }

}