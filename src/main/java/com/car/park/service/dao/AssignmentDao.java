package com.car.park.service.dao;

import com.car.park.service.model.*;

import java.util.List;

public interface AssignmentDao {

    void create(Assignment assignment);

    Assignment read(long assignmentId);

    Assignment readByBusId(long busId);

    Assignment readByDriverId(long driverId);

    List<Assignment> readByRouteId(long routeId);

    void update(Assignment assignment);

    void delete(long assignmentId);
}
