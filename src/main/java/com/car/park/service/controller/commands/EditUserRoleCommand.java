package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;
import com.car.park.service.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.GET_USER_DETAILS;
import static java.lang.Long.parseLong;

public class EditUserRoleCommand implements Command {

    private final UserDao userDao;
    private final TransactionManager transactionManager;
    private final Command getUserDetailsCommand;

    public EditUserRoleCommand(UserDao userDao, TransactionManager transactionManager, Command getUserDetailsCommand) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
        this.getUserDetailsCommand = getUserDetailsCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userRole = request.getParameter("accessRole");

        long userId = parseLong(request.getParameter("userId"));
        User user = userDao.read(userId);
        user.setAccessRole(UserRole.valueOf(userRole.toUpperCase()));
        userDao.update(user);
        transactionManager.commit();

        return getUserDetailsCommand.execute(request, response);
    }
}
