# spring-data-hibernate-postgres-bigint
Minimal working example on a bug in Hibernate, mapping Postgres type `bigint` to `java.math.BigInteger` instead of `java.lang.Long`

## Problem Description
A Postgres bigint column is mapped to BigInteger through Hibernate.

This effect triggers when using native queries, where no Java class is bound through an entity, otherwise Hibernate would use the class defined in the entity.

In this example this happens with native queries via Spring Data, but queries without Spring Data are affected in the same way.

## Possible fix
In `test.MyDialect` is a line ```registerHibernateType( Types.BIGINT, StandardBasicTypes.LONG.getName() );```.

Commenting in this line, will give the expected results, returning a Long.

## Used dependencies
* Spring boot (for ease of use)
* Spring Data JPA
* Hibernate 5.2.7
* Postgres JDBC 4.2 Driver

## Setup database
Login to psql via admin user
`psql`

```
-- Create user
CREATE USER test WITH PASSWORD 'test';

-- Create database
CREATE DATABASE test;

-- Give user right to access/modify newly created database
GRANT ALL PRIVILEGES ON DATABASE test TO test;
```



Login via newly created user: `psql -U test test`

```
-- Create sample table
CREATE TABLE sample(id BIGSERIAL, name TEXT NOT NULL UNIQUE);
```
