package com.car.park.service.dao.jdbc;

import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Bus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

public class JdbcBusDao extends AbstractJdbcDao<Bus> implements BusDao {

    private static final String INSERT_REQUEST = "INSERT INTO buses (" +
            "bus_number, bus_model, bus_passengers_capacity, bus_mileage, bus_creation_time, " +
            "bus_colour_en, bus_colour_ua, bus_notes_en, bus_notes_ua) " +
            "VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?)";
    private static final String UPDATE_REQUEST = "UPDATE buses SET " +
            "bus_number = ?, bus_model = ?, bus_passengers_capacity = ?, bus_mileage = ?, " +
            "bus_colour_en = ?, bus_colour_ua = ?, bus_notes_en = ?, bus_notes_ua = ? " +
            "WHERE bus_id = ?";
    private static final String SELECT_BY_ID_REQUEST = "SELECT * FROM buses WHERE bus_id = %s";
    private static final String SELECT_ALL_REQUEST = "SELECT * FROM buses";
    private static final String SELECT_ALL_WITHOUT_ASSIGNMENT_REQUEST = "SELECT * FROM buses WHERE " +
            "(SELECT assignment_id FROM assignments WHERE assignments.assignment_bus_id = buses.bus_id) IS NULL";
    private static final String DELETE_REQUEST = "DELETE FROM buses WHERE bus_id = %s";

    JdbcBusDao(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public void create(Bus bus) {
        insert(INSERT_REQUEST, bus);
    }

    @Override
    public Bus read(long busId) {
        return selectSingleElement(format(SELECT_BY_ID_REQUEST, busId));
    }

    @Override
    public List<Bus> readAll() {
        return selectMultipleElements(SELECT_ALL_REQUEST);
    }

    @Override
    public List<Bus> readAllWithoutAssignment() {
        return selectMultipleElements(SELECT_ALL_WITHOUT_ASSIGNMENT_REQUEST);
    }

    @Override
    public void update(Bus bus) {
        update(UPDATE_REQUEST, bus, bus.getId());
    }

    @Override
    public void delete(long busId) {
        delete(format(DELETE_REQUEST, busId));
    }

    @Override
    void setEntityDataToStatement(Bus bus, PreparedStatement statement) throws SQLException {
        statement.setString(1, bus.getNumber());
        statement.setString(2, bus.getModel());
        statement.setInt(3, bus.getPassengersCapacity());
        statement.setInt(4, bus.getMileage());
        statement.setString(5, bus.getLocalizedColour().get("en_EN"));
        statement.setString(6, bus.getLocalizedColour().get("uk_UA"));
        statement.setString(7, bus.getLocalizedNotes().get("en_EN"));
        statement.setString(8, bus.getLocalizedNotes().get("uk_UA"));
    }

    @Override
    Bus buildEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Bus bus = new Bus();
        bus.setId(resultSet.getLong("bus_id"));
        bus.setNumber(resultSet.getString("bus_number"));
        bus.setModel(resultSet.getString("bus_model"));
        bus.setPassengersCapacity(resultSet.getInt("bus_passengers_capacity"));
        bus.setMileage(resultSet.getInt("bus_mileage"));
        bus.setCreationTime(resultSet.getTimestamp("bus_creation_time").toLocalDateTime());
        bus.getLocalizedColour().put("en_EN", resultSet.getString("bus_colour_en"));
        bus.getLocalizedColour().put("uk_UA", resultSet.getString("bus_colour_ua"));
        bus.getLocalizedNotes().put("en_EN", resultSet.getString("bus_notes_en"));
        bus.getLocalizedNotes().put("uk_UA", resultSet.getString("bus_notes_ua"));
        return bus;
    }
}
