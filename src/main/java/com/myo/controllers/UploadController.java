package com.myo.controllers;

import com.myo.authentication.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@WebServlet("/UploadController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@Controller
public class UploadController extends HttpServlet {

    private static final String SAVE_DIR = "/tmp/uploadFiles";

    @RequestMapping(value = "/uploadForm", method = GET)
    public String uploadFile() {
        return "upload";
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        boolean isAuthenticated = Authentication.isAuthenticatedUser(request);
        if (isAuthenticated) {
            File fileSaveDir = new File(SAVE_DIR);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                fileName = new File(fileName).getName();
                part.write(SAVE_DIR + File.separator + fileName);
            }
            request.setAttribute("message", "Upload has been done successfully!");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/uploadMessage.jsp").forward(
                    request, response);
        } else {
            request.setAttribute("message1", " ");
            request.setAttribute("message2", " ");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(
                    request, response);
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}

