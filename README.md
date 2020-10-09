# Open Liberty Pact Testing

## Scenario 1 - With Dev Mode
### Pre-requisite
1. Start consumer in dev mode
    1. `cd consumer; mvn liberty:dev` (1st Terminal)
1. Start provider in dev mode
    1. `cd ../provider; mvn liberty:dev` (2nd Terminal)
1. Start pact-broker using docker compose. 
    1. `cd ../pact-broker; docker-compose -f docker-compose.yml up -d --build` (3rd Terminal)
    
### Flow:    
1. Consumer
    1. Replace pom.xml. Explain pact dependency + pact plugin
    1. Explain consumer pact test.
    1. Use the 1st Terminal dev mode to run the tests. - Obtain pact contract .json file.
    1. Run `cd ../consumer; mvn pact:verify` (3rd Terminal) to run tests against the real provider.
    1. Run `mvn pact:publish` (3rd Terminal) to  upload pact contract .json file to pact-broker.
    1. Explain pact-broker UI @http://localhost:9292/
1. Provider
    1. Replace unit test file
    1. Using 2nd Terminal - Press enter to run tests (Pact Provider will verify against pact-broker)
    1. Show in pact-broker UI that the contract has been verified by the provider.
    1. Make a change to a specific endpoint which breaks the contract.
    1. Press enter to run tests (2nd Terminal) and see pact failures.
    1. If guide is not too long - Re run `mvn pact:verify` on 3rd Terminal (Consumer side) to show test errors against real provider.     

## Scenario 2 - No dev mode
### Pre-requisite
1. Start consumer
    1. `cd consumer; mvn liberty:run` (1st Terminal)
1. Start provider
    1. `cd ../provider; mvn liberty:run` (2nd Terminal)
1. Start pact-broker using docker compose. 
    1. `cd ../pact-broker; docker-compose -f docker-compose.yml up -d --build` (3rd Terminal)
    
### Flow:  
1. Consumer
    1. Replace pom.xml. Explain pact dependency + pact plugin
    1. Explain consumer pact test.
    1. `cd ../consumer; mvn test` (3rd Terminal) to run the tests. - Obtain pact contract .json file.
    1. `mvn pact:verify` to run tests against the real provider.
    1. `mvn pact:publish` to upload pact contract .json file to pact-broker.
    1. Explain pact-broker UI @http://localhost:9292/
1. Provider
    1. Replace unit test file    
    1. `cd ../provider; mvn test` to run tests (Pact Provider will verify against pact-broker)
    1. Make a change to a specific endpoint which breaks the contract.
    1. `mvn test` to re run tests and see pact failures.
    1. If guide is not too long - run `cd ../consumer; mvn pact:verify` (Consumer side) to show test errors against real provider.     


-----------------------------------------------------------
### Start Consumer

`cd consumer; mvn liberty:dev`

### Create pact file
`Press enter to run test and create pact file`

### Start provider service
`cd provider; mvn liberty:dev`

### Run pact tests against actual provider service
`mvn pact:verify`

### Start the pact broker
```
cd pact-broker
docker-compose -f docker-compose.yml up --build
```

Visit http://localhost:9292/ to check out the pact broker UI

### Publish to the pact-broker
```
cd consumer;
mvn pact:publish
```

### Verify consumer contract from provider side using pact-broker
`cd provider; mvn liberty:dev; press enter to run tests`
