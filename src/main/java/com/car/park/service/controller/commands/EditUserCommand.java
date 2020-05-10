package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.GET_USER_OFFICE;
import static java.lang.Integer.parseInt;

public class EditUserCommand implements Command {

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
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setName(request.getParameter("name"));
        user.setAge(parseInt(request.getParameter("age")));
        userDao.update(user);
        transactionManager.commit();

        return getUserOfficeCommand.execute(request, response);
    }
}
