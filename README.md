### 起步
#### 什么是 spring cloud task

> Spring Cloud Task makes it easy to create short lived microservices. We provide capabilities that allow short lived JVM processes to be executed on demand in a production environment.

Spring Cloud Task 是为了实现短生命周期的、定时执行的、不需要被重新启动的轻量级应用架构。

### 创建 Demo

demo 已发布 https://github.com/Theembers/spring-cloud-task 可以下载参考
1.首先需要创建一个 springBoot 项目

2.除此以外需要加入数据库依赖,Spring Cloud Task 支持主流数据库（H2、HSQLDB、MySql、Oracle、Postgres）我们以mysql为例

```
<!--数据库依赖引入-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>1.5.7.RELEASE</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
3.引入task依赖
```
 <!--spring cloud task 依赖引入-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-task-core</artifactId>
    <version>1.0.0.BUILD-SNAPSHOT</version>
</dependency>
```
4.在application.properties中配置数据库信息
```
#DB Configuration:
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/testdb
spring.datasource.username = root
spring.datasource.password = root

#JPA Configuration:
spring.jpa.database=MySQL
spring.jpa.show-sql=true  
spring.jpa.generate-ddl=true  
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.naming_strategy=org.hibernate.cfg.ImprovedNamingStrategy  
#spring.jpa.database=org.hibernate.dialect.MySQL5InnoDBDialect
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MYSQL5Dialect
```

5.编写你的Application
```
@SpringBootApplication
@EnableTask
public class SpringCloudTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTaskApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return new TestCommandLineRunner();
    }

    public static class TestCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String... strings) throws Exception {
            System.out.println("this is a Test about spring cloud task.");
            try{
                List<String> list = new ArrayList<>();
                list.get(1);
            }catch (Exception e){
                System.out.println("Error");
                throw e;
            }
        }
    }
}
```

注意： 以上代码中通过异常的形式故意通过list.get(1)抛出，便于在数据库中可以看到一些数据产生。

6.现在可以通过运行main方法启动你的demo，以下是控制台输出：

```

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)

2017-09-25 17:02:43.349  INFO 11592 --- [           main] c.x.s.SpringCloudTaskApplication         : Starting SpringCloudTaskApplication on XinLing with PID 11592 (D:\workspaces\Java\spring-cloud-task\target\classes started by theem in D:\workspaces\Java\spring-cloud-task)
2017-09-25 17:02:43.351  INFO 11592 --- [           main] c.x.s.SpringCloudTaskApplication         : No active profile set, falling back to default profiles: default
2017-09-25 17:02:43.383  INFO 11592 --- [           main] s.c.a.AnnotationConfigApplicationContext : Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@11c20519: startup date [Mon Sep 25 17:02:43 CST 2017]; root of context hierarchy
2017-09-25 17:02:43.938  INFO 11592 --- [           main] j.LocalContainerEntityManagerFactoryBean : Building JPA container EntityManagerFactory for persistence unit 'default'
2017-09-25 17:02:43.947  INFO 11592 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [
	name: default
	...]
2017-09-25 17:02:43.988  INFO 11592 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate Core {5.0.12.Final}
2017-09-25 17:02:43.989  INFO 11592 --- [           main] org.hibernate.cfg.Environment            : HHH000206: hibernate.properties not found
2017-09-25 17:02:43.989  INFO 11592 --- [           main] org.hibernate.cfg.Environment            : HHH000021: Bytecode provider name : javassist
2017-09-25 17:02:44.016  INFO 11592 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.0.1.Final}
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
Mon Sep 25 17:02:44 CST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
2017-09-25 17:02:44.307  INFO 11592 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL5Dialect
2017-09-25 17:02:44.406  INFO 11592 --- [           main] org.hibernate.tool.hbm2ddl.SchemaUpdate  : HHH000228: Running hbm2ddl schema update
2017-09-25 17:02:44.422  INFO 11592 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2017-09-25 17:02:44.514  INFO 11592 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executing SQL script from class path resource [org/springframework/cloud/task/schema-mysql.sql]
2017-09-25 17:02:44.532  INFO 11592 --- [           main] o.s.jdbc.datasource.init.ScriptUtils     : Executed SQL script from class path resource [org/springframework/cloud/task/schema-mysql.sql] in 17 ms.
2017-09-25 17:02:44.636  INFO 11592 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-09-25 17:02:44.640  INFO 11592 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Starting beans in phase 0
this is a Test about spring cloud task.
Error
2017-09-25 17:02:44.675  INFO 11592 --- [           main] utoConfigurationReportLoggingInitializer : 

Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
2017-09-25 17:02:44.687  INFO 11592 --- [           main] s.c.a.AnnotationConfigApplicationContext : Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@11c20519: startup date [Mon Sep 25 17:02:43 CST 2017]; root of context hierarchy
2017-09-25 17:02:44.688  INFO 11592 --- [           main] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 0
2017-09-25 17:02:44.689  INFO 11592 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
2017-09-25 17:02:44.689  INFO 11592 --- [           main] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2017-09-25 17:02:44.698 ERROR 11592 --- [           main] o.s.boot.SpringApplication               : Application startup failed

java.lang.IllegalStateException: Failed to execute CommandLineRunner
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:735) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	at org.springframework.boot.SpringApplication.callRunners(SpringApplication.java:716) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	at org.springframework.boot.SpringApplication.afterRefresh(SpringApplication.java:703) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:304) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1118) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1107) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	at com.xz.springCoudTask.SpringCloudTaskApplication.main(SpringCloudTaskApplication.java:17) [classes/:na]
Caused by: java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
	at java.util.ArrayList.rangeCheck(ArrayList.java:653) ~[na:1.8.0_91]
	at java.util.ArrayList.get(ArrayList.java:429) ~[na:1.8.0_91]
	at com.xz.springCoudTask.SpringCloudTaskApplication$TestCommandLineRunner.run(SpringCloudTaskApplication.java:31) ~[classes/:na]
	at org.springframework.boot.SpringApplication.callRunner(SpringApplication.java:732) [spring-boot-1.5.7.RELEASE.jar:1.5.7.RELEASE]
	... 6 common frames omitted


Process finished with exit code 1
```

7.以下表会在第一次成功执行后创建。
![clipboard.png](/img/bVVLGy)

在task_execution中也可以看到执行记录，包括错误信息
![clipboard.png](/img/bVVLHk)

### TODO逐一整理关于配置、管理与其他组件协调相关
