package com.myo.controllers;

import com.myo.authentication.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class VideosListController extends HttpServlet {

    final static private String PATH = "/tmp/uploadFiles";

    @RequestMapping(value = "/getVideosList")
    public String getVideosList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String emailAddress = (String) request.getSession().getAttribute("emailAddress");
        if (emailAddress == null) {
            emailAddress = (String) request.getAttribute("emailAddress");
        }

        String password = (String) request.getSession().getAttribute("password");
        if (emailAddress == null) {
            password = (String) request.getAttribute("password");
        }

        boolean isAuthenticated = Authentication.isAuthenticatedUser(emailAddress, password);
        if (isAuthenticated) {
            File directory = new File(PATH);
            if (!directory.exists()) {
                directory.mkdir();
            }

            File[] filesList = directory.listFiles();
            request.setAttribute("files", filesList);

            return "dashboard";
        } else {
            response.sendRedirect("/");
        }
        return null;
    }
}
