package com.car.park.service.controller.validation;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

public class Validator {

    private final static String POSITIVE_NUMBER_REGEX = "^[0-9]{1,20}$";//from 1 to endless
    private final static String ALPHABET_REGEX = "^[a-zA-Z]{1,20}$";//Green

    boolean isValidByRegex(String compareString, String regex) {
        Pattern pattern = compile(regex);
        return !isEmpty(compareString) && pattern.matcher(compareString).matches();
    }

    boolean isPositiveNumber(String value) {
        return isValidByRegex(value, POSITIVE_NUMBER_REGEX);
    }

    boolean validateAlphabetOnly(String value) {
        return isValidByRegex(value, ALPHABET_REGEX);
    }
}
