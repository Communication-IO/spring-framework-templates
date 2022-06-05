package com.ahlquist.commio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class WǒDeHáiziController {

    private static final Logger logger = LoggerFactory.getLogger(WǒDeHáiziController.class);

    @GetMapping("/mykids")
    public String goodMorning(Model model) {

        List<String> kids = Arrays.asList("Robert", "Rein", "Trudi");

        logger.debug("The ones I love", kids);

        model.addAttribute("kids", kids);

        return "family"; 
    }

}