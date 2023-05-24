package com.application.lostandfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class NewAdvertActivity extends AppCompatActivity {

    RadioButton lostRadioButton, foundRadioButton;
    EditText editTextName, editTextPhone, editTextDescription, editTextDate, editTextLocation;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_advert);

        lostRadioButton = findViewById(R.id.lostRadioButton);
        foundRadioButton = findViewById(R.id.foundRadioButton);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLocation = findViewById(R.id.editTextLocation);
        saveButton = findViewById(R.id.saveButton);

        lostRadioButton.setOnClickListener(v -> {
            if (lostRadioButton.isChecked()) {
                foundRadioButton.setChecked(false);
            }
        });

        foundRadioButton.setOnClickListener(v -> {
            if (foundRadioButton.isChecked()) {
                lostRadioButton.setChecked(false);
            }
        });

        saveButton.setOnClickListener(v -> {
            if (validateEntries()) {
                // Save advert
                if (DatabaseHelper.getInstance().insertItem(
                        editTextName.getText().toString(),
                        lostRadioButton.isChecked() ? "Lost" : "Found",
                        editTextPhone.getText().toString(),
                        editTextDescription.getText().toString(),
                        editTextLocation.getText().toString(),
                        editTextDate.getText().toString()
                    )) {
                    openMainActivity();
                } else {
                    // Show error message
                    Toast.makeText(this, "Error saving advert", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    Boolean validateEntries() {
        if (editTextName.getText().toString().isEmpty()) {
            editTextName.setError("Please enter your name");
            return false;
        }
        if (editTextPhone.getText().toString().isEmpty()) {
            editTextPhone.setError("Please enter your phone number");
            return false;
        }
        if (editTextDescription.getText().toString().isEmpty()) {
            editTextDescription.setError("Please enter a description");
            return false;
        }
        if (editTextDate.getText().toString().isEmpty()) {
            editTextDate.setError("Please enter a date");
            return false;
        }
        if (editTextLocation.getText().toString().isEmpty()) {
            editTextLocation.setError("Please enter a location");
            return false;
        }
        return true;
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}