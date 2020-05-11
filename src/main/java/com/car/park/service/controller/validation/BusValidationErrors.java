package com.car.park.service.controller.validation;

public class BusValidationErrors {

    private String number;
    private String model;
    private String passengersCapacity;
    private String mileage;
    private String colourEn;
    private String colourUa;
    private String notesEn;
    private String notesUa;

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

    public String getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(String passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getColourEn() {
        return colourEn;
    }

    public void setColourEn(String colourEn) {
        this.colourEn = colourEn;
    }

    public String getColourUa() {
        return colourUa;
    }

    public void setColourUa(String colourUa) {
        this.colourUa = colourUa;
    }

    public String getNotesEn() {
        return notesEn;
    }

    public void setNotesEn(String notesEn) {
        this.notesEn = notesEn;
    }

    public String getNotesUa() {
        return notesUa;
    }

    public void setNotesUa(String notesUa) {
        this.notesUa = notesUa;
    }

    public boolean isPresent() {
        return number != null
                || model != null
                || passengersCapacity != null
                || mileage != null
                || colourEn != null
                || colourUa != null
                || notesEn != null
                || notesUa != null;
    }
}
