# Mortgage Check Application

This project contains rest api application created to show interest rates and calculate for a mortgage check.

# About The Project
This Project is developed to show interest rates and calculate for a mortgage check. This contains an api's to show and perform below actions:
* Show available interest rates
* Calculate mortgage check


# Build With
This project has build up with following frameworks and tools/technology used:
* Spring Boot
* Restfull Service
* Java


# Getting Started
## Prerequisites
	Following are the pre-requisites:
	
	* Intellij IDE
	* Postman

## Installation

	1. Import project
	2. Maven present in project
    3. Enable lombok
	3. Build project and Run as java application 
	4. Use rest service in postman to perform actions

## Example
    1. We can access all interest rates via - GET http://localhost:8080/api/interest-rates
```
[
    {
        "maturityPeriod": 24,
        "interestRate": 2.0,
        "lastUpdate": "2012-09-16T22:00:00.000+00:00"
    },
    {
        "maturityPeriod": 48,
        "interestRate": 4.0,
        "lastUpdate": "2012-09-16T22:00:00.000+00:00"
    }
]
```
    2. We can calculate mortgage check via - POST http://localhost:8080/api/mortgage-check
```
{
    "income": 10000,
    "maturityPeriod": 24,
    "loanValue": 100000,
    "homeValue": 110000

}
```

    3. If we want to add/edit interest rate, we can modify application.yml file and restart application
```

4. We can use endpoints help of http://localhost:8080/swagger-ui/index.html
