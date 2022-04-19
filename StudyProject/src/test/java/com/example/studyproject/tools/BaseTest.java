package com.example.studyproject.tools;

import com.example.studyproject.StudyProjectApplicationTests;
import com.example.studyproject.repository.buss.AccountRepository;
import com.example.studyproject.service.model.Account;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;

@ContextConfiguration(classes = {StudyProjectApplicationTests.class})
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureMockMvc
public class BaseTest {
    @ClassRule
    public static GenericContainer postgresContainer = new  GenericContainer("postgres:13").withExposedPorts(27017);

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

    public Account createAccount(String uuid, int value){

    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgresContainer.start();
        }
    }

}
