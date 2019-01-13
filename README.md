# java-app-playground

- Java 11
- Spring Boot 2.1.1.RELEASE
- JPA & JOOQ

## Setup

```
$ docker-compose build
$ docker-compose up -d
```

## Migration

```
$ ./gradlew flywayMigrate
```

## JOOQ CodeGen

```
$ ./gradlew generateMainJooqSchemaSource
```

## Start Application

```
$ ./gradlew bootRun
$ open http://localhost:8080/
```

## Debug

### MySQL

```
$ mysql -uroot -h127.0.0.1 --password=root test
```