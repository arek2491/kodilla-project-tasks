package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminConfigTestSuite {

    @Autowired
    AdminConfig adminConfig;

    @Test
    public void getAdminConfig() {
        String testAdminMail = adminConfig.getAdminMail();

        assertEquals("arkadiusz.matysiak@gmail.com", testAdminMail);
    }
}
