package com.car.park.service.controller.validation;

public class RouteValidationErrors {

    String number;
    String length;
    String descriptionEn;
    String descriptionUa;

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

    public boolean isPresent() {
        return number != null
                || length != null
                || descriptionEn != null
                || descriptionUa != null;
    }
}
