# service-boot-archetypes based on spring-boot 3.2.0 tested with java 21 
Maven Archetype for create the base of spring boot service with base swagger configurated and configuration base for Model Mapper  .

for use in local repository clone project and  launch the command

* `mvn clean install`


On build successful , look in your .m2 directory if file jar was created. (.m2/repository/it/service/spring-boot-archetype-3x/1.0-SNAPSHOT/).
Now is possible gerate project!

# Create Services project by Archetype

Is possible generate  the project by maven command line or by a  IDE like Intellij Idea or Eclipse.
Here we use only maven command line.

For  IntelliJ Idea https://plugins.jetbrains.com/plugin/7965-maven-archetype-catalogs
and https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project

For Eclipse please use wizard for new project , I can not find documentation on it :-(

# Maven Build

*  `mvn archetype:generate -B  -DarchetypeGroupId=it.service   -DarchetypeArtifactId=spring-boot-archetype-3x  -DarchetypeVersion=1.1-SNAPSHOT   -DgroupId=com.company   -DartifactId=projectName  -Dversion=1.0-SNAPSHOT   -Dpackage=com.company.project`



# jacoco plugin
For covered test code version 0.8.11 after junit test run you can find covered source under directory
<project-directory>>\target\site\jacoco. Plugin create file report  index.html with statistics .
Added new file properties for test profile.



# postgreSQL and Mysql connection  Flyway plugin for migrate with 3 profiles
When project is create from the Archetype execution in directory flyWay.config/mySqlConfig.properties you can push
your flyWay properties file .
By default, exist 3 file mySqlConfig.properties for MySql ,  postgreSqlConfig.properties and h2.properties (in memory) 
the properties for JPA and Hibernate connection are in directory resources properties are stored 
in application-<PROFILE>.properties files :

> ### DB -MySql
>  #spring.datasource.url=jdbc:mysql://localhost:3306/localTest?serverTimezone=UTC
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.properties.hibernate.format_sql=true


>### DB -postgresSql
>   spring.datasource.url=jdbc:postgresql://localhost:5432/localTest?ApplicationName=localTest
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.properties.hibernate.format_sql=true

>### DB -H2 (in memory)
>spring.datasource.url=jdbc:h2:mem:testdb:8082
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true

Comment or uncomment the properties for your db :

For add new properties db add a block in application-<PROFILE>.properties and add profile in pom.xml of your application
and add file properties in flyWay.config example :

For Oracle in pom.xml in section <dependencies> </dependencies> add

    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbcXXXX</artifactId>
        <scope>runtime</scope>
    </dependency>

in pom.xml section profile create :

    <profile>
            <id>oracle-migration</id>
            <properties>
                <fileFlyWayConfig>src/main/resources/flyWay.config/oracleConfig.properties</fileFlyWayConfig>
            </properties>
    </profile>
Add file oracleConfig.properties in directory flyWay.config.

for migrate db by command line (example for mySql but same command for others db ):

    mvn flyway:migrate -P mySql-migration

for start service create :

    java -jar <jourApplication>.jar --spring.profiles.active=local

after service start you can see port and context path in your console :

>2023-12-07 14:25:44,833 INFO  [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer: Tomcat started on **port(s): 8082 (http)** with **context path '/testArchetipo4'**
>2023-12-07 14:25:44,835 INFO  [main] org.springframework.boot.StartupInfoLogger: Started Application in 16.796 seconds (JVM running for 17.424)

Swagger documentation on :
> 'http://localhost:<your_port>/**<your_context_path>**/swagger-ui.html