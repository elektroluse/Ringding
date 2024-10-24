# Springboot web API for bulk lookup of phonenumbers on (gulesider.no, tbd....)
- Build tool : Maven
- Scraper/HTML parser : jsoup (https://jsoup.org/)
- Testcases written with Junit and MockMvc found under `src/test/java/.*`
- docker-compose.yml to easily set up a db image to use with the application

## Endpoints implemented and tested
- [x] `GET : api/lookup/{number}`
- [x] `POST : api/lookup/list`
- [ ] `POST : api/lookup/upload`
  - Implemented and tested but needs refactor


## How to run
- `git clone https://github.com/elektroluse/Ringding.git`
- `cd Ringding`
- `docker-compose up` (Not needed YET)
- `./mvnw spring-boot:run`
  - Make API calls with Postman or write a program that sends HTTP requests to the endpoints available 

## TODO
- Connect to postgresql db 
  - Write new controller and endpoints that interacts with a db
  - Register records in db, lookup in db
  - Query for all numbers registered to a company (that is present in the db)
  - Write sql queries using JDBC API
- Implement sensible restrictions
  - e.g stop someone from requesting 4 billion lookups
  - Validate that input string is numeric AND follows the E. 164 international standard
  - tbd
- Build and run instructions
- Usage examples