package com.car.park.service.controller.validation;

public class RouteValidationDto {

    private String number;
    private String length;
    private String descriptionEn;
    private String descriptionUa;

    private String numberError;
    private String lengthError;
    private String descriptionEnError;
    private String descriptionUaError;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUa() {
        return descriptionUa;
    }

    public void setDescriptionUa(String descriptionUa) {
        this.descriptionUa = descriptionUa;
    }

    public String getNumberError() {
        return numberError;
    }

    public void setNumberError(String numberError) {
        this.numberError = numberError;
    }

    public String getLengthError() {
        return lengthError;
    }

    public void setLengthError(String lengthError) {
        this.lengthError = lengthError;
    }

    public String getDescriptionEnError() {
        return descriptionEnError;
    }

    public void setDescriptionEnError(String descriptionEnError) {
        this.descriptionEnError = descriptionEnError;
    }

    public String getDescriptionUaError() {
        return descriptionUaError;
    }

    public void setDescriptionUaError(String descriptionUaError) {
        this.descriptionUaError = descriptionUaError;
    }

    public boolean validationFailed() {
        return numberError != null
                || lengthError != null
                || descriptionEnError != null
                || descriptionUaError != null;
    }
}
