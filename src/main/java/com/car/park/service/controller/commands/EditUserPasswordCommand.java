package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.controller.validation.UserValidationDto;
import com.car.park.service.controller.validation.UserValidationResultBuilder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditUserPasswordCommand implements Command {

    private final UserDao userDao;
    private final TransactionManager transactionManager;
    private final PasswordEncoder passwordEncoder;
    private final Command getUserOfficeCommand;

    public EditUserPasswordCommand(
            UserDao userDao, TransactionManager transactionManager,
            PasswordEncoder passwordEncoder, Command getUserOfficeCommand
    ) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
        this.passwordEncoder = passwordEncoder;
        this.getUserOfficeCommand = getUserOfficeCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        User user = getUserFromSession(request);

        UserValidationDto userValidationDto = new UserValidationResultBuilder()
                .validateOldPassword(oldPassword)
                .validateNewPassword(newPassword)
                .errors();
        checkOldPasswordMatches(userValidationDto, user);

        if (userValidationDto.validationFailed()) {
            request.getSession().setAttribute("validationResult", userValidationDto);
        } else {
            user.setPassword(passwordEncoder.encode(newPassword));
            userDao.update(user);
            transactionManager.commit();
            request.getSession().removeAttribute("validationResult");
            request.setAttribute("successMessage", "success.user.password.changed");
        }
        return getUserOfficeCommand.execute(request, response);
    }

    private User getUserFromSession(HttpServletRequest request) {
        long sessionUserId = (long) request.getSession().getAttribute("userId");
        return userDao.read(sessionUserId);
    }

    private void checkOldPasswordMatches(UserValidationDto userValidationDto, User user) {
        if (!passwordEncoder.encode(userValidationDto.getOldPassword()).equals(user.getPassword())) {
            userValidationDto.setOldPasswordError("validation.user.password.old.incorrect");
        }
    }
}