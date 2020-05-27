package com.car.park.service.controller.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class RouteValidationErrorsBuilder {

    private final static String NUMBER_REGEX = "\\d{0,4}";
    private final static String DESCRIPTION_EN_REGEX = "([a-zA-Z\\-\\ \\.\\,\\!\\?\\_]+){0,255}";
    private final static String DESCRIPTION_UA_REGEX = "([\\p{L}\\'\\-\\ \\.\\,\\!\\?\\_]+){0,255}";

    private final RouteValidationErrors routeValidationErrors = new RouteValidationErrors();
    private final Validator validator = new Validator();

    public RouteValidationErrorsBuilder validateNumber(String number) {
        if (isEmpty(number)) {
            routeValidationErrors.setNumber("validation.route.number.empty");
        } else if (!validator.isValidByRegex(number, NUMBER_REGEX)) {
            routeValidationErrors.setNumber("validation.route.number.invalid");
        }
        return this;
    }

    public RouteValidationErrorsBuilder validateLength(String length) {
        if (isEmpty(length) && !validator.isPositiveNumber(length)) {
            routeValidationErrors.setLength("validation.length.invalid");
        }
        return this;
    }

    public RouteValidationErrorsBuilder validateDescriptionEn(String descriptionEn) {
        if (!isEmpty(descriptionEn) && !validator.isValidByRegex(descriptionEn, DESCRIPTION_EN_REGEX)) {
            routeValidationErrors.setDescriptionEn("validation.description.en.invalid");
        }
        return this;
    }

    public RouteValidationErrorsBuilder validateDescriptionUa(String descriptionUa) {
        if (!isEmpty(descriptionUa) && !validator.isValidByRegex(descriptionUa, DESCRIPTION_UA_REGEX)) {
            routeValidationErrors.setDescriptionUa("validation.description.ua.invalid");
        }
        return this;
    }

    public RouteValidationErrors errors() {
        return routeValidationErrors;
    }
}
