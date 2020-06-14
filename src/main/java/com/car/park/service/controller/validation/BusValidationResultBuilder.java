package com.car.park.service.controller.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class BusValidationResultBuilder {

    private final static String NUMBER_REGEX = "^[A-Z]{2}\\d{4}[A-Z]{2}$";
    private final static String MODEL_REGEX = "^([a-zA-Z])\\w{0,20}$";
    private final static String PASSENGERS_CAPACITY_REGEX = "^\\d{0,4}$";
    private final static String MILEAGE_REGEX = "\\d{0,9}";
    private final static String COLOUR_EN_REGEX = "([a-zA-Z\\-]+){0,45}";
    private final static String COLOUR_UA_REGEX = "^([\\p{L}\\'\\-]+){0,45}";
    private final static String NOTES_EN_REGEX = "([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}";
    private final static String NOTES_UA_REGEX = "([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}";

    private final BusValidationDto busValidationDto = new BusValidationDto();
    private final Validator validator = new Validator();

    public BusValidationResultBuilder validateNumber(String number) {
        busValidationDto.setNumber(number);
        if (isEmpty(number)) {
            busValidationDto.setNumberError("validation.bus.number.empty");
        } else if (!validator.isValidByRegex(number, NUMBER_REGEX)) {
            busValidationDto.setNumberError("validation.bus.number.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validateModel(String model) {
        busValidationDto.setModel(model);
        if (!isEmpty(model) && !validator.isValidByRegex(model, MODEL_REGEX)) {
            busValidationDto.setModelError("validation.bus.model.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validatePassengersCapacity(String passengersCapacity) {
        busValidationDto.setPassengersCapacity(passengersCapacity);
        if (isEmpty(passengersCapacity)) {
            busValidationDto.setPassengersCapacityError("validation.bus.capacity.empty");
        } else if (!validator.isValidByRegex(passengersCapacity, PASSENGERS_CAPACITY_REGEX)) {
            busValidationDto.setPassengersCapacityError("validation.bus.capacity.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validateMileage(String mileage) {
        busValidationDto.setMileage(mileage);
        if (isEmpty(mileage)) {
            busValidationDto.setMileageError("validation.bus.mileage.empty");
        } else if (!validator.isValidByRegex(mileage, MILEAGE_REGEX)) {
            busValidationDto.setMileageError("validation.bus.mileage.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validateColourEn(String colourEn) {
        busValidationDto.setColourEn(colourEn);
        if (!isEmpty(colourEn) && !validator.isValidByRegex(colourEn, COLOUR_EN_REGEX)) {
            busValidationDto.setColourEnError("validation.bus.colour.en.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validateColourUa(String colourUa) {
        busValidationDto.setColourUa(colourUa);
        if (!isEmpty(colourUa) && !validator.isValidByRegex(colourUa, COLOUR_UA_REGEX)) {
            busValidationDto.setColourUaError("validation.bus.colour.ua.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validateNotesEn(String notesEn) {
        busValidationDto.setNotesEn(notesEn);
        if (!isEmpty(notesEn) && !validator.isValidByRegex(notesEn, NOTES_EN_REGEX)) {
            busValidationDto.setNotesEnError("validation.bus.notes.en.invalid");
        }
        return this;
    }

    public BusValidationResultBuilder validateNotesUa(String notesUa) {
        busValidationDto.setNotesUa(notesUa);
        if (!isEmpty(notesUa) && !validator.isValidByRegex(notesUa, NOTES_UA_REGEX)) {
            busValidationDto.setNotesUaError("validation.bus.notes.ua.invalid");
        }
        return this;
    }

    public BusValidationDto errors() {
        return busValidationDto;
    }
}
