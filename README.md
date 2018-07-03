# currency

Current Currencies Collector service. And web service for converting amount of money.
Written in Java.

### Used Libraries:
- Jersey-client
- Postgres
- Jdbi
- Gson
- Spring Boot

### Installation:
 - Put your https://openexchangerates.org/api Key to currency-collector/config/app.properties -> appId
 - Create PostreSql database
 - Run Sql script from currency/sql/initDB.sql
 - Set database properties to currency-collector/config/database.properties
   And currency-viewer/src/main/resources/application.properties
 - Deploy and start both services.
