package com.goswiftly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JsonByLineGPSModuleTest {

    @Test
    public void testIngestGPSPings() {
        VehicleStates vehicleStates = new VehicleStates();
        JsonByLineGPSModule gpsModule = new JsonByLineGPSModule("/test_gps_data_json_by_line",
                vehicleStates);
        List<GPSPing> actualGpsPings = gpsModule.ingestGPSPings();

        List<GPSPing> expectedGpsPings = Arrays.asList(
                new GPSPing("0102", 1600877200, 37.790010, -122.400490),
                new GPSPing("VEH_1425", 1600877235, 37.788490, -122.402480),
                new GPSPing("VEH_1425", 1600877230, 37.788590, -122.405480));

        Assertions.assertEquals(expectedGpsPings, actualGpsPings);

        List<Vehicle> expectedStates = List.of(
                new Vehicle("0102",37.79001,   -122.40049, 1600877200),
                new Vehicle("VEH_1425",37.78849,   -122.40248, 1600877235)
        );
        Assertions.assertEquals(expectedStates, vehicleStates.getLatestVehicleStates());
    }
}
