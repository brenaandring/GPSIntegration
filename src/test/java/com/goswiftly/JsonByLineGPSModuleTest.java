package com.goswiftly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JsonByLineGPSModuleTest {

    @Test
    public void testIngestGPSPings() {
        JsonByLineGPSModule gpsModule = new JsonByLineGPSModule("/test_gps_data_json_by_line",
                new VehicleStates());
        List<GPSPing> actualGpsPings = gpsModule.ingestGPSPings();

        List<GPSPing> expectedGpsPings = Arrays.asList(
                new GPSPing("0102", 1600877200, 37.790010, -122.400490),
                new GPSPing("VEH_1425", 1600877235, 37.788490, -122.402480));

        Assertions.assertEquals(expectedGpsPings, actualGpsPings);
    }
}
