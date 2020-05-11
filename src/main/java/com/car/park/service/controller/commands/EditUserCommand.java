package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.UserValidationErrors;
import com.car.park.service.controller.validation.UserValidationErrorsBuilder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

public class EditUserCommand implements Command {

    private static final String USER_OFFICE_PAGE = "WEB-INF/user-office.jsp";

    private final UserDao userDao;
    private final TransactionManager transactionManager;
    private final Command getUserOfficeCommand;

    public EditUserCommand(UserDao userDao, TransactionManager transactionManager, Command getUserOfficeCommand) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
        this.getUserOfficeCommand = getUserOfficeCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long sessionUserId = (long) request.getSession().getAttribute("userId");
        User user = userDao.read(sessionUserId);

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        UserValidationErrors userValidationErrors = new UserValidationErrorsBuilder()
                .validateEmail(email)
                .validatePhone(phone)
                .validateName(name)
                .validateAge(age)
                .errors();

        if (userValidationErrors.isPresent()) {
            request.setAttribute("validationErrors", userValidationErrors);
            return USER_OFFICE_PAGE;
        } else {
            user.setEmail(email);
            user.setPhone(phone);
            user.setName(name);
            user.setAge(parseInt(age));
            userDao.update(user);
            transactionManager.commit();

            return getUserOfficeCommand.execute(request, response);
        }
    }
}
