package com.car.park.service.dao;

import com.car.park.service.model.Bus;

import java.util.List;

public interface BusDao {

    void create(Bus bus);

    Bus read(long busId);

    List<Bus> readAll();

    List<Bus> readAllWithoutAssignment();

    void update(Bus bus);

    void delete(long busId);
}
