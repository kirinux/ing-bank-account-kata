# Getting Started


#### Launch the micro-service
 
  open cmd and navigate to /bank-account-kata folder then run the following commands:
  
  ```
    - mvn clean install

    - mvn spring-boot:run
  ```


  concerning app security, the application allow requests only from local ip address like:
  [192.168.1.37, 0:0:0:0:0:0:0:1 (local ipv6)]. Implementation can be found on WebSecurity class.
  
#### Launch the ui
 
  navigate to /ui folder then run the following commands:
  
  ```
    - npm install

    - ng serve
  ```
