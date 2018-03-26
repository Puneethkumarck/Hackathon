# Hackathon

This repoitory contains my solution for hackathon organised by my company for SpringBoot.

Below is the Hackthon Problem detial
====================================
### Easy Fashions is a very small online eCom unit that sells clothing and accessories. Rest APIs are built on Spring boot framework to address the below mentioned functions. Write Unit Test cases using Junit , MockMVC and Mockito. 

#### 16 hours duration to solve the problem hosted in hacker rank


### Function related to Products, Brands and Categories
- Load Master data to database
- Get all Products available in the db
- Get all Brands available in the db
- Get all Categories available in the db
- Get all Stores available in the db
- Retrieve Product details by ID
- Retrieve brand details by ID
- Retrieve category details by ID
- Retrieve Product details for a specific Brand from db
- Retrieve Product details for a specific Category from db
- Delete  Product data from db
- Insert new product to db
- Update product details in db
- Insert new brand to db
- Update brand details in db
- Insert new category to db
- Update category details in db
 

### Functions related to Cart , Savings, Offers 

- Add product items to cart
- Delete cart Items
- Retrieve all cart items in a cart for a specific user
- Get Total Savings on all cart items for a User
- Update Cart items in a cart for a User
 

### Pricing policies
These policies are defined based on the offers redeemed. Offers/ Discounts Rules are defined at a Product level along with the order of priority. 
Some of the pricing policies used are 

- Bundle Pricing 
- Group Pricing
- Degressive Pricing
- Promotion Pricing
 

### Database Used

In memory H2 database is configured as an internal testing db. Some of the Entities are 

- User
- Product
- Brand
- Category
- Store
- Offer
- Cart
- CartItem
 

### Test data
Test data for Master tables can be loaded from json files

- product.json
- brand.json
- category.json
- store.json
- user.json
 
### Instructions for Starting a Web Server and Viewing the App

For Starting a webserver of your application click on Project ---> Run 
For viewing your application click on Preview App
 

### Instructions for Running Test Cases

Code coverage and quality reports are generated as a part of Maven Install command.
Click on Project --> Test to rungenerage the test coverage and quality reports.
Coverage Report is available in /target/site/Jacoco folder. 
Code checkstyle report can be viewed in /target/checkstyle-result.xml. 
Note : Any syntax errors or invalid statements or failures in your test cases, will stop generating above mentioned test report files.
 

### Parameters considered for Scoring

Unit Test Cases
Code Coverage
Code quality
Instructions
Complete the project in the editor by writing the necessary code to satisfy the given requirements and pass all tests.
Install build dependencies by clicking Project > Install.
Run code by clicking Project > Run.
Run tests by clicking Project > Test.
If the project exposes a web service, view the application website by clicking Project > Visit App.
