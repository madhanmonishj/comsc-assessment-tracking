package com.assessment.comsc.controller;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            // Setting Error Messages
            String errorMessage = "Incorrect password. Please try again.";

            // Send error messages to the front end
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(errorMessage);
        }
    }


