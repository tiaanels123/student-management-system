package com.example.studentmanagement;

import com.example.studentmanagement.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {UserManagementSystemApplication.class, TestConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
class UserManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}
}
