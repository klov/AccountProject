package com.example.StudyProject.tools;

import com.example.StudyProject.service.Application;
import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;

@ContextConfiguration(classes = {Application.class})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT/*,
        properties = ["spring.data.mongodb.uri=mongodb://127.0.0.1:27017/redsquaretest"]*/
)
public class BaseTest {
    @ClassRule
    public static GenericContainer postgresContainer = new  GenericContainer("postgres:13").withExposedPorts(27017);


    @Autowired
    BaseApi api;


    public void setup() {
        api.setup();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            postgresContainer.start();
        }
    }

}
