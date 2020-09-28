package com.goswiftly;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        System.out.println("** Test 1: Locations after processing JSON by line GPS feed **");
        processJsonByLineGPS("/gps_data_json_by_line");

        System.out.println("** Test 2: Locations after processing HTTP GPS feed **");
        processJsonHttpGPS("https://swiftly-public-data.s3-us-west-2.amazonaws.com/gps_ping_data.json");
    }

    public static void processJsonByLineGPS(String inputFileName) {
        final VehicleStates vehicleStates = new VehicleStates();
        JsonByLineGPSModule gpsModule = new JsonByLineGPSModule(inputFileName, vehicleStates);
        gpsModule.ingestGPSPings();
        vehicleStates.printVehicleLocations();
    }

    public static void processJsonHttpGPS(String inputFileUrl) {
        final VehicleStates vehicleStates = new VehicleStates();
        final URL urlPath;
        try {
            urlPath = new URL(inputFileUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpGPSModule httpGPSModule = new HttpGPSModule(urlPath, vehicleStates);
        httpGPSModule.ingestGPSPings();
        vehicleStates.printVehicleLocations();
    }
}