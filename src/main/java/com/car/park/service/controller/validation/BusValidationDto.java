package com.car.park.service.controller.validation;

public class BusValidationDto {

    private String number;
    private String model;
    private String passengersCapacity;
    private String mileage;
    private String colourEn;
    private String colourUa;
    private String notesEn;
    private String notesUa;

    private String numberError;
    private String modelError;
    private String passengersCapacityError;
    private String mileageError;
    private String colourEnError;
    private String colourUaError;
    private String notesEnError;
    private String notesUaError;

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

    public String getNumberError() {
        return numberError;
    }

    public void setNumberError(String numberError) {
        this.numberError = numberError;
    }

    public String getModelError() {
        return modelError;
    }

    public void setModelError(String modelError) {
        this.modelError = modelError;
    }

    public String getPassengersCapacityError() {
        return passengersCapacityError;
    }

    public void setPassengersCapacityError(String passengersCapacityError) {
        this.passengersCapacityError = passengersCapacityError;
    }

    public String getMileageError() {
        return mileageError;
    }

    public void setMileageError(String mileageError) {
        this.mileageError = mileageError;
    }

    public String getColourEnError() {
        return colourEnError;
    }

    public void setColourEnError(String colourEnError) {
        this.colourEnError = colourEnError;
    }

    public String getColourUaError() {
        return colourUaError;
    }

    public void setColourUaError(String colourUaError) {
        this.colourUaError = colourUaError;
    }

    public String getNotesEnError() {
        return notesEnError;
    }

    public void setNotesEnError(String notesEnError) {
        this.notesEnError = notesEnError;
    }

    public String getNotesUaError() {
        return notesUaError;
    }

    public void setNotesUaError(String notesUaError) {
        this.notesUaError = notesUaError;
    }

    public boolean validationFailed() {
        return numberError != null
                || modelError != null
                || passengersCapacityError != null
                || mileageError != null
                || colourEnError != null
                || colourUaError != null
                || notesEnError != null
                || notesUaError != null;
    }
}
