package com.example.bottomnavigation.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bottomnavigation.model.Hiker;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hiker.db";

    // Define your table and column names
    public static final String TABLE_NAME = "person_hiker";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PARKING_AVAILABLE = "parking_available";
    public static final String COLUMN_LENGTH_HIKE = "length_hike";
    public static final String COLUMN_DIFFICULTY_LEVEL = "difficulty_level";
    public static final String COLUMN_DESCRIPTION = "description";

    private SQLiteDatabase database;

    private static final String TABLE_CREATE = String.format(
            // The SQL query to create the table
            // %s expects a value of any type
            // The query will be:
            // CREATE TABLE persons(
            //      person_id INTEGER PRIMARY KEY AUTOINCREMENT,
            //      name TEXT,
            //      dob TEXT,
            //      email TEXT
            // )
            "CREATE TABLE %s (" +
                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            TABLE_NAME, COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION,
            COLUMN_DATE, COLUMN_PARKING_AVAILABLE,
            COLUMN_LENGTH_HIKE, COLUMN_DIFFICULTY_LEVEL,
            COLUMN_DESCRIPTION
    );

    // The constructor makes a call to the method in the super class, passing the database name
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    // Overriding the onCreate() method which generates the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    // This method upgrades the database if the version number changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        Log.v(this.getClass().getName(), TABLE_NAME +
                "database upgrade to version" + newVersion + " - old data lost"
        );
        onCreate(db);
    }

    // Returns the automatically generated primary key
    public long insertDetails(String name, String location, String date, String parking_available, String length_hike, String difficulty_level, String description) {
        // ContentValues represents a single table row as a key/value map
        ContentValues rowValues = new ContentValues();

        rowValues.put(COLUMN_NAME, name);
        rowValues.put(COLUMN_LOCATION, location);
        rowValues.put(COLUMN_DATE, date);
        rowValues.put(COLUMN_PARKING_AVAILABLE, parking_available);
        rowValues.put(COLUMN_LENGTH_HIKE, length_hike);
        rowValues.put(COLUMN_DIFFICULTY_LEVEL, difficulty_level);
        rowValues.put(COLUMN_DESCRIPTION, description);

        return database.insertOrThrow(
                TABLE_NAME,
                // nullColumnHack specifies a column that will be set to null if the ContentValues argument contains no data
                null,
                // Inserts ContentValues into the database
                rowValues
        );
    }
    @SuppressLint("Range")
    public List<Hiker> getDetails() {
        List<Hiker> hikers = new ArrayList<>(); // Create a list to store Hiker objects

        // The query to retrieve hiker details
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor results = database.rawQuery(query, null);

        // Iterate through the result set
        while (results.moveToNext()) {

            int id = results.getInt(results.getColumnIndex(COLUMN_ID));
            String name = results.getString(results.getColumnIndex(COLUMN_NAME));
            String location = results.getString(results.getColumnIndex(COLUMN_LOCATION));
            String date = results.getString(results.getColumnIndex(COLUMN_DATE));
            String parkingAvailable = results.getString(results.getColumnIndex(COLUMN_PARKING_AVAILABLE));
            String lengthHike = results.getString(results.getColumnIndex(COLUMN_LENGTH_HIKE));
            String difficultyLevel = results.getString(results.getColumnIndex(COLUMN_DIFFICULTY_LEVEL));
            String description = results.getString(results.getColumnIndex(COLUMN_DESCRIPTION));


            // Create a new Hiker object and add it to the list
            Hiker hiker = new Hiker(id, name, location, date, parkingAvailable, lengthHike, difficultyLevel, description);
            hikers.add(hiker);
        }

        // Close the cursor
        results.close();

        return hikers;
    }
}
