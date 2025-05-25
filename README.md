# Price Comparator Backend - Accesa Coding Challenge

## Overview

This is a **Spring Boot** web application developed in **Java** using **Maven**, designed for the *Accesa Java Internship Coding Challenge*. The application allows users to compare grocery item prices across different supermarket chains. It enables users to track price changes, find the best deals, discover new discounts, get product recommendations, and set custom price alerts.

## Project Structure

The application is built upon a **layered architecture** to ensure a clear separation of concerns and promote maintainability. Core components include **JPA entities** for the data model, **Spring Data JPA repositories** for database interaction, a **service layer** encapsulating business logic, and **REST controllers** exposing the API. Between layers, data is exchanged through **DTOs** and **MapStruct mappers**. Data from CSV files is parsed on startup and mapped to objects to populate the **MySql** database. 


## How to Build and Run the Application

### Prerequisites

* Java JDK (Version 17 or later)
* Apache Maven

### Build Steps

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/ioanaghergu/AccesaCodingChallenge.git](https://github.com/ioanaghergu/AccesaCodingChallenge.git)
    cd AccesaCodingChallenge
    ```
2.  **Build with Maven:**
    From the root directory of the project, run:
    ```bash
    mvn clean install
    ```
    
### Running the Application

1.  **Run using Maven Spring Boot Plugin:**
    From the root directory of the project, execute:
    ```bash
    mvn spring-boot:run
    ```
2.  The application will start and be available at `http://localhost:8080`.

## Assumptions Made or Simplifications

* **Data Source**: CSV files are the primary data source, loaded into a MySql database at startup.
* **User Management**: User entities exist, but authentication is not implemented. User-specific actions often on `userId` passed in requests.
* **Notifications**: For price alerts, triggering is logged, but actual notification dispatch (email, sms etc.) is out of scope.
* **Product Original Price**: For discount calculations, this is typically the latest price entry for a product at a store.
* **Newly Added Discounts**: Based on an `entryDate` field in the `Discount` entity, typically meaning "added today".

