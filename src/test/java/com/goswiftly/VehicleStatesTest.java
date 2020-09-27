package com.goswiftly;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleStatesTest {

    @Test
    void processUpdatesVehicleState() {
        VehicleStates vehicleStates = new VehicleStates();
        GPSPing gpsPing = new GPSPing("my-tesla", 100, 10.0, 20.0);

        vehicleStates.process(gpsPing);

        List<Vehicle> latestVehicleStates = vehicleStates.getLatestVehicleStates();
        List<Vehicle> expectedState = List.of(new Vehicle("my-tesla", 10.0, 20.0, 100));

        assertEquals(expectedState, latestVehicleStates);
    }
}