package com.car.park.service.dao.jdbc;

import com.car.park.service.dao.exceptions.UncheckedSQLException;
import com.car.park.service.dao.support.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractJdbcDao<T> {

    private final TransactionManager transactionManager;

    AbstractJdbcDao(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    void insert(String request, T entity) {
        try {
            Connection connection = transactionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(request);
            setEntityDataToStatement(entity, statement);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    T selectSingleElement(String request) {
        List<T> entities = selectMultipleElements(request);
        return entities.isEmpty() ? null : entities.stream().findFirst().get();
    }

    List<T> selectMultipleElements(String request) {
        List<T> entities = new ArrayList<>();
        try {
            Connection connection = transactionManager.getConnection();
            PreparedStatement query = connection.prepareStatement(request);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                entities.add(buildEntityFromResultSet(resultSet));
            }
            resultSet.close();
            query.close();
            return entities;
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    void update(String request, T entity, long entityId) {
        try {
            Connection connection = transactionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(request);
            setEntityDataToStatement(entity, statement);
            statement.setLong(statement.getParameterMetaData().getParameterCount(), entityId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    void delete(String request) {
        try {
            Connection connection = transactionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(request);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    abstract void setEntityDataToStatement(T entity, PreparedStatement statement) throws SQLException;

    abstract T buildEntityFromResultSet(ResultSet resultSet) throws SQLException;
}
