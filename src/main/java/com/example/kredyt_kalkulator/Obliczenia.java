package com.example.kredyt_kalkulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.ApplicationContext;


@org.springframework.stereotype.Controller
public class Obliczenia {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/Oblicz")
    public String Oblicz(@RequestParam(value="Kwota",defaultValue="1000") double Kwota, @RequestParam(value="Procent",defaultValue="0") double Procent, @RequestParam(value="Lata",defaultValue="1") int Lata, Model mod) {

        if(Kwota<1000) {return "Kwota nie moze byc na minusie!!!";}
        if(Procent<0 || Procent>100) {return "Procent nie moze byc mniejszy od 0 i wiekszy od 100!!!";}
        if(Lata<0 || Lata>25) {return "Lata nie moga byc mniejsze od 0 i wieksze od 25!!!";}

        mod.addAttribute("liczba1",Kwota);
        mod.addAttribute("liczba2",Procent);
        mod.addAttribute("liczba3",Lata);

            int miesiace = Lata * 12;
            double wynik = 0.0;
            double Zero = 0.0;
            double suma = 0.0;

            if (Procent == 0.0) {
                wynik = (Kwota / miesiace);
                suma = Kwota;
            } else {
                Zero = (Procent / 100) * Kwota;
                suma = Kwota + Zero;
                wynik = (suma) / miesiace;
            }

            suma = Math.round(suma*100);
            wynik = Math.round(wynik*100);

            mod.addAttribute("suma","Do spłacenia: " + suma/100 + " zł");
            mod.addAttribute("wynik","Rata co miesiąc: " + wynik/100 + " zł");
            mod.addAttribute("miesiace"," przez " + miesiace + " miesiecy");

        String query="INSERT INTO wyniki(Wynik,Raty,Czas) values(?,?,?)";
        jdbcTemplate.update(query,suma/100,wynik/100,miesiace);

        return "index";
    }

}
