package com.example.bottomnavigation.activities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.database.DatabaseHelper;
import com.example.bottomnavigation.databinding.ActivityConfirmationBinding;
import com.example.bottomnavigation.fragment.AddFragment;

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
                        saveToDatabase();
                        clearInputFields();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the "Cancel" button click (e.g., go back to the previous screen)
                        finish();
                    }
                })
                .show();

    }

    private void saveToDatabase() {
        String name = getIntent().getStringExtra("name");
        String location = getIntent().getStringExtra("location");
        String date = getIntent().getStringExtra("date");
        String parkingAvailable = getIntent().getStringExtra("parkingAvailable");
        String lengthHike = getIntent().getStringExtra("lengthHike");
        String difficultyLevel = getIntent().getStringExtra("difficultyLevel");
        String description = getIntent().getStringExtra("description");

        long newRowId = insertDataIntoDatabase(name, location, date, parkingAvailable, lengthHike, difficultyLevel, description);

        if (newRowId != -1) {
            // Data inserted successfully
            // Optionally, you can clear input fields or display a success message
            Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
        } else {
            // Handle insertion failure
            // Optionally, you can display an error message
            Toast.makeText(this, "Failed to save data.", Toast.LENGTH_SHORT).show();
            Log.e("DatabaseError", "Failed to insert data into the database");
        }
    }



    // Helper method to insert data into the database
    private long insertDataIntoDatabase(String name, String location, String date, String parkingAvailable, String lengthHike, String difficultyLevel, String description) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_LOCATION, location);
        values.put(DatabaseHelper.COLUMN_DATE, date);
        values.put(DatabaseHelper.COLUMN_PARKING_AVAILABLE, parkingAvailable);
        values.put(DatabaseHelper.COLUMN_LENGTH_HIKE, lengthHike);
        values.put(DatabaseHelper.COLUMN_DIFFICULTY_LEVEL, difficultyLevel);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);

        long newRowId = db.insert(DatabaseHelper.TABLE_NAME, null, values);

        db.close();

        return newRowId;
    }



    private void navigateBackToAddFragment() {
        // Optionally, navigate back to the AddFragment
        Intent intent = new Intent(this, AddFragment.class);
        startActivity(intent);
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
