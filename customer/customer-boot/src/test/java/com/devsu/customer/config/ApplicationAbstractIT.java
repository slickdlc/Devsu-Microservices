package com.devsu.customer.config;

import com.devsu.customer.boot.CustomerApplication;
import com.devsu.customer.boot.configuration.DbConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {CustomerApplication.class, DbConfiguration.class})
@ActiveProfiles({"test"})
@TestConfiguration
public abstract class ApplicationAbstractIT {

}