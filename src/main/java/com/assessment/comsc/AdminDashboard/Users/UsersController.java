package com.assessment.comsc.AdminDashboard.Users;

import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import com.assessment.comsc.AdminDashboard.Users.UserRepo;

@Controller
@RequestMapping("/Users")
public class UsersController {

    @Autowired
    private UserRepo userRepo;

    /**
     * Display the user grid view.
     *
     * @return String representing the view name in templates - usersGrid
     */
    @GetMapping("/grid")
    public String gridView() {
        return "usersGrid";
    }

    /**
     * Display the user view for adding/editing users.
     *
     * @return String representing the view name in templates - addUser
     */
    @GetMapping("/userview")
    public String userView() {
        return "addUser";
    }

    /**
     * Get user details filtered by user ID.
     *
     * @param userId The ID of the user to retrieve
     * @return List of User objects representing user details
     */
    @GetMapping("/getUser")
    @ResponseBody
    public List<User> getUserView(@RequestParam("userID") int userId) {
        return userRepo.getUser(userId);
    }

    /**
     * Get a list of all users.
     *
     * @return List of User objects of all users
     */
    @GetMapping("/lists")
    @ResponseBody
    public List<User> getUserData() {
        return userRepo.getAllUsers();
    }

    /**
     * Save or update a user based on the provided User object.
     *
     * @param user The User object to be saved or updated
     */
    @PostMapping("/saveUser")
    public String submitForm(@RequestBody User user) {

        // Check if the userId is 0 to determine whether to save or update the user
        if (user.getId() == 0) {
            userRepo.saveUser(user);
        } else {
            userRepo.updateUser(user);
        }

        return "addUser";
    }

    /**
     * Get assessment details filtered by tracker ID.
     *
     * @param trackerId The ID of the tracker to retrieve assessment details
     * @return List of Assessment objects representing assessment details
     */
    @GetMapping("/getassessment")
    @ResponseBody
    public List<Assessment> getAssessmentView(@RequestParam("trackerId") int trackerId) {
        return userRepo.getAssessment(trackerId);
    }

    /**
     * Update a tracker based on the provided Tracker object.
     *
     * @param tracker The Tracker object to be updated
     */
    @PostMapping("/updateTracker")
    public String updateTracker(@RequestBody Tracker tracker) {

        // Update the tracker based on the provided object
        userRepo.updateTracker(tracker);
        
        return "addUser";
    }
}
