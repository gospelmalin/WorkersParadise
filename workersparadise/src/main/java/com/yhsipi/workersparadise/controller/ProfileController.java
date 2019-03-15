package com.yhsipi.workersparadise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    /*
       All authenticated areas should be placed here, meaning after a user has been signed in
     */

    //Should return your authenticated user profile
    @GetMapping("/profile")
    public String Profile() {
        return "profile/profile";
    }
}
