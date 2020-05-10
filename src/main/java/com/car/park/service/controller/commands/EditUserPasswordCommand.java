package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.GET_USER_OFFICE;

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
        String oldPassword = passwordEncoder.encode(request.getParameter("oldPassword"));
        String newPassword = passwordEncoder.encode(request.getParameter("newPassword"));

        long sessionUserId = (long) request.getSession().getAttribute("userId");
        User user = userDao.read(sessionUserId);
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userDao.update(user);
            transactionManager.commit();
        }

        return getUserOfficeCommand.execute(request, response);
    }
}