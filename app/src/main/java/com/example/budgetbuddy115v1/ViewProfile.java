package com.example.budgetbuddy115v1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ViewProfile extends AppCompatActivity {
    TextView fullname, email;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    Button mEditAccount;
    Button mReturnToMenu;
    Button changeProfileImage;

    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        fullname = findViewById(R.id.textViewFullName);
        email = findViewById(R.id.textViewEmail);
        profileImage = findViewById(R.id.profileImage);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fullname.setText(documentSnapshot.getString("fName"));
                email.setText(documentSnapshot.getString("email"));

            }
        });


        mEditAccount = findViewById(R.id.editProfileBtn);

        mEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfile.this, EditProfile.class);
                intent.putExtra("fullName", fullname.getText().toString());
                intent.putExtra("email", email.getText().toString());


                startActivity(intent);
            }
        });


        mReturnToMenu = findViewById(R.id.mainMenuBtn);

        mReturnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
