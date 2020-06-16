package com.car.park.service.controller.validation;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

class Validator {

    boolean isValidByRegex(String compareString, String regex) {
        Pattern pattern = compile(regex);
        return !isEmpty(compareString) && pattern.matcher(compareString).matches();
    }
}
