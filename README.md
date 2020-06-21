# Getting Started

 
#### start the back end micro-service
   you should have installed maven
 
   open cmd and navigate to /bank-account-kata folder then run the following commands:
  
  ```
    - mvn clean install

    - mvn spring-boot:run
  ```
 

  
#### to access to the database please use the following link
    http://localhost:8080/h2-console/
    port 8080 to update with your port
    
#### start the front-end
 
  navigate to  web folder then run the commands:
  you should install npm and angular CLI
  ```
    - npm install

    - ng serve 
  ```
  #### to access to the interface please use the following link
     http://localhost:4200/
     
     
   #### a postman collection for the rest api is in the resources folder
   
   #### Indication
   this application don't include authentification and session, once we add it we can performe all operations without asking id
   (Account number and Costumer number)
    
