package com.example.kredyt_kalkulator;

import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {

@GetMapping("/Siema")
public String get(){
    return "Siema";
}
@GetMapping("/")
public String index(){
    return "index";
    }

}