Aggregation Service
===============================
---------------

## 🔨 Environments & Dependencies

```
------------------------------
- JDK 11
- Maven 4.0.0
- Docker Engine v20.10.21
- Filebeat
- ELK stack
------------------------------
- Spring Boot - 2.7.3
    - starter-web
    - springdoc-openapi-ui
    - starter-test

- lombok
- junit 5
------------------------------
```

## 🚀 Run all instances

- `docker-compose.yml` file has been created to run all modules.
- `run.sh` starts the docker-compose.

##### To make it work;

1. `chmod a+x *.sh`
2. `./run.sh`

## 🎉 Endpoints

##### Swagger Documentation : `http://localhost/doc/`

##### Business endpoints:

```shell script
 - GET storeDetections: /detection
    - Sample data:
    {
  "shipments": {
    "shipmentsOrderNumbers": [
      "109347263"
    ]
  },
  "track": {
    "trackOrderNumbers": "109347263"
  },
  "pricing": {
    "pricingCountryCode": "NL",
  }
}   
```

## 🧪 Test

- The Docker engine must be running to run tests.
- Unit ✅
- Integration ✅

## 📝 Note

- There are points "Could Have Added" in the code. So, I am looking forward to explaining them all at the next meeting.

## Thanks for your time and consideration.
