package com.car.park.service.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Assignment {

    private long id;
    private boolean acceptedByDriver;
    private LocalDateTime creationTime;
    private LocalDate startDate;
    private Route route;
    private Bus bus;
    private User driver;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAcceptedByDriver() {
        return acceptedByDriver;
    }

    public void setAcceptedByDriver(boolean acceptedByDriver) {
        this.acceptedByDriver = acceptedByDriver;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }
}
