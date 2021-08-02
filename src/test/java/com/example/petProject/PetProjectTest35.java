package com.example.petProject;

import io.restassured.RestAssured;
import io.restassured.internal.http.HTTPBuilder;
import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.IOException;

class PetProjectTest35 extends SpringTestRunner {

    @Test
    void contextLoads96() {
       String string =  RestAssured.get("http://localhost:" + port + "/getAll").asString();
        Assert.notNull(string);
    }

}
