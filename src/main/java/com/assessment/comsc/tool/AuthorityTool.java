package com.assessment.comsc.tool;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthorityTool {


    /**
     * the method use to get the username which is transferred within the request
     *
     * @return
     */
    public static String getUserName() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            return username;
        } else {
            throw new Exception("please log in");
        }

    }
}
