package com.example.bottomnavigation.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.ActivityConfirmationBinding;

public class ConfirmationActivity extends AppCompatActivity {

    private ActivityConfirmationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get data from the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String location = intent.getStringExtra("location");
        String date = intent.getStringExtra("date");
        String parkingAvailable = intent.getStringExtra("parkingAvailable");
        String lengthHike = intent.getStringExtra("lengthHike");
        String difficultyLevel = intent.getStringExtra("difficultyLevel");
        String description = intent.getStringExtra("description");

        // Create a confirmation message
        String confirmationMessage = "Please review your information:\n\n" +
                "Name: " + name + "\n" +
                "Location: " + location + "\n" +
                "Date: " + date + "\n" +
                "Parking Available: " + parkingAvailable + "\n" +
                "Length of Hike: " + lengthHike + "\n" +
                "Difficulty Level: " + difficultyLevel + "\n" +
                "Description: " + description;

        // Inflate the custom AlertDialog layout
        View customAlertDialogView = LayoutInflater.from(this).inflate(R.layout.activity_confirmation, null);

        // Find the TextViews in the custom layout
        TextView titleTextView = customAlertDialogView.findViewById(R.id.alertDialogTitle);
        TextView messageTextView = customAlertDialogView.findViewById(android.R.id.message);

        // Set the title and message
        titleTextView.setText("Confirmation");
        messageTextView.setText(confirmationMessage);

        // Create and show the AlertDialog with the custom layout
        new AlertDialog.Builder(this)
                .setView(customAlertDialogView) // Set the custom layout
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the "Yes" button click (e.g., save to the database)
                        // You can implement the database saving logic here
                        clearInputFields();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the "Cancel" button click (e.g., go back to the previous screen)
                        finish(); // Close the activity
                    }
                })
                .show();

    }

    private void clearInputFields() {
        // Clear your EditText and other input fields here
        EditText nameHikeText = findViewById(R.id.nameHikeText);
        EditText locationText = findViewById(R.id.locationText);
        EditText dateHikeText = findViewById(R.id.dateHikeText);
        EditText lengthHikeText = findViewById(R.id.lengthHikeText);
        EditText descriptionHikeText = findViewById(R.id.descriptionHikeText);

        nameHikeText.setText("");
        locationText.setText("");
        dateHikeText.setText("");
        lengthHikeText.setText("");
        descriptionHikeText.setText("");
    }
}
