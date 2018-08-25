package com.myo.controllers;

import com.myo.authentication.Authentication;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
@Controller
public class GetVideosController extends HttpServlet {


    @RequestMapping(value = "/loadVideoFile", method = RequestMethod.GET)
    @ResponseBody
    public void loadVideoFile(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "path") String path,
                              @RequestParam(name = "fileName") String fileName) throws IOException {

        boolean isAuthenticated = Authentication.isAuthenticatedUser(request);
        if (isAuthenticated) {

            try {
                if (!path.isEmpty()) {
                    int fileSize = (int) new File(path).length();
                    response.setContentLength(fileSize);
                    response.setContentType("video/quicktime");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    FileInputStream inputStream = new FileInputStream(path);
                    ServletOutputStream outputStream = response.getOutputStream();
                    IOUtils.copy(inputStream, outputStream);
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly(outputStream);
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            } catch (java.io.FileNotFoundException e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
            } catch (Exception e) {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        } else {
            response.sendRedirect("/");
        }
    }
}

