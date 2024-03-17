package com.devsu.account.config;

import com.devsu.account.boot.AccountApplication;
import com.devsu.account.boot.configuration.DbConfiguration;
import com.devsu.account.boot.configuration.RestTemplateConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {AccountApplication.class, DbConfiguration.class,
    RestTemplateConfiguration.class})
@ActiveProfiles({"test"})
@TestConfiguration
public abstract class ApplicationAbstractIT {

}