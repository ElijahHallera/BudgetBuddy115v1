package com.example.budgetbuddy115v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

        private Button Logout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_page);

            Logout = findViewById(R.id.Logout_button);

            //Pulls the Logged in User from the Log in Page.

            Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomePage.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
}