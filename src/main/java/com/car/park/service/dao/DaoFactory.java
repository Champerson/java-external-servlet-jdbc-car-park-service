package com.car.park.service.dao;

import com.car.park.service.dao.jdbc.JdbcDaoFactory;
import com.car.park.service.dao.support.TransactionManager;

public abstract class DaoFactory {

    public abstract AssignmentDao createAssignmentDao(TransactionManager transactionManager);

    public abstract UserDao createUserDao(TransactionManager transactionManager);

    public abstract BusDao createBusDao(TransactionManager transactionManager);

    public abstract RouteDao createRouteDao(TransactionManager transactionManager);

    public static DaoFactory getInstance() {
        return new JdbcDaoFactory();
    }
}
