package com.sparta.myscheduler.viewController;

import com.sparta.myscheduler.entity.User;
import com.sparta.myscheduler.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final JwtUtil jwtUtil;

    public HomeController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        User user = (User)request.getAttribute("user");
        System.out.println("username" + user.getUsername());
        model.addAttribute("user", user);

        return "index";
    }
}