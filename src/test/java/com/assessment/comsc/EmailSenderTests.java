package com.assessment.comsc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.assessment.comsc.AdminDashboard.AdminController;
import com.assessment.comsc.AdminDashboard.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmailSenderTests {

    @Autowired
    private AdminController adminController;
    private static Email emailContents;

    // Set email content before running the tests
    @BeforeAll
    public static void before() {
        emailContents = new Email(
                "team13project987@gmail.com",
                "JUnit Testing",
                "This is a test Email to verify if the mail is received by the receiver");
    }

    // Test case for email success
    @Test
    public void successEmailTest() {
        System.out.println("Entering Successful Email Unit Testing");

        // Send the email and get the response
        ResponseEntity<String> response = adminController.sendEmail(emailContents);

        // Check if the response indicates success
        assertEquals("Email sent successfully", response.getBody());
        System.out.println("Exiting Successful Email Unit Testing");
    }

    // Test case for email failure
    @Test
    public void failureEmailTest() {
        System.out.println("Entering Failed Email Unit Testing");

        // Simulate a failed case by providing incorrect email address
        Email incorrectEmailContents = new Email(
                "incorrectemail.com",
                "JUnit Testing",
                "This is a test Email to verify if the mail is received by the receiver");
        ResponseEntity<String> response = adminController.sendEmail(incorrectEmailContents);

        // Check for the internal server error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Check for body message
        assertTrue(response.getBody().contains("Failed to send email"));
        System.out.println("Exiting Failed Email Unit Testing");
    }
}
