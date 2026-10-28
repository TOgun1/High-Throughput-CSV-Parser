# High-Throughput-CSV-Parser

A Spring Book application designed to parse and ingest massive datasets (1,000,000 rows).

## Performance Metrics
* **Baseline (Spring Data JPA `save()`):** Estimated 4 hours (Tested: 100,000 rows in 24 mins / ~70 rows per second).
* **Optimized (JdbcTemplate Batching):** 16 seconds (~62,500 rows per second).
* **Batch Size:** 10,000 rows per network trip.

## Tech Stack
* **Language:** Java (Spring Boot)
* **Database:** PostgreSQL
* **Infrastructure:** Docker Desktop
* **Key Libraries:** Spring Data JPA, Spring JDBC, PostgreSQL Driver

## Core Architecture
* **Buffered Streaming:** Uses `BufferedReader` to process the 200MB+ CSV file line-by-line, preventing `OutOfMemoryError` crashes.
* **Chunking:** Groups parsed data into arrays of 10,000 records before sending a single bulk `INSERT` statement over the network.
* **Transactional Safety:** Wraps the ingestion process in Spring's `@Transactional` annotation to ensure database integrity (all-or-nothing commit).

## How to Run
### 1. Start the Database
Ensure Docker is running, then spin up the PostgreSQL container:

docker run --name ingestion_db -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=ecommercedb -p 5432:5432 -d postgres

### 2. Clear the Database before each run
docker exec -it ingestion_db psql -U postgres -d ecommercedb -c "TRUNCATE TABLE shoppers;"

### 3. Run the Spring Boot Server
./mvnw spring-boot:run

### 4. Trigger Ingestion
Replace the path with the real path to your CSV file.

Fast: curl -X POST "http://localhost:8080/api/v1/upload/fast?filePath=C:/YOUR_PATH/data/e_commerce_shopper_behaviour_and_lifestyle.csv"

Slow: curl -X POST "http://localhost:8080/api/v1/upload/slow?filePath=C:/YOUR_PATH/data/e_commerce_shopper_behaviour_and_lifestyle.csv"
