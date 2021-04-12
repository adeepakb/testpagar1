package com.example.restApi;

import io.cucumber.junit.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;




@ContextConfiguration(classes = CucumberRunnerTest.class, loader = SpringBootContextLoader.class)
@CucumberContextConfiguration
@SpringBootTest(classes = RestApiApplication.class)
class RestApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
