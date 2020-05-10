package com.car.park.service.dao.jdbc;

import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;
import com.car.park.service.model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class JdbcUserDao extends AbstractJdbcDao<User> implements UserDao {

    private static final String INSERT_REQUEST = "INSERT INTO users " +
            "(user_login, user_password, user_email, user_phone, user_name, user_age, user_creation_time, user_access_role) " +
            "VALUES (?, ?, ?, ?, ?, ?, NOW(), ?)";
    private static final String UPDATE_REQUEST = "UPDATE users SET " +
            "user_login = ?, user_password = ?, user_email = ?, user_phone = ?, user_name = ?, user_age = ?, user_access_role = ? " +
            "WHERE user_id = ?";
    private static final String SELECT_BY_ID_REQUEST = "SELECT * FROM users WHERE user_id = %s";
    private static final String SELECT_BY_LOGIN_REQUEST = "SELECT * FROM users WHERE user_login = '%s'";
    private static final String SELECT_ALL_REQUEST = "SELECT * FROM users";
    private static final String SELECT_ALL_WITHOUT_ASSIGNMENT_REQUEST = "SELECT * FROM users WHERE " +
            "(SELECT assignment_id FROM assignments WHERE assignments.assignment_user_id = users.user_id) IS NULL";
    private static final String DELETE_REQUEST = "DELETE FROM users WHERE user_id = %s";

    public JdbcUserDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public void create(User user) {
        insert(INSERT_REQUEST, user);
    }

    @Override
    public User read(long userId) {
        return selectSingleElement(format(SELECT_BY_ID_REQUEST, userId));
    }

    @Override
    public User read(String userLogin) {
        return selectSingleElement(format(SELECT_BY_LOGIN_REQUEST, userLogin));
    }

    @Override
    public List<User> readAll() {
        return selectMultipleElements(SELECT_ALL_REQUEST);
    }

    @Override
    public List<User> readAllWithoutAssignment() {
        return selectMultipleElements(SELECT_ALL_WITHOUT_ASSIGNMENT_REQUEST);
    }

    @Override
    public void update(User user) {
        update(UPDATE_REQUEST, user, user.getId());
    }

    @Override
    public void delete(long userId) {
        delete(format(DELETE_REQUEST, userId));
    }

    @Override
    void setEntityDataToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getName());
        statement.setInt(6, user.getAge());
        statement.setString(7, user.getAccessRole().name());
    }

    @Override
    User buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("user_id"));
        user.setLogin(resultSet.getString("user_login"));
        user.setPassword(resultSet.getString("user_password"));
        user.setEmail(resultSet.getString("user_email"));
        user.setPhone(resultSet.getString("user_phone"));
        user.setName(resultSet.getString("user_name"));
        user.setAge(resultSet.getInt("user_age"));
        user.setCreationTime(resultSet.getTimestamp("user_creation_time").toLocalDateTime());
        user.setAccessRole(UserRole.valueOf(resultSet.getString("user_access_role")));
        return user;
    }
}
