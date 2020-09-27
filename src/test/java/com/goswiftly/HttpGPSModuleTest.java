package com.goswiftly;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpGPSModuleTest {

    @Test
    void ingestGPSPings() {
        URL url = getClass().getClassLoader().getResource("gps_data_http_json");
        VehicleStates vehicleStates = new VehicleStates();
        HttpGPSModule httpGPSModule = new HttpGPSModule(url, vehicleStates);

        List<GPSPing> actualGpsPings = httpGPSModule.ingestGPSPings();

        List<GPSPing> expectedGpsPings = List.of(
                new GPSPing("123", 1600877135, 100.5, 500.6),
                new GPSPing("asd", 1600877157, -100.0, -200.123),
                new GPSPing("123", 1600877200, 23.23232, 47.47475)
        );

        assertEquals(expectedGpsPings, actualGpsPings);

        List<Vehicle> expectedVehicleStates = List.of(
                new Vehicle("123",23.23232, 47.47475, 1600877200),
                new Vehicle("asd",-100.0, -200.123, 1600877157)
        );

        assertEquals(expectedVehicleStates, vehicleStates.getLatestVehicleStates());
    }
}