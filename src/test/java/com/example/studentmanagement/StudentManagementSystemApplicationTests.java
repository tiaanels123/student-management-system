package com.example.studentmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Test class for the main application.
 */
@SpringBootTest
@Import(TestConfig.class)
class StudentManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}
}
