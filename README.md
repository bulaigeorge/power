# Power consumption and Emission API

### To get the API running you just need to 
```
run the PowerApplication.java file
```
### The data is stored using PostgreSQL hosted on ElephantSQL
### You will be able to access the different routes using REST-conventions

## Available routes are:

### Accessing the api for locations -
* GET all locations - localhost:8080/api/location
* GET location by id - localhost:8080/api/location/{id}
* POST location - localhost:8080/api/location
* POST multiple locations - localhost:8080/api/location/all
* PUT update location by id - localhost:8080/api/location/{id}
* DELETE location by id - localhost:8080/api/location/{id}

### Accessing the api for emissions -
* GET all emission - localhost:8080/api/emission
* GET emission by type - localhost:8080/api/emission/{type}
* POST emission - localhost:8080/api/emission
* POST multiple emissions - localhost:8080/api/emission/all
* PUT update emission - localhost:8080/api/emission
* DELETE emission by type - localhost:8080/api/emission/{type}


### Accessing the api for cityInformation - this is where the calculations are made

* GET all cities - localhost:8080/api/information
* GET city by name - localhost:8080/api/information/{cityName}
* GET city by name and month - localhost:8080/api/information/{cityName}/{month}

To aggregate the data and create a result for a city use the:

    POST city - localhost:8080/api/information/{cityName}


* PUT update city - localhost:8080/api/information/{cityName}
* DELETE city by name - localhost:8080/api/information/{cityName}
        
 
