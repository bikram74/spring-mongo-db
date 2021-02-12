# Product REST API

REST API to Get, Create and Update for products.

### Import Project

Project can be run in any Java 1.8 compatible IDE. Import project to IntelliJ or Eclipse as gradle project. The application was built using Intellij on Mac.

### Install and start MongoDB (Mac)

```
brew install mongodb-community@4.4

brew services start mongodb-community@4.4

```
### Insert Test Data

```
mongo

db.product.insert({ "_id" : "13860428", "name" : "", "current_price" : { "value" : 799.99, "currency_code" : "USD" } });
db.product.insert({ "_id" : "13264003", "name" : "", "current_price" : { "value" : 348.49, "currency_code" : "USD" } });
db.product.insert({ "_id" : "54456119", "name" : "", "current_price" : { "value" : 199.49, "currency_code" : "USD" } });
```

### Build and Run Rest API

To build double click on build under Gradle -> build. To Run double click on Gradle -> applcaition -> bootRun.

### Run Tests

Double click on Gradle -> build -> testClasses. Or right click on ProductRestApplicationTest class and click run.
 
### API Calls

### Index

```
curl http://localhost:8080/
```

### All

```
curl http://localhost:8080/product
```

### Get One by Id

```
http://localhost:8080/product/13860428
```

### Create One (POST)

```
curl -i -H "Content-Type:application/json" -d '{
  "id": "72837212",
  "name": "Samsung TV",
  "current_price": {
    "value": 399.49,
    "currency_code": "USD"
  }
}' http://localhost:8080/product

```

### Update One (PUT)

```
curl -X PUT -H "Content-Type:application/json" -d '{
  "name": "Samsung TV",
  "current_price": {
    "value": 199.49,
    "currency_code": "USD"
  }
}' http://localhost:8080/product/72837212

```

 
