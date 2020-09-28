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

    @Test
    void processDiscardsOldGPSPing() {
        VehicleStates vehicleStates = new VehicleStates();
        GPSPing gpsPingNew = new GPSPing("mazda", 1701220, 10.0, 20.0);
        GPSPing gpsPingOld = new GPSPing("mazda", 1701100, 5.0, 10.0);

        vehicleStates.process(gpsPingNew);
        vehicleStates.process(gpsPingOld);

        List<Vehicle> latestVehicleStates = vehicleStates.getLatestVehicleStates();
        List<Vehicle> expectedState = List.of(new Vehicle("mazda", 10.0, 20.0, 1701220));

        assertEquals(expectedState, latestVehicleStates);
    }

    @Test
    void processMultipleGPSPings() {
        VehicleStates vehicleStates = new VehicleStates();
        GPSPing gpsPing1 = new GPSPing("bus1", 1205967, 15.0, 20.0);
        GPSPing gpsPing2 = new GPSPing("bus2", 1302954, 5.0, 20.0);

        vehicleStates.process(gpsPing1);
        vehicleStates.process(gpsPing2);

        List<Vehicle> latestVehicleStates = vehicleStates.getLatestVehicleStates();
        List<Vehicle> expectedState = List.of(
                new Vehicle("bus1", 15.0, 20.0, 1205967),
                new Vehicle("bus2", 5.0, 20.0, 1302954)
        );

        assertEquals(expectedState, latestVehicleStates);

    }

}