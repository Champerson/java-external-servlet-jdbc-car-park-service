package com.car.park.service.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Bus {

    private long id;
    private String number;
    private String model;
    private int passengersCapacity;
    private int mileage;
    private LocalDateTime creationTime;
    private Assignment assignment;
    private final Map<String, String> localizedColour = new HashMap<>();
    private final Map<String, String> localizedNotes = new HashMap<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Map<String, String> getLocalizedColour() {
        return localizedColour;
    }

    public Map<String, String> getLocalizedNotes() {
        return localizedNotes;
    }
}
