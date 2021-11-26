package de.uni.passau.fim.sdbs.josch.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * This is the main Application if Josch is launched by web. It runs the
 * Application as a Spring Application on the Tomcat Server.
 *
 * @version 1.0
 * @since 2021-02-02
 * @author Kai Dauberschmidt
 */
// Disables security over all.
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {

    /**
     * Execute the {@link Application} as a {@link SpringApplication}.
     *
     * @param args the arguments, if existent.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
