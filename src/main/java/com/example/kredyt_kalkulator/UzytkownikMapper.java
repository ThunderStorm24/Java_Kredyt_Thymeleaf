package com.example.kredyt_kalkulator;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UzytkownikMapper implements RowMapper<Uzytkownik> {
    public Uzytkownik mapRow(ResultSet rs, int rowNum) throws SQLException {
        Uzytkownik uzytkownik = new Uzytkownik();
        uzytkownik.setName(rs.getString("Login" ));
        uzytkownik.setPassword(rs.getString("Haslo" ));
        return uzytkownik;
    }
}
