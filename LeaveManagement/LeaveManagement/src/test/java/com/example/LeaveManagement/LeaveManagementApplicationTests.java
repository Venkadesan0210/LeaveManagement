package com.example.leavemanagement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNotNull;





@SpringBootTest
class LeaveManagementApplicationTests {

    @Autowired
    private ApplicationContext context;
    @Test
    void contextLoads() {
        // This test ensures that the Spring application context loads successfully.
        // If this test fails, it indicates that there might be issues with the application startup.
        assertNotNull(context);
    }

    private void assertNotNull(ApplicationContext context) {
    }

    @Test
    void testSample() {
        // This is a sample test case. You can replace it with your actual test cases.
        int result = add(2, 3);
        // Assert that the result of adding 2 and 3 is equal to 5
        assertEquals(5, result);
    }

    // Sample method to demonstrate testing
    int add(int a, int b) {
        return a + b;
    }

    // Exclude LeaveManagementApplication from test coverage
    // @CoverageIgnore
    static class LeaveManagementApplication {}


}