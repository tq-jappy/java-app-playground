# java-app-playground

## Setup

```
$ docker-compose build
$ docker-compose up -d
```

## CodeGen

```
$ ./gradlew generateMainJooqSchemaSource
```

## Debug

MySQL

```
$ mysql -uroot -h127.0.0.1 --password=root test
```