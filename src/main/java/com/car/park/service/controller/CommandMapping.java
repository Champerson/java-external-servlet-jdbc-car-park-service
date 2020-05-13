package com.car.park.service.controller;

import com.car.park.service.controller.commands.*;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.dao.*;
import com.car.park.service.dao.support.TransactionManager;

public class CommandMapping {

    private static TransactionManager transactionManager = TransactionManager.getInstance();
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static UserDao userDao = daoFactory.createUserDao(transactionManager);
    private static RouteDao routeDao = daoFactory.createRouteDao(transactionManager);
    private static BusDao busDao = daoFactory.createBusDao(transactionManager);
    private static AssignmentDao assignmentDao = daoFactory.createAssignmentDao(transactionManager);
    private static PasswordEncoder passwordEncoder = new PasswordEncoder();

    public enum Commands {

        GET_ALL_USERS(
                new GetAllUsersCommand(userDao)
        ),
        GET_ALL_BUSES(
                new GetAllBusesCommand(busDao)
        ),
        GET_ALL_ROUTES(
                new GetAllRoutesCommand(routeDao)
        ),
        GET_USER_DETAILS(
                new GetUserDetailsCommand(userDao, assignmentDao)
        ),
        GET_USER_OFFICE(
                new GetUserOfficeCommand(userDao, assignmentDao)
        ),
        GET_ROUTE_DETAILS(
                new GetRouteDetailsCommand(routeDao, assignmentDao)
        ),
        GET_UNASSIGNED_DRIVERS(
                new GetUnassignedDriversCommand(userDao)
        ),
        GET_UNASSIGNED_BUSES(
                new GetUnassignedBusesCommand(busDao)
        ),
        REDIRECT(
                new RedirectCommand()
        ),
        AUTHORIZATION(
                new AuthorizationCommand(userDao, passwordEncoder, GET_USER_OFFICE.command)
        ),
        LOGOUT(
                new LogoutCommand()
        ),
        CREATE_USER(
                new CreateNewUserCommand(userDao, transactionManager, passwordEncoder)
        ),
        CREATE_BUS(
                new CreateNewBusCommand(busDao, transactionManager, GET_ALL_BUSES.command)
        ),
        CREATE_ROUTE(
                new CreateNewRouteCommand(routeDao, transactionManager, GET_ALL_ROUTES.command)
        ),
        CREATE_DRIVER_ASSIGNMENT(
                new CreateAssignmentForDriverCommand(userDao, assignmentDao, transactionManager, GET_ROUTE_DETAILS.command)
        ),
        CREATE_BUS_ASSIGNMENT(
                new CreateAssignmentForBusCommand(routeDao, busDao, assignmentDao, transactionManager, GET_ROUTE_DETAILS.command)
        ),
        EDIT_BUS(
                new EditBusCommand(busDao, transactionManager, GET_ALL_BUSES.command)
        ),
        EDIT_USER(
                new EditUserCommand(userDao, transactionManager, GET_USER_OFFICE.command)
        ),
        EDIT_USER_PASSWORD(
                new EditUserPasswordCommand(userDao, transactionManager, passwordEncoder, GET_USER_OFFICE.command)
        ),
        EDIT_USER_ASSIGNMENT_ACCEPT(
                new EditUserAssignmentAcceptCommand(userDao, assignmentDao, transactionManager, GET_USER_OFFICE.command)
        ),
        EDIT_USER_ASSIGNMENT_DELETE(
                new EditUserAssignmentDeleteCommand(userDao, assignmentDao, transactionManager, GET_USER_DETAILS.command)
        ),
        EDIT_USER_ROLE(
                new EditUserRoleCommand(userDao, transactionManager, GET_USER_DETAILS.command)
        ),
        EDIT_ROUTE(
                new EditRouteCommand(routeDao, transactionManager, GET_ROUTE_DETAILS.command)
        ),
        DELETE_BUS(
                new DeleteBusCommand(busDao, assignmentDao, transactionManager, GET_ALL_BUSES.command)
        ),
        DELETE_USER(
                new DeleteUserCommand(userDao, assignmentDao, transactionManager, GET_ALL_USERS.command)
        ),
        DELETE_ASSIGNMENT(
                new DeleteAssignmentCommand(assignmentDao, transactionManager, GET_ROUTE_DETAILS.command)
        ),
        DELETE_ROUTE(
                new DeleteRouteCommand(routeDao, transactionManager, GET_ALL_ROUTES.command)
        );

        Command command;

        Commands(Command command) {
            this.command = command;
        }

        public Command command() {
            return command;
        }
    }
}
