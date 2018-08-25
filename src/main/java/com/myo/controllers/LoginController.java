package com.myo.controllers;

import com.myo.authentication.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 */
@Controller
public class LoginController extends HttpServlet {

    @RequestMapping(value = "/")
    public String getHomePage(HttpServletRequest request) {
        request.setAttribute("message1", " ");
        request.setAttribute("message2", " ");
        return "index";
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String emailAddress = request.getParameter("emailAddress");
        String password = request.getParameter("password");
        if (Authentication.isAuthenticatedUser(request)) {
            HttpSession session = request.getSession(false);
            session.setAttribute("emailAddress", emailAddress);
            session.setAttribute("password", password);
            response.sendRedirect("/getVideosList");
        } else {
            request.setAttribute("message1", "Invalid email or password");
            request.setAttribute("message2", "please enter your password and email address again");
            return "index";
        }
        return "index";
    }
}