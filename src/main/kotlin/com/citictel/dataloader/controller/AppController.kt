package com.citictel.dataloader.controller

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/")
    fun showIndexPage(model: Model, auth: Authentication?): String {
        return if (auth == null)
            "redirect:/login"
        else
            "redirect:/home"
    }

    @GetMapping("/login")
    fun showLoginPage(model: Model): String {
        model["title"] = "Data Loader"
        return "login"
    }
}

@Controller
class HomeController {
    @GetMapping("/home")
    fun showHomePage(model: Model, auth: Authentication): String {
        model["title"] = "Home"
        model["username"] = auth.name
        return "home"
    }
}