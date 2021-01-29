# GPS Feed Integration

## _Background_
All of the company's products depend on getting accurate GPS data from vehicles. They have to integrate with a 
variety of GPS feeds to pull this data depending on the equipment on the transit agency’s vehicles. 

### _Problem Statements and Requirements_
#### Problem 1 
We’ve received a report from a customer using the ​JsonByLineGPSModule​ that their vehicles are occasionally jumping 
around and going backwards on our live map. In fact, the final printed location of vehicles in our example is not 
correct based on the input file.

##### Requirement:
Figure out what the issue is and amend the code to handle it. There could be multiple solutions so please include a 
quick note in the README on the approach you chose.

#### Problem 2
The same transit agency has decided to provide an alternate GPS feed hosted at an HTTP endpoint. The M​ ain.java​ class 
contains a stub method for us to integrate their feed by ingesting the file at the included URL. The data format is 
not exactly the same but the data is, so we should ultimately see the same results as in problem 1.

##### Requirement:
Build a new GPS module to pull GPS data from the provided HTTP endpoint, process it, and print the final vehicle locations.

## Project Solutions and Tests
The application processes data from files and URL and prints final locations to the screen for each vehicle 
after ingesting all the pings.

#### Problem 1 Solution
When running the `Main` class, I noticed that all printed vehicle information were the most recent timestamps, 
except for vehicleID 3892, which printed 
```
{ "vehicleId": "3829", "timestamp": 1600877327, "latitude": 37.775840, "longitude": -122.446400 }
```
to the command line. However, this vehicle had a newer ping, so 
what should have actually been printed to the command line was 
```
{ "vehicleId": "3829", "timestamp": 1600877339, "latitude": 37.777830, "longitude": -122.438290 }
```
which explains why it was reported the bus was going backwards. 

To fix this problem, we could either sort the pings by timestamp or discard irrelevant pings. Sorting the file can be 
difficult if the file gets bigger. I decided to discard the old pings and this is why I added a field `lastUpdated` to 
the `Vehicle` class, so `VehicleStates` could compare incoming pings with already processed ones.

#### Problem 2 Solution
For the second problem, I used Java IO to download the data as a file. Since it one JSON object, I couldn't parse through 
it line by line, so that's why I had to parse the file. Once the JSON object is parsed, `UrlGPSModule` gets 
pings from the object and processes them one by one. 

### Tests
In the `VehicleStatesTest`, I am checking that the process method is working correctly. Also, this class includes a
test for checking pings with old timestamps are discarded and that the latest vehicle state is what will be printed in the 
command line.

The `JsonByLineGPSModuleTest` and `UrlGPSModuleTest` both test that GPS data can be pulled from a file and a Http endpoint.

## Getting Started
- Download and install Java 11

### Running the Code

Run Tests:
```
mvn test
```
You should see 5 passing test.

Build the Code:
```
mvn package
```

Run the Code:
```
java -jar target/Main.jar
```
