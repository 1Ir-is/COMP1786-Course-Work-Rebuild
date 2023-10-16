package com.example.bottomnavigation.model;

public class Hiker {
    private int id;
    private String name;
    private String location;
    private String date;
    private String parkingAvailable;
    private String lengthHike;
    private String difficultyLevel;
    private String description;

    public Hiker(int id, String name, String location, String date, String parkingAvailable, String lengthHike, String difficultyLevel, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.parkingAvailable = parkingAvailable;
        this.lengthHike = lengthHike;
        this.difficultyLevel = difficultyLevel;
        this.description = description;
    }

    // Add getters and setters if needed
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getParkingAvailable() {
        return parkingAvailable;
    }

    public String getLengthHike() {
        return lengthHike;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getDescription() {
        return description;
    }
}
