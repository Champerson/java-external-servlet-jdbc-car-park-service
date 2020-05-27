package com.car.park.service.dao;

import com.car.park.service.model.Route;

import java.util.List;

public interface RouteDao {

    void create(Route route);

    Route read(long routeId);

    Route read(String number);

    List<Route> readAll();

    void update(Route route);

    void delete(long routeId);
}

