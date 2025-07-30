/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.formatters;

import com.kmt.pojo.Journey;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author kieum
 */
public class JourneyFormatter implements Formatter<Journey>{

    @Override
    public String print(Journey jour, Locale locale) {
        return String.valueOf(jour.getId());
    }

    @Override
    public Journey parse(String jourID, Locale locale) throws ParseException {
        Journey j = new Journey();
        j.setId(Integer.parseInt(jourID));
        return j;
    }
}
