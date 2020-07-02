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

import static com.car.park.service.model.UserRole.ROLE_DRIVER;
import static java.lang.Integer.parseInt;
import static java.time.LocalDateTime.now;

/**
 * This command is responsible for User creation. It handles incoming parameters and validate them, so
 * in case of valid values creates new User and returns to INDEX_PAGE, otherwise it sets UserValidationDto
 * to request and returns to NEW_ROUTE_PAGE
 */
public class CreateNewUserCommand implements Command {

    private static final String INDEX_PAGE = "index.jsp";
    private static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration-page.jsp";

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

        UserValidationDto userValidationDto = new UserValidationResultBuilder()
                .validateLogin(login)
                .validatePassword(password)
                .validateEmail(email)
                .validatePhone(phone)
                .validateName(name)
                .validateAge(age)
                .errors();
        checkUserLoginExist(userValidationDto);

        if (userValidationDto.validationFailed()) {
            request.getSession().setAttribute("validationResult", userValidationDto);
            return REGISTRATION_PAGE;
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setPhone(phone);
            user.setName(name);
            user.setAge(parseInt(age));
            user.setAccessRole(ROLE_DRIVER);
            user.setCreationTime(now());
            userDao.create(user);
            transactionManager.commit();
            request.getSession().removeAttribute("validationResult");
            request.setAttribute("successMessage", "success.registration.completed");
            return INDEX_PAGE;
        }
    }

    private void checkUserLoginExist(UserValidationDto userValidationDto) {
        if (userValidationDto.getLoginError() == null) {
            if (userDao.read(userValidationDto.getLogin()) != null) {
                userValidationDto.setLoginError("validation.user.login.exist");
            }
        }
    }
}
