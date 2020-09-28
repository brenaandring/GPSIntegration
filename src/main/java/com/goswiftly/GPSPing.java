package com.goswiftly;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Objects;

public class GPSPing {

    @JsonAlias("id")
    private String vehicleId;
    private long timestamp;
    @JsonAlias("lat")
    private double latitude;
    @JsonAlias("lon")
    private double longitude;

    public GPSPing() {
    }

    public GPSPing(String vehicleId, long timestamp, double latitude, double longitude) {
        this.vehicleId = vehicleId;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPSPing gpsPing = (GPSPing) o;
        return timestamp == gpsPing.timestamp &&
                Double.compare(gpsPing.latitude, latitude) == 0 &&
                Double.compare(gpsPing.longitude, longitude) == 0 &&
                Objects.equals(vehicleId, gpsPing.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, timestamp, latitude, longitude);
    }

    @Override
    public String toString() {
        return "GPSPing{" +
                "vehicleId='" + vehicleId + '\'' +
                ", timestamp=" + timestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
