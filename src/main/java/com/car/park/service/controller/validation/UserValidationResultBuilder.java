package com.car.park.service.controller.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class UserValidationResultBuilder {

    private final static String LOGIN_REGEX = "^[a-zA-Z0-9_-]{1,15}$";
    private final static String PASSWORD_REGEX = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private final static String EMAIL_REGEX = "^(.+)@(.+)$";
    private final static String PHONE_REGEX = "\\d{10}";
    private final static String AGE_REGEX = "[1-9]{0,2}";
    private final static String NAME_REGEX = "^[a-zA-Z0-9_-]{1,15}$";

    private final UserValidationDto userValidationDto = new UserValidationDto();
    private final Validator validator = new Validator();

    public UserValidationResultBuilder validateLogin(String login) {
        userValidationDto.setLogin(login);
        if (isEmpty(login)) {
            userValidationDto.setLoginError("validation.user.login.empty");
        } else if (!validator.isValidByRegex(login, LOGIN_REGEX)) {
            userValidationDto.setLoginError("validation.user.login.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validatePassword(String password) {
        userValidationDto.setPassword(password);
        if (isEmpty(password)) {
            userValidationDto.setPasswordError("validation.user.password.empty");
        } else if (!validator.isValidByRegex(password, PASSWORD_REGEX)) {
            userValidationDto.setPasswordError("validation.user.password.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validateOldPassword(String password) {
        userValidationDto.setOldPassword(password);
        if (isEmpty(password)) {
            userValidationDto.setOldPasswordError("validation.user.password.old.empty");
        } else if (!validator.isValidByRegex(password, PASSWORD_REGEX)) {
            userValidationDto.setOldPasswordError("validation.user.password.old.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validateNewPassword(String password) {
        userValidationDto.setNewPassword(password);
        if (isEmpty(password)) {
            userValidationDto.setNewPasswordError("validation.user.password.new.empty");
        } else if (!validator.isValidByRegex(password, PASSWORD_REGEX)) {
            userValidationDto.setNewPasswordError("validation.user.password.new.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validateEmail(String email) {
        userValidationDto.setEmail(email);
        if (isEmpty(email)) {
            userValidationDto.setEmailError("validation.user.email.empty");
        } else if (!validator.isValidByRegex(email, EMAIL_REGEX)) {
            userValidationDto.setEmailError("validation.user.email.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validatePhone(String phone) {
        userValidationDto.setPhone(phone);
        if (isEmpty(phone)) {
            userValidationDto.setPhoneError("validation.user.phone.empty");
        } else if (!validator.isValidByRegex(phone, PHONE_REGEX)) {
            userValidationDto.setPhoneError("validation.user.phone.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validateName(String name) {
        userValidationDto.setName(name);
        if (!isEmpty(name) && !validator.isValidByRegex(name, NAME_REGEX)) {
            userValidationDto.setNameError("validation.user.name.invalid");
        }
        return this;
    }

    public UserValidationResultBuilder validateAge(String age) {
        userValidationDto.setAge(age);
        if (isEmpty(age)) {
            userValidationDto.setAgeError("validation.user.age.empty");
        } else if (!validator.isValidByRegex(age, AGE_REGEX)) {
            userValidationDto.setAgeError("validation.user.age.invalid");
        }
        return this;
    }

    public UserValidationDto errors() {
        return userValidationDto;
    }
}
