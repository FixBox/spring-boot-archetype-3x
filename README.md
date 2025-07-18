# service-boot-archetypes based on spring-boot 3.2.0 tested with java 21 
Maven Archetype for create the base of spring boot service with base swagger configurated and configuration base for Model Mapper  .

for use in local repository clone project and  launch the command

* `mvn clean install`


On build successful , look in your .m2 directory if file jar was created. (.m2/repository/it/service/spring-boot-archetype-3x/1.0-SNAPSHOT/).
Now is possible gerate project!

# Create Service project by Archetype

Is possible generate  the project by maven command line or by a  IDE like Intellij Idea or Eclipse.
Here we use only maven command line.

For  IntelliJ Idea https://plugins.jetbrains.com/plugin/7965-maven-archetype-catalogs
and https://www.jetbrains.com/help/idea/maven-support.html#create_new_maven_project

For Eclipse please use wizard for new project , I can not find documentation on it :-(

# Maven Build

*  `mvn archetype:generate -B  -DarchetypeGroupId=it.service   -DarchetypeArtifactId=spring-boot-archetype-3x  -DarchetypeVersion=1.2-SNAPSHOT   -DgroupId=com.company   -DartifactId=projectName  -Dversion=1.0-SNAPSHOT   -Dpackage=com.company.project`



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

# postgreSQL and Mysql connection Flyway plugin for reverse engineering  db object from db 
From the version 1.2-SNAPSHOT is possible create Entity , DAO , DDL schema ) from DB using hibernate-tools-maven .
For every db exist directory with specific configuration (hibbernate.properties and reveng.xml) for example for mySql
under directory revegHmb.config exist directory mySql same for others db

## hibernate.properties for MySql basic configuration 
>######## HIBERNATE TOOL CONFIGURATION# ###########
>hibernate.connection.driver_class=com.mysql.cj.jdbc.Driver
> 
>hibernate.connection.url=jdbc:mysql://localhost:3306/test?serverTimezone=UTC
> 
>hibernate.connection.username=root
> 
>hibernate.connection.password=rootmysql
> 
>hibernate.show_sql=true
> 
>hibernate.format_sql=true
> 
>hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
> 

## reveng.xml for MySql basic configuration this file set the filter for revert engineering. (it is possible different DB will be have different configurations ):
    <hibernate-reverse-engineering>
        <type-mapping>
            <sql-type jdbc-type="VARCHAR" hibernate-type="string"/>
        </type-mapping>
        <table
                match-catalog="test"
                match-schema="test"
                match-name="flyway_schema_history"
                exclude="true"
                package="org.test.jpa.models"
        />
        <table-filter
                match-catalog=".*"
                match-schema=".*"
                match-name="TEST_.*"
                exclude="false"
                package="org.generate.jpa.models"
        />
    </hibernate-reverse-engineering>

## run Entities generation 
    mvn hibernate-tools:hbm2java -P mySql-migration

## run DAO generation:
    mvn hibernate-tools:hbm2dao -P mySql-migration

## run DDL generation 
    mvn hibernate-tools:hbm2ddl -P mySql-migration
   
The destination output directory is set in pom.xml in plugin property:

    <outputDirectory>${project.build.directory}/generated-sources</outputDirectory>

The Entities generation , DAO and DLL are separate from the every build or rn maven build , it is a my choice for work 
in dev generation and avoid involuntary db access.

for start service create :

    java -jar <jourApplication>.jar --spring.profiles.active=local

after service start you can see port and context path in your console :

>2023-12-07 14:25:44,833 INFO  [main] org.springframework.boot.web.embedded.tomcat.TomcatWebServer: Tomcat started on **port(s): 8082 (http)** with **context path '/testArchetipo4'**
>2023-12-07 14:25:44,835 INFO  [main] org.springframework.boot.StartupInfoLogger: Started Application in 16.796 seconds (JVM running for 17.424)

Swagger documentation on :
> 'http://localhost:<your_port>/**<your_context_path>**/swagger-ui.html

<b>This archetype is released under the GNU General Public License v3 (GPLv3).
Projects generated using this archetype are not automatically subject to the same license, unless they include substantial portions of the code from the archetype itself.</b>