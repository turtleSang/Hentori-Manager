package com.ThankSens.Hentori.Service;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConvertToDate {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Date convertDate(String dateString) throws ParseException {
        Date date = dateFormat.parse(dateString);
        return date;
    }
}
