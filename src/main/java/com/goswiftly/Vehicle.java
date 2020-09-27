package com.goswiftly;

import java.util.Objects;

public class Vehicle {

    private String vehicleId;
    private double latitude;
    private double longitude;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public Vehicle setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Vehicle setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Vehicle setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.latitude, latitude) == 0 &&
                Double.compare(vehicle.longitude, longitude) == 0 &&
                Objects.equals(vehicleId, vehicle.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                "vehicleId='" + vehicleId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
