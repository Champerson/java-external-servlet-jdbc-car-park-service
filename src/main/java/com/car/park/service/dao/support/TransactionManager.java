package com.car.park.service.dao.support;

import com.car.park.service.dao.exceptions.UncheckedSQLException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private static TransactionManager transactionManager;
    private ThreadLocal<Connection> connectionContainer = new ThreadLocal<>();

    private TransactionManager() {
    }

    public static TransactionManager getInstance() {
        TransactionManager localInstance = transactionManager;
        if (localInstance == null)
            synchronized (TransactionManager.class) {
                localInstance = transactionManager;
                if (localInstance == null)
                    transactionManager = localInstance = new TransactionManager();
            }
        return localInstance;
    }

    public Connection getConnection() throws SQLException {
        if (!isConnectionOpen()) {
            connectionContainer.set(DbcpDataSource.getConnection());
            connectionContainer.get().setAutoCommit(false);
        }
        return connectionContainer.get();
    }

    public void commit() {
        try {
            connectionContainer.get().commit();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    public void rollback() {
        try {
            if (isConnectionOpen()) {
                connectionContainer.get().rollback();
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    public void closeConnection() {
        try {
            if (isConnectionOpen()) {
                connectionContainer.get().close();
                connectionContainer.remove();
            }
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    private boolean isConnectionOpen() throws SQLException {
        return connectionContainer.get() != null && !connectionContainer.get().isClosed();
    }
}
