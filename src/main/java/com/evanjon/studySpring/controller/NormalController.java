package com.evanjon.studySpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/normal")
public class NormalController {

    @GetMapping("/normal")
    public String normal() {
        return "normal";
    }
}
