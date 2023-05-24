package com.application.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button lostAndFoundBtn;
    Button newAdvertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise the database
        DatabaseHelper.init(this);

        lostAndFoundBtn = findViewById(R.id.lostAndFoundBtn);
        newAdvertBtn = findViewById(R.id.newAdvertBtn);

        lostAndFoundBtn.setOnClickListener(v -> {
            // Go to lost and found activity
            openLostAndFoundActivity();
        });

        newAdvertBtn.setOnClickListener(v -> {
            // Go to new advert activity
            openNewAdvertActivity();
        });
    }

    public void openNewAdvertActivity() {
        Intent intent = new Intent(this, NewAdvertActivity.class);
        startActivity(intent);
    }

    public void openLostAndFoundActivity() {
        Intent intent = new Intent(this, LostAndFoundItemsActivity.class);
        startActivity(intent);
    }
}