package com.example.petProject;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@Testable
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public abstract class SpringTestRunner {
    @LocalServerPort
    protected String port;

    @Test
    void contextLoads() {
        RestAssured.post("http://localhost:" + port + "/del").statusCode();
    }

}
