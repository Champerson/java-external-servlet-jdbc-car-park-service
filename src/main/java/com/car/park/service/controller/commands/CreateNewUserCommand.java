package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.controller.validation.UserValidationErrors;
import com.car.park.service.controller.validation.UserValidationErrorsBuilder;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.model.UserRole.DRIVER;
import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.now;

public class CreateNewUserCommand implements Command {

    private static final String INDEX_PAGE = "index.jsp";
    private static final String REGISTRATION_PAGE = "registration.jsp";

    private final UserDao userDao;
    private final TransactionManager transactionManager;
    private final PasswordEncoder passwordEncoder;

    public CreateNewUserCommand(UserDao userDao, TransactionManager transactionManager, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        UserValidationErrors userValidationErrors = new UserValidationErrorsBuilder()
                .validateLogin(login)
                .validatePassword(password)
                .validateEmail(email)
                .validatePhone(phone)
                .validateName(name)
                .validateAge(age)
                .errors();

        if (userValidationErrors.isPresent()) {
            request.setAttribute("validationErrors", userValidationErrors);
            return REGISTRATION_PAGE;
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setPhone(phone);
            user.setName(name);
            user.setAge(parseInt(age));
            user.setAccessRole(DRIVER);
            user.setCreationTime(now());
            userDao.create(user);
            transactionManager.commit();
            return INDEX_PAGE;
        }
    }
}
