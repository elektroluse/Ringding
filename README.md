# Springboot web API for bulk lookup of phonenumbers on (gulesider.no, tbd....)
- Build tool : Maven
- Scraper/HTML parser : jsoup (https://jsoup.org/)
- Testcases written with Junit, MockMvc and Mockito found under `src/test/java/.*`
- docker-compose.yml to easily set up a db image to use with the application
- DB interaction using DAO model with JDBC API calls, no JPA
- No db interaction in endpoints exposed by LookupController
- PhonebookController has all endpoints that interact with a db
## Endpoints
- [x] `GET : api/lookup/{number}`
- [x] `POST : api/lookup/list`
- [ ] `POST : api/lookup/upload`
  - Implemented and tested but needs refactor

- [ ] `GET : api/db/{number}`
- [ ] `GET : api/db/{companyname}`
- `MANY MORE implemented, list when decided`

## How to run
- `git clone https://github.com/elektroluse/Ringding.git`
- `cd Ringding`
- `docker-compose up`
- `./mvnw spring-boot:run`
  - Make API calls with Postman or write a program that sends HTTP requests to the endpoints available 

## TODO
- Refactor and test PhonebookController
  - Return a list of objects even when only one is to be expected, to avoid returning `null` at any point
- Implement sensible restrictions
  - Rate limiting
  - Validate that input string is numeric AND follows the E. 164 international standard
  - tbd
- Build and run instructions
- Usage examples