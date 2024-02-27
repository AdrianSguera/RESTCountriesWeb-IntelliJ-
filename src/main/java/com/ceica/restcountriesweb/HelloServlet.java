package com.ceica.restcountriesweb;

import java.io.*;
import java.util.Arrays;

import com.ceica.restcountriesweb.services.RestCountryService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        RestCountryService restCountryService = new RestCountryService();
        String[] regions = restCountryService.getRegions();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + Arrays.toString(regions) + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}