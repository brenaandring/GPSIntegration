package com.goswiftly;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class HttpGPSModule {

    private final URL urlPath;
    private final VehicleStates vehicleStates;
    private final ObjectMapper mapper = new ObjectMapper();

    public HttpGPSModule(URL urlPath, VehicleStates vehicleStates) {
        this.urlPath = urlPath;
        this.vehicleStates = vehicleStates;
    }

    public List<GPSPing> ingestGPSPings() {
        final File tempFile;
        try {
            tempFile = Files.createTempFile("gps-pings.json", "").toFile();
            tempFile.deleteOnExit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedInputStream in = new BufferedInputStream(urlPath.openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            GPSPings parsedGpsPingsObj = mapper.readValue(tempFile, GPSPings.class);
            List<GPSPing> gpsPings = parsedGpsPingsObj.getGpsPings();
            gpsPings.forEach(vehicleStates::process);
            return gpsPings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
