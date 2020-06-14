package com.car.park.service.dao.jdbc;

import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.Bus;
import com.car.park.service.model.Route;
import com.car.park.service.model.User;

import java.sql.*;
import java.util.List;

import static java.lang.String.format;

public class JdbcAssignmentDao extends AbstractJdbcDao<Assignment> implements AssignmentDao {

    private static final String INSERT_REQUEST = "INSERT INTO assignments " +
            "(assignment_accepted, assignment_creation_time, assignment_start_date, assignment_route_id, assignment_bus_id, assignment_user_id) " +
            "VALUES (?, NOW(), ?, ?, ?, ?)";
    private static final String UPDATE_REQUEST = "UPDATE assignments SET " +
            "assignment_accepted = ?, assignment_start_date = ?, assignment_route_id = ?, assignment_bus_id = ?, assignment_user_id = ? " +
            "WHERE assignment_id = ?";
    private static final String JOIN_ASSIGNMENTS_BUSES_USERS_ROUTES = "SELECT * FROM assignments " +
            "LEFT JOIN users ON assignments.assignment_user_id = users.user_id " +
            "LEFT JOIN buses ON assignments.assignment_bus_id = buses.bus_id " +
            "LEFT JOIN routes ON assignments.assignment_route_id = routes.route_id";
    private static final String SELECT_BY_ID_REQUEST = JOIN_ASSIGNMENTS_BUSES_USERS_ROUTES + " WHERE assignments.assignment_id = %s";
    private static final String SELECT_BY_ROUTE_ID_REQUEST = JOIN_ASSIGNMENTS_BUSES_USERS_ROUTES + " WHERE routes.route_id = %s";
    private static final String SELECT_BY_BUS_ID_REQUEST = JOIN_ASSIGNMENTS_BUSES_USERS_ROUTES + " WHERE buses.bus_id = %s";
    private static final String SELECT_BY_DRIVER_ID_REQUEST = JOIN_ASSIGNMENTS_BUSES_USERS_ROUTES + " WHERE users.user_id = %s";
    private static final String DELETE_REQUEST = "DELETE FROM assignments WHERE assignment_id = %s";

    private final TransactionManager transactionManager;

    JdbcAssignmentDao(TransactionManager transactionManager) {
        super(transactionManager);
        this.transactionManager = transactionManager;
    }

    @Override
    public void create(Assignment assignment) {
        insert(INSERT_REQUEST, assignment);
    }

    @Override
    public Assignment read(long assignmentId) {
        return selectSingleElement(format(SELECT_BY_ID_REQUEST, assignmentId));
    }

    @Override
    public Assignment readByBusId(long busId) {
        return selectSingleElement(format(SELECT_BY_BUS_ID_REQUEST, busId));
    }

    @Override
    public Assignment readByDriverId(long driverId) {
        return selectSingleElement(format(SELECT_BY_DRIVER_ID_REQUEST, driverId));
    }

    @Override
    public List<Assignment> readByRouteId(long routeId) {
        return selectMultipleElements(format(SELECT_BY_ROUTE_ID_REQUEST, routeId));
    }

    @Override
    public void update(Assignment assignment) {
        update(UPDATE_REQUEST, assignment, assignment.getId());
    }

    @Override
    public void delete(long assignmentId) {
        delete(format(DELETE_REQUEST, assignmentId));
    }

    @Override
    void setEntityDataToStatement(Assignment assignment, PreparedStatement statement) throws SQLException {
        statement.setBoolean(1, assignment.isAcceptedByDriver());
        statement.setDate(2, Date.valueOf(assignment.getStartDate()));
        statement.setLong(3, assignment.getRoute().getId());
        statement.setLong(4, assignment.getBus().getId());
        User driver = assignment.getDriver();
        if (driver != null) {
            statement.setLong(5, driver.getId());
        } else {
            statement.setNull(5, Types.BIGINT);
        }
    }

    @Override
    Assignment buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Assignment assignment = new Assignment();
        assignment.setId(resultSet.getLong("assignment_id"));
        assignment.setAcceptedByDriver(resultSet.getBoolean("assignment_accepted"));
        assignment.setCreationTime(resultSet.getTimestamp("assignment_creation_time").toLocalDateTime());
        assignment.setStartDate(resultSet.getDate("assignment_start_date").toLocalDate());
        Bus bus = new JdbcBusDao(transactionManager).buildEntityFromResultSet(resultSet);
        assignment.setBus(bus);
        Route route = new JdbcRouteDao(transactionManager).buildEntityFromResultSet(resultSet);
        assignment.setRoute(route);
        if (resultSet.getObject("user_id") != null) {
            User user = new JdbcUserDao(transactionManager).buildEntityFromResultSet(resultSet);
            assignment.setDriver(user);
        }
        return assignment;
    }
}
