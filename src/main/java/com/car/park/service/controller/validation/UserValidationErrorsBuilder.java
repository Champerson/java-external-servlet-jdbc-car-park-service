package com.car.park.service.controller.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class UserValidationErrorsBuilder {

    private final static String LOGIN_REGEX = "^[a-zA-Z0-9_-]{1,15}$";
    private final static String PASSWORD_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private final static String EMAIL_REGEX = "^(.+)@(.+)$";
    private final static String PHONE_REGEX = "\\d{10}";
    private final static String AGE_REGEX = "[1-9]{2}";
    private final static String NAME_REGEX = "^[a-zA-Z0-9_-]{1,15}$";

    private final UserValidationErrors userValidationErrors = new UserValidationErrors();
    private final Validator validator = new Validator();

    public UserValidationErrorsBuilder validateLogin(String login) {
        if (isEmpty(login)) {
            userValidationErrors.setLogin("validation.login.empty");
        } else if (!validator.isValidByRegex(login, LOGIN_REGEX)) {
            userValidationErrors.setLogin("validation.login.invalid");
        }
        return this;
    }

    public UserValidationErrorsBuilder validatePassword(String password) {
        if (isEmpty(password)) {
            userValidationErrors.setPassword("validation.password.empty");
        } else if (!validator.isValidByRegex(password, PASSWORD_REGEX)) {
            userValidationErrors.setPassword("validation.password.invalid");
        }
        return this;
    }

    public UserValidationErrorsBuilder validateEmail(String email) {
        if (isEmpty(email)) {
            userValidationErrors.setEmail("validation.email.empty");
        } else if (!validator.isValidByRegex(email, EMAIL_REGEX)) {
            userValidationErrors.setEmail("validation.email.invalid");
        }
        return this;
    }

    public UserValidationErrorsBuilder validatePhone(String phone) {
        if (isEmpty(phone)) {
            userValidationErrors.setPhone("validation.phone.empty");
        } else if (!validator.isValidByRegex(phone, PHONE_REGEX)) {
            userValidationErrors.setPhone("validation.phone.invalid");
        }
        return this;
    }

    public UserValidationErrorsBuilder validateName(String name) {
        if (!isEmpty(name) && !validator.isValidByRegex(name, NAME_REGEX)) {
            userValidationErrors.setName("validation.name.invalid");
        }
        return this;
    }

    public UserValidationErrorsBuilder validateAge(String age) {
        if (!isEmpty(age) && !validator.isValidByRegex(age, AGE_REGEX)) {
            userValidationErrors.setAge("validation.age.invalid");
        }
        return this;
    }

    public UserValidationErrors errors() {
        return userValidationErrors;
    }
}
