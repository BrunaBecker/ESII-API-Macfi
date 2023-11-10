package com.macfi;

import com.macfi.controllertests.AttendanceControllerTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MacfiApplicationTests {

    @Test
    void contextLoads() {

    }

    @TestConfiguration
    static class MyTestConfig {


    }
}
