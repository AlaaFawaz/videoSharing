package com.myo.authentication;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Authentication {

    final private static String PATH_OF_LOGIN_FILE = "loginData.txt";

    public static boolean isAuthenticatedUser(String emailAddress, String password) throws IOException {

        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        File file = new File(classLoader.getResource(PATH_OF_LOGIN_FILE).getFile());
        String loginEmailPassword = emailAddress + password;
        Stream<String> linesOfLogin = Files.lines(file.toPath());

        return linesOfLogin.anyMatch(lineOfLogin -> lineOfLogin.equals(loginEmailPassword));
    }
}
