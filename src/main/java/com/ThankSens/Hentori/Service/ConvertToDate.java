package com.ThankSens.Hentori.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConvertToDate {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Date convertDate(String dateString) throws ParseException{
        try {
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
           e.printStackTrace();
           return null;
        }
    }
}
