package com.devsu.account.config;

import com.devsu.account.boot.Application;
import com.devsu.account.boot.configuration.DbConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class, DbConfiguration.class})
@ActiveProfiles({"test"})
@TestConfiguration
public abstract class ApplicationAbstractIT {

}