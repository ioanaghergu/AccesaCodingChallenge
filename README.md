# Price Comparator Backend - Accesa Coding Challenge

## Overview

This is a **Spring Boot** web application developed in **Java** using **Maven**, designed for the *Accesa Java Internship Coding Challenge*. The application allows users to compare grocery item prices across different supermarket chains. It enables users to track price changes, find the best deals, discover new discounts, get product recommendations, and set custom price alerts.

## Project Structure

The application is built upon a **layered architecture** to ensure a clear separation of concerns and promote maintainability. Core components include **JPA entities** for the data model, **Spring Data JPA repositories** for database interaction, a **service layer** encapsulating business logic, and **REST controllers** exposing the API. Between layers, data is exchanged through **DTOs** and **MapStruct mappers**. Data from CSV files is parsed on startup, mapped to entity objects, and used to populate the **MySQL** database. 


## How to Build and Run the Application

### Prerequisites

* Java JDK (Version 17 or later)
* Apache Maven
* MySQL Server

### Build Steps

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/ioanaghergu/AccesaCodingChallenge.git
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

## Implemented features

This application implements the following features:

* **Best Discounts**: Users can find products with the highest percentage discounts across all stores. For each product is considered the last price entry date.
* **New Discounts**: Lists discounts that have been recently added (e.g., "today" based on their system entry date) and are currently active.
* **Product Recommendations & Substitutes**: For a given product, the application can suggest variations (same name&brand, different packaging options) available in all tracked stores, highlighting the price per standardized unit to help users find the best value. It can also provide broader substitute suggestions if exact variations aren't available.
* **Custom Price Alerts**: Users can set target prices for products. A scheduled task periodically checks if a product's current price meets or falls below the target, notionally triggering an alert and then deactivating it.
* **Filterable Price Data Points**: Provides price and entry dates data for products, which can be filtered by product ID, store ID, category, or brand. 

### API Endpoints:

| Feature                                       | Method | Endpoint                                      | Query Parameters (Required/Optional)             | Request Body (Example for POST/PUT)                     |
| :-------------------------------------------- | :----- | :-------------------------------------------- | :----------------------------------------------- | :------------------------------------------------------ |
| **Discounts** |        |                                               |                                                  |                                                         |
| Best Discounts                                | `GET`  | `/discounts/bestDiscounts`                    | N/A                                              | N/A                                                     |
| Newly Added Discounts                         | `GET`  | `/discounts/newDiscounts`                     | N/A                                              | N/A                                                     |
| **Price History (Filterable Data Points)** |        |                                               |                                                  |                                                         |
| History by Product, Store                   | `GET`  | `/filterPriceEntries/by-store`                | `productId` (Long, required), `storeId` (Long, required) | N/A                                                     |
| History by Product, Store, Brand            | `GET`  | `/filterPriceEntries/by-brand`                | `productId` (Long, req.), `storeId` (Long, req.), `brand` (String, req.) | N/A                                                     |
| History by Product, Store, Category         | `GET`  | `/filterPriceEntries/by-category`             | `productId` (Long, req.), `storeId` (Long, req.), `category` (String, req.) | N/A                                                     |
| **Product Recommendations & Substitutes** |        |                                               |                                                  |                                                         |
| Variations/Substitutes      | `GET`  | `/recommendations/{targetProductId}`            | N/A                                              | N/A                                                     |
| **Price Alerts** |        |                                               |                                                  |                                                         |
| Create Price Alert                            | `POST` | `/alerts/createAlert/{userId}`                  | N/A                                              | `{"userId": 1, "productId": 1, "targetPrice": 10.00}` |
| Get User's Active Alerts                      | `GET`  | `/alerts/{userId}`                              | N/A                                              | N/A                                                     |
