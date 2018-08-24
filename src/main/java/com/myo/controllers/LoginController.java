package com.myo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 *
 */
@Controller
public class LoginController extends HttpServlet {

    final private static String PATH_OF_LOGIN_FILE = "loginData.txt";

    @RequestMapping(value = "/")
    public String getHomePage(HttpServletRequest request,
                              HttpServletResponse response) {
        request.setAttribute("message1", " ");
        request.setAttribute("message2", " ");
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(name = "emailAddress") String emailAddress,
                        @RequestParam(name = "password") String password, HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        if (isAuthenticatedUser(emailAddress, password)) {
            return "dashboard";
        } else {
            request.setAttribute("message1", "Invalid email or password");
            request.setAttribute("message2", "please enter your password and email address again");
            return "index";
        }

    }

    private boolean isAuthenticatedUser(String emailAddress, String password) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(PATH_OF_LOGIN_FILE).getFile());
        String loginEmailPassword = emailAddress + password;
        Stream<String> linesOfLogin = Files.lines(file.toPath());

        return linesOfLogin.anyMatch(lineOfLogin -> lineOfLogin.equals(loginEmailPassword));
    }
}