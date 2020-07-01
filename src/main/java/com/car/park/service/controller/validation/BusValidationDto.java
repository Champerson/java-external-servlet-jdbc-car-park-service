package com.car.park.service.controller.validation;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusValidationDto that = (BusValidationDto) o;
        return Objects.equals(number, that.number) &&
                Objects.equals(model, that.model) &&
                Objects.equals(passengersCapacity, that.passengersCapacity) &&
                Objects.equals(mileage, that.mileage) &&
                Objects.equals(colourEn, that.colourEn) &&
                Objects.equals(colourUa, that.colourUa) &&
                Objects.equals(notesEn, that.notesEn) &&
                Objects.equals(notesUa, that.notesUa) &&
                Objects.equals(numberError, that.numberError) &&
                Objects.equals(modelError, that.modelError) &&
                Objects.equals(passengersCapacityError, that.passengersCapacityError) &&
                Objects.equals(mileageError, that.mileageError) &&
                Objects.equals(colourEnError, that.colourEnError) &&
                Objects.equals(colourUaError, that.colourUaError) &&
                Objects.equals(notesEnError, that.notesEnError) &&
                Objects.equals(notesUaError, that.notesUaError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, passengersCapacity, mileage, colourEn, colourUa, notesEn, notesUa, numberError, modelError, passengersCapacityError, mileageError, colourEnError, colourUaError, notesEnError, notesUaError);
    }
}
