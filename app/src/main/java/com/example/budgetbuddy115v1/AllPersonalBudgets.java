package com.example.budgetbuddy115v1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class AllPersonalBudgets extends AppCompatActivity {


    //TextView fullname, email;
    TextView userID, userSalary, userSavingsRatio, userNecessitiesRatio, userFreeSpendingRatio;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_personal_budgets);

        userID = findViewById(R.id.UserID);
        userSalary = findViewById(R.id.userSalary);
        userSavingsRatio = findViewById(R.id.userSavingsRatio);
        userNecessitiesRatio = findViewById(R.id.userNecessitiesRatio);
        userFreeSpendingRatio = findViewById(R.id.userFreeSpendingRatio);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        String docref = "FeWeZ2QE9iD1BgNgFgL9";
        DocumentReference documentReference = fStore.collection("personalBudgets").document(docref);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                //fullname.setText(documentSnapshot.getString("fName"));
                //email.setText(documentSnapshot.getString("email"));


                int uSavingRatio = documentSnapshot.getLong("userSavingsRatio").intValue();
                String uSRatio = String.valueOf(uSavingRatio);

                int uNecessitiesRatio = documentSnapshot.getLong("userNecessitiesRatio").intValue();
                String uNRatio = String.valueOf(uNecessitiesRatio);

                int uFreeSpendingRatio = documentSnapshot.getLong("userFreeSpendingRatio").intValue();
                String uFSRatio = String.valueOf(uFreeSpendingRatio);


                userID.setText(documentSnapshot.getString("userID"));
                userSalary.setText(documentSnapshot.getString("userSalary"));
                userSavingsRatio.setText(uSRatio);
                userNecessitiesRatio.setText(uNRatio);
                userFreeSpendingRatio.setText(uFSRatio);

            }


        });
    }
}