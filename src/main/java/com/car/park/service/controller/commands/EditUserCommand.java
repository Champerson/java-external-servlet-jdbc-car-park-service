package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.UserValidationDto;
import com.car.park.service.controller.validation.UserValidationResultBuilder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

public class EditUserCommand implements Command {

    private static final String USER_OFFICE_PAGE = "WEB-INF/jsp/user-office-page.jsp";

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

        UserValidationDto userValidationDto = new UserValidationResultBuilder()
                .validateEmail(email)
                .validatePhone(phone)
                .validateName(name)
                .validateAge(age)
                .errors();

        if (userValidationDto.validationFailed()) {
            request.getSession().setAttribute("validationResult", userValidationDto);
        } else {
            user.setEmail(email);
            user.setPhone(phone);
            user.setName(name);
            user.setAge(parseInt(age));
            userDao.update(user);
            transactionManager.commit();
            request.setAttribute("successMessage", "success.user.updated");
            request.getSession().removeAttribute("validationResult");
        }
        return getUserOfficeCommand.execute(request, response);
    }
}
