package com.goswiftly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ingests GPS data from a file, where each line is a JSON object representing a single GPS ping. Then, updates the
 * VehicleStates with updated vehicle locations.
 */
public class JsonByLineGPSModule {

    private String dataPath;
    private VehicleStates vehicleStates;
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonByLineGPSModule(String dataPath, VehicleStates vehicleStates) {
        this.dataPath = dataPath;
        this.vehicleStates = vehicleStates;
    }

    public List<GPSPing> ingestGPSPings() {
        List<GPSPing> gpsPings = new ArrayList<>();

        try {
            InputStream is = this.getClass().getResourceAsStream(dataPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            for (String ln : bufferedReader.lines().collect(Collectors.toList())) {
                gpsPings.add(mapper.readValue(ln, GPSPing.class));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while reading GPS ping file", e);
        }

        gpsPings.forEach(ping -> vehicleStates.process(ping));
        return gpsPings;
    }
}
