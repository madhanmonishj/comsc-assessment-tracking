
package com.assessment.comsc;

import com.assessment.comsc.AdminDashboard.Users.Assessment;
import com.assessment.comsc.AdminDashboard.Users.User;
import com.assessment.comsc.AdminDashboard.Users.UsersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UsersTests {

    @Autowired
    private UsersController usersController;

    // get single user data testcase
    @Test
    public void getUserTest() {
        System.out.println("Entering getUserView unit testing");
        List<User> user = usersController.getUserView(2);

        // Check if the user list is not null
        assertNotNull(user);

        // Check if the user list return only one element
        assertEquals(true, user.size() > 0);

        // Print the contents of each user
        for (User obj : user) {
            System.out.println(obj);
        }

        System.out.println("Exiting usersList unit testing");
    }

    // get single aseesment data testcase
    @Test
    public void getAssessmentTest() {
        System.out.println("Entering getAssessmentView unit testing");
        List<Assessment> assessment = usersController.getAssessmentView(1);

        // Check if the assessment list is not null
        assertNotNull(assessment);

        // Check if the assessment list return only one element
        assertEquals(true, assessment.size() == 1);

        // Print the contents of each assessment
        for (Assessment value : assessment) {
            System.out.println(value);
        }

        System.out.println("Exiting getAssessmentView unit testing");
    }

    // get all users testcase
    @Test
    public void getAllUsersTest() {
        System.out.println("Entering get all users unit testing");
        List<User> users = usersController.getUserData();

        // Check if the user list is not null
        assertNotNull(users);

        // Check if the user list contains atleast one user
        assertEquals(true, users.size() > 0);

        // Print the contents of each user
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("Exiting getUserData unit testing");
    }
}

