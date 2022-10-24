package com.example.kredyt_kalkulator;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/Siema")
    public String get(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("ID")!=null) {
            String txIdSession = httpSession.getAttribute("ID").toString();
            mod.addAttribute("Loginn", txIdSession);
            if (txIdSession.equals("admin")) {
                return "Siema";
            }
        }
        return"logowanie";

    }
    @GetMapping("/")
    public String index(HttpSession httpSession, Model mod) {
        if(httpSession.getAttribute("ID")!=null) {
            String txIdSession = httpSession.getAttribute("ID").toString();
            mod.addAttribute("Loginn", txIdSession);
            if (txIdSession.equals("admin")) {
                return "index";
            }
        }
            return"logowanie";
    }
    @PostMapping("/Signin")
    public String Signin(HttpSession httpSession, @RequestParam(value = "Login") String Login, @RequestParam(value = "Password") String Password, Model mod) {
        mod.addAttribute("Loginn", Login);
        mod.addAttribute("Passwordd", Password);
        mod.addAttribute("Message", "Błędny login lub hasło!!!");

        if (Login.equals("admin") && Password.equals("admin")) {
            httpSession.setAttribute("ID", Login);
            return "index";
        } else {
            return "logowanie";
        }
    }
    @GetMapping("/Logout")
    public String Logout(HttpSession httpSession) {
        httpSession.removeAttribute("ID");
        return "logowanie";
    }

}