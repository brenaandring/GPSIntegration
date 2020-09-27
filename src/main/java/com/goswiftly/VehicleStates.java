package com.goswiftly;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Maintains a mapping of vehicle ID to vehicle location and can print out vehicle locations **/
public class VehicleStates {

    private Map<String, Vehicle> vehicles = new HashMap<>();

    Vehicle getVehicle(String vehicleId) {
        return vehicles.get(vehicleId);
    }

    public void process(GPSPing gpsPing) {
        Vehicle vehicle = vehicles.getOrDefault(gpsPing.getVehicleId(), new Vehicle(gpsPing.getVehicleId()));
        if (vehicle.getLastUpdated() < gpsPing.getTimestamp()) {
            vehicle.setLastUpdated(gpsPing.getTimestamp());
            vehicle.setLatitude(gpsPing.getLatitude());
            vehicle.setLongitude(gpsPing.getLongitude());
            vehicles.put(gpsPing.getVehicleId(), vehicle);
        }
    }

    public void printVehicleLocations() {
        List<Vehicle> sortedList = getLatestVehicleStates();
        sortedList.forEach(System.out::println);
    }

    protected List<Vehicle> getLatestVehicleStates() {
        return vehicles.values().stream()
                .sorted(Comparator.comparing(Vehicle::getVehicleId))
                .collect(Collectors.toList());
    }
}
