package com.ahlquist.commio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SslTemplateApplication {

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("title", "Spring Boot + SSL (HTTPS)");
        model.addAttribute("msg", "Welcome to the SSL Template Project!");
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(SslTemplateApplication.class, args);
    }

}
