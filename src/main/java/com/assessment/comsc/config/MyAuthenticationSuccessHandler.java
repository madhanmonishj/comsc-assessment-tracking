package com.assessment.comsc.config;

import com.assessment.comsc.entity.UserInfo;
import com.assessment.comsc.role.Role;
import com.assessment.comsc.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private final UserInfoService userInfoService;

    @Autowired
    public MyAuthenticationSuccessHandler(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        String mail = request.getParameter("username");

        UserInfo userInfoByMail = userInfoService.getUserInfoByMail(mail);
        String urlByRoleCode = Role.getUrlByRoleCode(userInfoByMail.getRole(), userInfoByMail.getId());
        response.sendRedirect(urlByRoleCode);
    }
}
