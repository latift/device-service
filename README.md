This guide will show you how to run and test Device Rest API application.

Code is available on [github](https://github.com/latift/device-service) along with this document. 

## Prerequisites

* Java(11 Version), Maven, Git, Lombok enabled IDE(Optional), Postman(Optional)

## Clone the Source Code

    :::term
    $ git clone https://github.com/latift/device-service.git 

## Test Data

App uses H2 database(It may easily configured to use a standard SQL or NoSQL DB). Sample data is loaded on app start. You may list, create, edit and delete the sample data according to your test case. You may edit LoadDatabase.java.

###	Data Type And Entity Explained
* Device entity has name, brand and creationTime. 
* Sample device names are: Sim1, Sim2, Sim3
* Sample device brands are: BrandX, BrandY, BrandZ
* You can change the brand name and device name according to your wish, add a new one or delete it via LoadDatabase.java

## Build And Run The App Locally

Let's run the app locally first to test that it all works.

### Build and Run The Test Cases

    :::term
    $ mvn package
	[INFO] Scanning for projects...
	[INFO] 
	[INFO] --------------< com.restservicechallenge:device-service >---------------
	[INFO] Building device-service 0.0.1-SNAPSHOT
	[INFO] --------------------------------[ jar ]---------------------------------
	[INFO] 
	[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ device-service ---
	[INFO] Using 'UTF-8' encoding to copy filtered resources.
	[INFO] Using 'UTF-8' encoding to copy filtered properties files.
	[INFO] Copying 1 resource
	[INFO] Copying 0 resource
	[INFO] 
	[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ device-service ---
	[INFO] Nothing to compile - all classes are up to date
	[INFO] 
	[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ device-service ---
	[INFO] Not copying test resources
	[INFO] 
	[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ device-service ---
	[INFO] Not compiling test sources
	[INFO] 
	[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ device-service ---
	[INFO] Tests are skipped.
	[INFO] 
	[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ device-service ---
	[INFO] 
	[INFO] --- spring-boot-maven-plugin:2.5.1-SNAPSHOT:repackage (repackage) @ device-service ---
	[INFO] Replacing main artifact with repackaged archive
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time:  3.258 s
	[INFO] Finished at: 2021-06-01T10:40:05+03:00
	[INFO] ------------------------------------------------------------------------

### Start The App

    :::term
    $ java -jar target/device-service-0.0.1-SNAPSHOT.jar

## Test The App
You can test th app via curl, postman or browser
### Test it By CURL 
	Add device:  	
	curl -X POST localhost:8080/devices -H 'Content-type:application/json' -d '{"name": "Sim99", "brand": "truphone", "creationTime": "2019-04-09T23:15:45.345875+03:00"}'
	
	Get device by identifier:  
	curl -v localhost:8080/devices/1 | json_pp
	
	List all devices
	curl -v localhost:8080/devices | json_pp
	
	Update device (full and partial)
	curl -X PUT localhost:8080/devices/1 -H 'Content-type:application/json' -d '{"name": "Sim1_Updated", "brand": "truphone", "creationTime": "2019-04-09T23:15:45.345875+03:00"}'
	
	Delete a device
	curl -X DELETE localhost:8080/devices/1
	
	Search device by brand
	curl -v localhost:8080/devices?brand=truphone | json_pp

### Test it By Postman 
Be sure that app is running locally before running the below steps.
You should import Device-Server-Rest-API-Postman.json file to Postman.
You can test it by running it in the order you want. 

### Test it By Browser
Click the links below for test purposes.
*	[First Five Device](http://localhost:8080/devices)
*	[First Page of The BrandX Devices ](http://localhost:8080/devices?brand=BrandX&page=0)
	 
