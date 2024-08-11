package com.assessment.comsc.AdminDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.mail.MailException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Display the user view for admin dashboard
     *
     * @return String representing the view name in templates - adminDashboard
     */
    @GetMapping("/Dashboard")
    public String dashboardView() {
        return "adminDashboard";
    }

    /**
     * Display the user view for redering side navigation bar page.
     *
     * @return String representing the view name in templates - sideNavBar
     */
    @GetMapping("/sideNav")
    public String sideNavBarView() {
        return "sideNavBar";
    }

    /**
     * Display the user view to render the navigation element pages.
     *
     * @return String representing the view name in templates - sideNavBar
     */
    @GetMapping("/sideNav/{section}")
    public String loadSection(@PathVariable String section) {
        if ("file".equals(section)) {
            return "upload";
        }

        if ("users".equals(section)) {
            return "usersGrid";
        }

        return "adminDashboard";
    }

  /**
     * Display the user view for rendering assessment details page
     *
     * @return String representing the view name in templates - assessmentDetails
     */
    @GetMapping("/assessment")
    public String assessmentview() {
        return "assessmentDetails";
    }

     /**
     * Handles the POST request to send an email
     *
     * @return response ody message if successfull or not
     */
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Email email) {
        try {
            // Retireve the properties
            SimpleMailMessage emailBody = new SimpleMailMessage();
            emailBody.setTo(email.getMailto());
            emailBody.setSubject(email.getSubject());
            emailBody.setText(email.getBody());

            // Send the email
            javaMailSender.send(emailBody);
            System.out.println("Email sent successfully");

            return ResponseEntity.ok("Email sent successfully");
        } catch (MailException e) {
            // Handle exception and return an error response
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email");
        }
    }
}
