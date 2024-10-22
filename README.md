# Springboot web API for bulk lookup of phonenumbers on (gulesider.no, tbd....)
- Build tool : Maven
- Scraper/HTML parser : jsoup (https://jsoup.org/)
- Testcases written with Junit and MockMvc found under `src/test/java/.*`

## Endpoints implemented and tested
- [x] `GET : api/lookup/{number}`
- [x] `POST : api/lookup/list`
- [ ] `POST : api/lookup/upload`
  - Implemented and working but lacks testcase and needs refactor


## TODO
- Connect to postgresql db 
  - To record api calls processed OR store results (not decided)
  - Write the sql queries using JDBC API
- Implement sensible restrictions
  - e.g stop someone from requesting 4 billion lookups
  - Validate that input string is numeric AND follows the E. 164 international standard
  - tbd
- Build and run instructions
- Usage examples