package de.uni.passau.fim.sdbs.josch.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.mail.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * This class controls the error page.
 *
 * @author Kai Dauberschmidt
 */
@Controller
public class CustomErrorController implements ErrorController {

    /** Injected admin E-Mail */
    @Value("${josch.mail.admin}")
    private String admin;

    /**
     * The return value from this method is not used; the property `server.error.path`
     * must be set to override the default error page path.
     *
     * @return the error path
     * @deprecated since 2.3.0 in favor of setting the property `server.error.path`
     */
    @Override
    public String getErrorPath() {
        return null;
    }

    /**
     * Handles the error dependent on the status code.
     *
     * @param request The request that leads to the error.
     * @return The error page.
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // add the mail props.
        model.addAttribute("adminMail", admin);
        String link = "mailto:" + admin;
        model.addAttribute("adminLink", link);

        // Get the code and check the status.
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode != null) {
            HttpStatus status = HttpStatus.resolve(Integer.parseInt(statusCode.toString()));

            // Add the code and the reason to the model.
            if (status != null) {
                model.addAttribute("code", status.value());
                model.addAttribute("message", status.getReasonPhrase());

                String subject = "Error " + status.value() + ": " + status.getReasonPhrase();
                model.addAttribute("subject", subject);
            }
        }

        return "error";
    }
}
