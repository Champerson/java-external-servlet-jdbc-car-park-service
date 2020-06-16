package com.car.park.service.controller.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class RouteValidationResultBuilder {

    private final static String NUMBER_REGEX = "\\d{1,4}";
    private final static String LENGTH_REGEX = "^[1-9]\\d{0,2}$";
    private final static String DESCRIPTION_EN_REGEX = "([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}";
    private final static String DESCRIPTION_UA_REGEX = "([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}";

    private final RouteValidationDto routeValidationDto = new RouteValidationDto();
    private final Validator validator = new Validator();

    public RouteValidationResultBuilder validateNumber(String number) {
        routeValidationDto.setNumber(number);
        if (isEmpty(number)) {
            routeValidationDto.setNumberError("validation.route.number.empty");
        } else if (!validator.isValidByRegex(number, NUMBER_REGEX)) {
            routeValidationDto.setNumberError("validation.route.number.invalid");
        }
        return this;
    }

    public RouteValidationResultBuilder validateLength(String length) {
        routeValidationDto.setLength(length);
        if (isEmpty(length)) {
            routeValidationDto.setLengthError("validation.route.length.empty");
        } else if (!validator.isValidByRegex(length, LENGTH_REGEX)) {
            routeValidationDto.setLengthError("validation.route.length.invalid");
        }
        return this;
    }

    public RouteValidationResultBuilder validateDescriptionEn(String descriptionEn) {
        routeValidationDto.setDescriptionEn(descriptionEn);
        if (isEmpty(descriptionEn)) {
            routeValidationDto.setDescriptionEnError("validation.route.description.en.empty");
        } else if (!validator.isValidByRegex(descriptionEn, DESCRIPTION_EN_REGEX)) {
            routeValidationDto.setDescriptionEnError("validation.route.description.en.invalid");
        }
        return this;
    }

    public RouteValidationResultBuilder validateDescriptionUa(String descriptionUa) {
        routeValidationDto.setDescriptionUa(descriptionUa);
        if (isEmpty(descriptionUa)) {
            routeValidationDto.setDescriptionUaError("validation.route.description.ua.empty");
        } else if (!validator.isValidByRegex(descriptionUa, DESCRIPTION_UA_REGEX)) {
            routeValidationDto.setDescriptionUaError("validation.route.description.ua.invalid");
        }
        return this;
    }

    public RouteValidationDto errors() {
        return routeValidationDto;
    }
}
