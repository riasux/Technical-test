# Build

## With TUs

`clean install`

## Without TUs

`clean install -DskipTests`

## Variables d'environnement

| Variables                          | Descriptions        | Examples |
|------------------------------------|---------------------|----------|
| TECHNICAL_TEST_SERVER_PORT_NUMBER  | port number         | 8082 |
| DB_TECHNICAL_TEST_USERNAME         | Username DB CerbaView |sa|
| DB_TECHNICAL_TEST_PASSWORD         | Database password TechnicalTest ||
| DB_TECHNICAL_TEST_DRIVER_NAME      | Name of DB TechnicalTest driver | org.h2.Driver |
| DB_TECHNICAL_TEST_DIALECT          | dialect of DB TechnicalTest | org.hibernate.dialect.H2Dialect |
| DB_TECHNICAL_TEST_URL              | URL de la DB | jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false |

## links to swagger documentation

http://localhost:8082/api/technical-test/swagger-ui.html

## links to h2 database

http://localhost:8082/api/technical-test/h2-console

    --JDBC URL = jdbc:h2:mem:testdb
    --User Name = sa
    --Password = 