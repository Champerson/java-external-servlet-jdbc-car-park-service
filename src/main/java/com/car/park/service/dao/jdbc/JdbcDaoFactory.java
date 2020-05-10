package com.car.park.service.dao.jdbc;

import com.car.park.service.dao.*;
import com.car.park.service.dao.support.TransactionManager;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public AssignmentDao createAssignmentDao(TransactionManager transactionManager) {
        return new JdbcAssignmentDao(transactionManager);
    }

    @Override
    public UserDao createUserDao(TransactionManager transactionManager) {
        return new JdbcUserDao(transactionManager);
    }

    @Override
    public BusDao createBusDao(TransactionManager transactionManager) {
        return new JdbcBusDao(transactionManager);
    }

    @Override
    public RouteDao createRouteDao(TransactionManager transactionManager) {
        return new JdbcRouteDao(transactionManager);
    }
}
