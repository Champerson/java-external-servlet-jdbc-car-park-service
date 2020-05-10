package com.car.park.service.dao.jdbc;

import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Route;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class JdbcRouteDao extends AbstractJdbcDao<Route> implements RouteDao {

    private static final String INSERT_REQUEST = "INSERT INTO routes " +
            "(route_number, route_length, route_creation_time, route_description_en, route_description_ua) " +
            "VALUES (?, ?, NOW(), ?, ?)";
    private static final String UPDATE_REQUEST = "UPDATE routes SET " +
            "route_number = ?, route_length = ?, route_description_en = ?, route_description_ua = ? " +
            "WHERE route_id = ?";
    private static final String SELECT_BY_ID_REQUEST = "SELECT * FROM routes WHERE route_id = %s";
    private static final String SELECT_ALL_REQUEST = "SELECT * FROM routes";
    private static final String DELETE_REQUEST = "DELETE FROM routes WHERE route_id = %s";

    JdbcRouteDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public void create(Route route) {
        insert(INSERT_REQUEST, route);
    }

    @Override
    public Route read(long routeId) {
        return selectSingleElement(format(SELECT_BY_ID_REQUEST, routeId));
    }

    @Override
    public List<Route> readAll() {
        return selectMultipleElements(SELECT_ALL_REQUEST);
    }

    @Override
    public void update(Route route) {
        update(UPDATE_REQUEST, route, route.getId());
    }

    @Override
    public void delete(long routeId) {
        delete(format(DELETE_REQUEST, routeId));
    }

    @Override
    void setEntityDataToStatement(Route route, PreparedStatement statement) throws SQLException {
        statement.setString(1, route.getNumber());
        statement.setInt(2, route.getLength());
        statement.setString(3, route.getLocalizedDescription().get("en_EN"));
        statement.setString(4, route.getLocalizedDescription().get("uk_UA"));
    }

    @Override
    Route buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getLong("route_id"));
        route.setNumber(resultSet.getString("route_number"));
        route.setLength(resultSet.getInt("route_length"));
        route.setCreationTime(resultSet.getTimestamp("route_creation_time").toLocalDateTime());
        route.getLocalizedDescription().put("en_EN", resultSet.getString("route_description_en"));
        route.getLocalizedDescription().put("uk_UA", resultSet.getString("route_description_ua"));
        return route;
    }
}
