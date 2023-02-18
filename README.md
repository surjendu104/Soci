# Soci
This a complete API that can be easily integrated in the backend of any blogging social media application

## How to start

```bash 
# Frist clone the repository  
git clone https://github.com/surjendu104/Soci.git
```
### Once repo cloned into your local mechine do the following changes .......
#### Go to application.properties in src/main/resources/application.properties
```bash
# change the port number according to your choice
server.port=<port_number>
```
```bash
# change the spring.datasource.url= your database compitable jdbc connection like the following
spring.datasource.url=jdbc:mysql://localhost:port_number/schema_name (e.g. jdbc:mysql://localhost:3306/soci_app_apis)
``` 
```bash 
# Give the spring.datasource.username= to your schema username
spring.datasource.username=<schema_username>

```
```bash
# Give the spring.datasource.password= your schema password
spring.datasource.password=<schema_password>
```
```bash
# Give your driver class name in the field spring.datasource.dbcp2.driver-class-name= like following
spring.datasource.dbcp2.driver-class-name=com.mysql.cj.jdbc.Driver
```
#### Finally run your application by ``mvn spring-boot:run``

#### -- Final EER Dialgram
![EER](./Final%20EER%20Image.png)

#### -- Access to swagger ui
```bash
# In your browser go here
http://localhost<port_number>/swagger-ui.index.html
```

### Troubleshoot
1. JDBC Doumentation [JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/)
2. Check out this for run related queries
[Troubleshooting run](https://www.javaguides.net/2019/05/run-spring-boot-app-from-command-line.html)
3. Check here for driver class docs [datasource doc](https://docs.spring.io/spring-boot/docs/1.3.0.M2/reference/html/boot-features-sql.html)
