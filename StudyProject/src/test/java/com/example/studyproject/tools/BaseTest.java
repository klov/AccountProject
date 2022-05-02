package com.example.studyproject.tools;

import com.example.studyproject.StudyProjectApplicationTests;
import com.example.studyproject.repository.AccountRepository;
import com.example.studyproject.service.model.Account;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StudyProjectApplicationTests.class})
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@SpringBootTest(
        classes = SpringBootApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class BaseTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public DataSource postgresDataSource(){

            HikariConfig config = new HikariConfig();
            config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
            config.setUsername(POSTGRES_USER);
            config.setPassword(POSTGRES_PASSWORD);
            config.addDataSourceProperty("databaseName", POSTGRES_DATABASE);
            config.addDataSourceProperty("serverName", "localhost");
            HikariDataSource ds = new HikariDataSource(config);
            return ds;
        }
    }

    public static final String POSTGRES_USER = "postgres";
    public static final String POSTGRES_DATABASE = "account";
    public static final String POSTGRES_PASSWORD = "postgres";
    public static final int DATABASE_PORT = 27017;
    @ClassRule
    public static GenericContainer postgresContainer = new PostgreSQLContainer("postgres:13")
            .withDatabaseName(POSTGRES_DATABASE)
            .withUsername(POSTGRES_USER)
            .withPassword(POSTGRES_PASSWORD)
            .withExposedPorts(DATABASE_PORT);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    BaseApi api;

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @Value("${test.topic}")
    private String topic;

    public void setup() {
        api.setup();
    }

    public void cleanDb(){
        accountRepository.deleteAll();
    }

    public Account createAccount(UUID uuid, long value){
        return accountRepository.create(uuid, value);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgresContainer.start();
        }
    }

}
