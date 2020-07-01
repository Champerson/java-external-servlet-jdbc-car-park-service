package com.car.park.service.controller.validation;

import java.util.Objects;

public class UserValidationDto {

    private String login;
    private String password;
    private String oldPassword;
    private String newPassword;
    private String email;
    private String phone;
    private String name;
    private String age;

    private String loginError;
    private String passwordError;
    private String oldPasswordError;
    private String newPasswordError;
    private String emailError;
    private String phoneError;
    private String nameError;
    private String ageError;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPasswordError() {
        return oldPasswordError;
    }

    public void setOldPasswordError(String oldPasswordError) {
        this.oldPasswordError = oldPasswordError;
    }

    public String getNewPasswordError() {
        return newPasswordError;
    }

    public void setNewPasswordError(String newPasswordError) {
        this.newPasswordError = newPasswordError;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getAgeError() {
        return ageError;
    }

    public void setAgeError(String ageError) {
        this.ageError = ageError;
    }

    public boolean validationFailed() {
        return loginError != null
                || passwordError != null
                || emailError != null
                || phoneError != null
                || nameError != null
                || ageError != null
                || oldPasswordError != null
                || newPasswordError != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserValidationDto that = (UserValidationDto) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(oldPassword, that.oldPassword) &&
                Objects.equals(newPassword, that.newPassword) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(name, that.name) &&
                Objects.equals(age, that.age) &&
                Objects.equals(loginError, that.loginError) &&
                Objects.equals(passwordError, that.passwordError) &&
                Objects.equals(oldPasswordError, that.oldPasswordError) &&
                Objects.equals(newPasswordError, that.newPasswordError) &&
                Objects.equals(emailError, that.emailError) &&
                Objects.equals(phoneError, that.phoneError) &&
                Objects.equals(nameError, that.nameError) &&
                Objects.equals(ageError, that.ageError);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, oldPassword, newPassword, email, phone, name, age, loginError, passwordError, oldPasswordError, newPasswordError, emailError, phoneError, nameError, ageError);
    }
}
