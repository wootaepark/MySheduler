package com.sparta.myscheduler.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error/unauthorized")
    public String unauthorized() {
        return "error/unauthorized"; // View name
    }
}
