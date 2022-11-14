package com.example.kredyt_kalkulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    JdbcTemplate jdbcTemplate;
    @PostMapping("/Signin")
    public String Signin(HttpSession httpSession, @RequestParam(value = "Login", defaultValue="Visitor") String Login, @RequestParam(value = "Password", defaultValue="Visitor") String Password, Model mod) {
        mod.addAttribute("Loginn", Login);
        mod.addAttribute("Passwordd", Password);
        mod.addAttribute("Message", "Błędny login lub hasło!!!");


        String SQL = "select Login,Haslo from uzytkownicy";
        List<Uzytkownik> login = jdbcTemplate.query(SQL, new UzytkownikMapper());

        String LLogin="";
        String HHaslo="";
        for (Uzytkownik record : login) {
            if(Login.equals(record.getName())) {
                LLogin = record.getName();
            }
            if(Password.equals(record.getPassword())) {
                HHaslo = record.getPassword();
            }
        }

        if (Login.equals(LLogin) && Password.equals(HHaslo)) {
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