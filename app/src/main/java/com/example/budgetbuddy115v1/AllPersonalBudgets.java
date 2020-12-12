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
    TextView userID2, userSalary2, userSavingsRatio2, userNecessitiesRatio2, userFreeSpendingRatio2;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_personal_budgets);

        //userID = findViewById(R.id.UserID);
        userSalary = findViewById(R.id.userSalary);
        userSavingsRatio = findViewById(R.id.userSavingsRatio);
        userNecessitiesRatio = findViewById(R.id.userNecessitiesRatio);
        userFreeSpendingRatio = findViewById(R.id.userFreeSpendingRatio);

        userSalary2 = findViewById(R.id.userSalary2);
        userSavingsRatio2 = findViewById(R.id.userSavingsRatio2);
        userNecessitiesRatio2 = findViewById(R.id.userNecessitiesRatio2);
        userFreeSpendingRatio2 = findViewById(R.id.userFreeSpendingRatio2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        String docref = "FeWeZ2QE9iD1BgNgFgL9";
        String docref2 = "VvHWx9dYMSYq3sZxKh45";

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


                //userID.setText(documentSnapshot.getString("userID"));
                userSalary.setText(documentSnapshot.getString("userSalary"));
                userSavingsRatio.setText(uSRatio);
                userNecessitiesRatio.setText(uNRatio);
                userFreeSpendingRatio.setText(uFSRatio);




            }


        });

        DocumentReference documentReference2 = fStore.collection("personalBudgets").document(docref2);

        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                //fullname.setText(documentSnapshot.getString("fName"));
                //email.setText(documentSnapshot.getString("email"));


                int uSavingRatio2 = documentSnapshot.getLong("userSavingsRatio").intValue();
                String uSRatio2 = String.valueOf(uSavingRatio2);

                int uNecessitiesRatio2 = documentSnapshot.getLong("userNecessitiesRatio").intValue();
                String uNRatio2 = String.valueOf(uNecessitiesRatio2);

                int uFreeSpendingRatio2 = documentSnapshot.getLong("userFreeSpendingRatio").intValue();
                String uFSRatio2 = String.valueOf(uFreeSpendingRatio2);


                //userID.setText(documentSnapshot.getString("userID"));
                userSalary2.setText(documentSnapshot.getString("userSalary"));
                userSavingsRatio2.setText(uSRatio2);
                userNecessitiesRatio2.setText(uNRatio2);
                userFreeSpendingRatio2.setText(uFSRatio2);




            }


        });

    }
}