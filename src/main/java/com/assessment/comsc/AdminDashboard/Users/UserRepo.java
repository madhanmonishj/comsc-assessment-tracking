package com.assessment.comsc.AdminDashboard.Users;
import java.util.List;

/**
 * Interface defining the contract for accessing user-related CRUD operations.
 */
public interface UserRepo {
    List <User> getAllUsers();

    User saveUser(User user);

    User updateUser(User user);

    List<User> getUser(int userId);

    List<Assessment> getAssessment(int trackerId);

    Tracker updateTracker(Tracker tracker);
}