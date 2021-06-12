package com.config.template;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.helpers.RequestHelper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTemplate  {

    @LocalServerPort
    protected int port;

    protected RequestSpecification requestSpecification;

    @Autowired
    protected RequestHelper requestHelper;


    @BeforeEach
    void init() {
        requestSpecification = requestHelper.createRequestSpecification(port);
    }
}
