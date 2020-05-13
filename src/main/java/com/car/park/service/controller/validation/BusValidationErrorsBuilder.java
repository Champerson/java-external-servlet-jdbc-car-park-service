package com.car.park.service.controller.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class BusValidationErrorsBuilder {

    private final static String NUMBER_REGEX = "^[A-Z]{2}\\d{4}[A-Z]{2}$";
    private final static String MODEL_REGEX = "([a-zA-Z])\\w{0,20}";
    private final static String PASSENGERS_CAPACITY_REGEX = "\\d{0,4}";
    private final static String MILEAGE_REGEX = "\\d{0,9}";
    //private final static String COLOUR_EN_REGEX = "([a-zA-Z])-?([a-zA-Z]){0,45}";
    //private final static String COLOUR_UA_REGEX = "([а-яА-Я]]) -? (а-яА-Я){0,45}";
    //private final static String NOTES_EN_REGEX = "([a-zA-Z])\\p{P}?{0,255}";
    //private final static String NOTES_UA_REGEX = "(\\p{L}\\p{P}?){0,255}";

    private final BusValidationErrors busValidationErrors = new BusValidationErrors();
    private final Validator validator = new Validator();


    public BusValidationErrorsBuilder validateNumber(String number) {
        if (isEmpty(number)) {
            busValidationErrors.setNumber("validation.bus.number.empty");
        } else if (!validator.isValidByRegex(number, NUMBER_REGEX)) {
            busValidationErrors.setNumber("validation.bus.number.invalid");
        }
        return this;
    }

    public BusValidationErrorsBuilder validateModel(String model) {
        if (!isEmpty(model) && !validator.isValidByRegex(model, MODEL_REGEX)) {
            busValidationErrors.setModel("validation.bus.model.invalid");
        }
        return this;
    }

    public BusValidationErrorsBuilder validatePassengersCapacity(String passengersCapacity) {
        if (!isEmpty(passengersCapacity) && !validator.isValidByRegex(passengersCapacity, PASSENGERS_CAPACITY_REGEX)) {
            busValidationErrors.setPassengersCapacity("validation.bus.capacity.invalid");
        }
        return this;
    }

    /*public BusValidationErrorsBuilder validateMileage(String mileage) {
        if (!isEmpty(mileage) && !validator.isValidByRegex(mileage, MILEAGE_REGEX)) {
            busValidationErrors.setMileage("validation.bus.mileage.invalid");
        }
        return this;
    }

    public BusValidationErrorsBuilder validateColourEn(String colourEn) {
        if (!isEmpty(colourEn) && !validator.isValidByRegex(colourEn, COLOUR_EN_REGEX)) {
            busValidationErrors.setColourEn("validation.bus.colour.en.invalid");
        }
        return this;
    }

    public BusValidationErrorsBuilder validateColourUa(String colourUa) {
        if (!isEmpty(colourUa) && !validator.isValidByRegex(colourUa, COLOUR_UA_REGEX)) {
            busValidationErrors.setColourUa("validation.bus.colour.ua.invalid");
        }
        return this;
    }

    public BusValidationErrorsBuilder validateNotesEn(String notesEn) {
        if (!isEmpty(notesEn) && !validator.isValidByRegex(notesEn, NOTES_EN_REGEX)) {
            busValidationErrors.setNotesEn("validation.bus.notes.en.invalid");
        }
        return this;
    }

    public BusValidationErrorsBuilder validateNotesUa(String notesUa) {
        if (!isEmpty(notesUa) && !validator.isValidByRegex(notesUa, NOTES_UA_REGEX)) {
            busValidationErrors.setNotesUa("validation.bus.notes.ua.invalid");
        }
        return this;
    }*/

    public BusValidationErrors errors() {
        return busValidationErrors;
    }
}
