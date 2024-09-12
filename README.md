# About the app

This is a Point Of Sale (POS) system created using **springboot** and  **react**. In which, a user is capable of adding item into the cart by entering its serial number. Serial number is a unique string which is provided to an item which belongs to a specific item category. 

This is similar to systems that are used in supermarket and shop cashiers , where a user is capable of adding multiple items in to a list and make the sale after adding all the items. 

Any user can make an order by adding list of serial numbers of preferred items. But in order to perform following operations a user need to logged into the system as an authorized user. Those operations are,

- Add new items to the system and update stock status
- Delete an available item
- Add a new item category
- Delete available item category
- Update item details
- Update category name etc.

In here authentication features has been added , using **Spring Security**

Following images shows the appearance of the UI of the app. UI was designed using **react** .

![image](https://github.com/user-attachments/assets/f4dce9fb-cd65-4f03-bd12-b8dc6213adde)

![image](https://github.com/user-attachments/assets/3ee2e0b9-534c-495f-b616-1e741f51141b)

![image](https://github.com/user-attachments/assets/ccb17955-c9b7-43f5-87f8-81c37a75bf23)

![image](https://github.com/user-attachments/assets/b38372e6-a614-4a57-9b29-9c6680b6132e)

## Development of back end

The back end of this application was designed using **springboot** a popular java framework for back end development with layered architecture. 

Following basic configurations were made in spring initializer at project generation stage.

### Dependencies added

- MySQL driver
- Spring JPA
- Lombok
- Spring Web

### Build system

- maven

### Java version

- JDK 17

For this project, intellij was used as the IDE.

In order to add authentication features at logging and register stages of a user, spring security dependency is required to be added. But it was left as a later step , in order to make development of other features more easier and flexible. 

#### Database configurations

Database configurations were made , using 'Application.properties' file, following configurations were made as first step of development process.

```properties
spring.application.name=final  
  
spring.jpa.hibernate.ddl-auto=update  
spring.datasource.url=jdbc:mysql://localhost:3306/point_of_sale?createDatabaseIfNotExist=true  
spring.datasource.username=root  
spring.datasource.password=root  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
  
spring.jpa.show-sql=true
```

In here "?createDatabaseIfNotExist=true " statement is added at the defining stage of data source, so that application creates a database at the initial execution of the springboot application. So creating a data base explicitly is not required.

### Adding classes and directories

In layered architecture, all the classes are included in directories which contain classes that only perform specific tasks. 

Main components of layered architecture

![image](https://github.com/user-attachments/assets/a299d125-d91b-425d-ab4d-f1d84f742358)

Following direcotiries are created in the file directory of the project at initial stage

- controller
- dto
- entity
- repository
- service

Above directories fall into following layers,

- Presentation layer
	- controller 

- Persistence layer 
	- entity
	- repository

- Service layer 
	- service

- Data transfer layer
	- dto

One of other component which is included in presentation layer is UI of this application. In here , it is created as a separate react application in separate repository.

### Adding entity classes

After creating the directory structure, entities for different  table in database is created.   

In springboot, entities represent the mapping between java objects and database tables. This allows to work with database records using oop principles. These are used in the context of ORM, and they are typically annotated with JPA annotations. 

Because an entity class represents a table in the database, each instance of entity represents a row in the table. And field of entity represents a column in the table.

Following annotations are mostly used when an entity class is created,

- @Entity 
		-Marks the class as a JPA entity, which means it will be mapped to a table
- @Table
		- Specifies the table name if it's different from the class name , Otherwise , the class name is used by default.
		- It is very important to not to use reserved keywords as table name, mistakenly usage of reserved keywords as table name leads to error in execution of the app.

- @Id 
		- Marks the primary key field of the entity , corresponding to the primary key of the database table. 

- @GeneratedValue
		- Specifies how the primary key should be generated 

- @Column 
		- Maps a class field to a specific database column. It can also define column attributes (nullable , length , etc.)

- @ManyToOne , @OneToMany , @ManyToMany , @OneToOne 
		- Define the relationship between entities

- @JoinColumn 
		- Specifies the foreign key column in a relationship

### Adding repository interfaces

After creating an entity class for a corresponding table, repository interface relevant to that entity class is created. Adding @Repository annotation marks the interface as a repository.  The repository entity uses entity class to performing CRUD (Create , Read , Update , Delete) operations on the database. The repository interface is extended by 'JpaRepository' so that repository can be implemented by Spring Data JPA. 

Spring Data JPA provides a wide range of pre-built methods that allow developer to perform common database operations without a lot of coding. But in some cases developer may require some operations that can not be done with provided methods. In such situations , developer may create custom methods in repository interface. In order to do that, query languages such as , JPQL (Java Persistance Query Language) , SQL or HQL can be used.

### Adding service classes

In order to add a service class, a service interface is created, in which includes the blueprint of service class. After that , a service class is created implementing the corresponding interface. @Service annotation is used to mark the class as a service In the service class , business logics required to perform database operations are included. CRUD operations are only take place when logical requirements are met.

@Autowired annotation is used in service implementation class to inject an instance of repository interface. This dependency injection is required because it allows to methods in service class  perform operations like , saving , updating , and querying the database.

### Adding controller classes

A controller class acts as a bridge between user interface and service layer. It is responsible for handling HTTP requests and returning responses. Following requests are defined by controller class ,
- GET
- POST
- PUT
- DELETE

HTTP requests are handled through defined API endpoints in the controller class.

In springboot above API requests are mapped using annotations like @GetMapping , @PutMapping , @PostMapping etc.

@RestController is put above the class signature to mark the class as a controller class. Also an instance of service class is injected using @Autowired annotation as a field of controller class. 

After processing a request, the controller returns a response to the client, which contains fetched or processed by the service layer.

### Adding DTO classes

DTO classes are used to transfer data between two layers of the application. In here DTO is used to avoid unnecessary exposure of data of entity classes in client side. And also maintain the simplicity when API requests are sent from client side. 

In order to maintain the simplicity of entity and dto classes , java library called 'lombok ' is used. This allows to auto generate most boiler plate codes making the code more clean and easy to maintain. 

Lombok is also added in the spring initializer as a dependency , or it can be added directly in maven. 

### Authentication configurations

In order to add authentication features to the application, spring security is used. 

This is done by adding spring security dependency in maven Project Object Model (POM) file. 

Following dependencies are added for configure security features,

- spring-boot-starter-security
- jjwt-api
- jjwt-impl
- jjwt-jackson

After adding above dependencies , a secret is generated for the token and it is stored in the 'Application.properties' file. In that file token expiration time is also added in milli seconds. In this case it was set to 86400000 ms. 

To form a security layer, a directory 'security' is created. Inside that directory , a classes called 'UserDetailsService' , 'WebSecurityConfig' and a directory called 'jwt' is created.

In side the directory 'jwt' , following classes are created,
- AuthEntryPoint
- AuthTokenFilter
- JwtUtils

For every class in 'jwt' directory, annotaion '@Component' is added, so that they are marked as components.

Inside the JwtUtils class, both secret and expiration time are added as fields. In that class, following methods are included,

- A method to generate a key using the secret.
- A method to generate  a JwtToken.
- A method to validate jwtToken

Inside 'AuthEntryPoint' class, a method is added to verify whether a user is authorized or not.

Inside 'AuthTokenFilter' , methods are added to filter user requests.

Inside 'UserDetailsService' class, a method is added which returns an object of the user who is trying to send API requests after authentication is completed.

In 'WebSecurityConfig' class annotations '@Configuration','@EnableWebSecurity' annotations are added. 

@Configuration marks WebSecurityConfig class as configuration class for security settings.

@EnableWebSecurity enables Spring Security's ability to enforce security at the method level. This feature is use full when securing individual service or controller methods based on user roles or permissions.

After configuring all the classes in security layer, 'AuthController' class is added in controller layer, which allows user to get authorized by registering and logging. 

At the registering phase, user password is stored in the database as an encrypted code avoiding any exploitations of password. 

After successful logging, user is given a token which should be attached with all the API requests which require an authentication.





