package de.uni.passau.fim.sdbs.josch.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This class is used to handle navigation to pages that do not rely on underlying services, for
 * example the legal notice. That way these pages do not need a own controller that only contains
 * the navigation mapping.
 *
 * @author Kai Dauberschmidt
 */
@Controller
public class NavigationController {

    /**
     * Handles navigation (mapping) for the legal notice page.
     */
    @GetMapping("/legalnotice")
    public String legalNotice() {
        return "legalnotice";
    }

    /**
     * Handles navigation (mapping) for the privacy policy page.
     */
    @GetMapping("/privacypolicy")
    public String privacyPolicy() {
        return "privacypolicy";
    }

    /**
     * Handles navigation (mapping) for the template.
     */
    @GetMapping("/template")
    public String template() {
        return "template";
    }

    /**
     * Handles navigation (mapping) for the about page.
     */
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}