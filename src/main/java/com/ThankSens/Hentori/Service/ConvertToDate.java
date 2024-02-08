package com.ThankSens.Hentori.Service;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConvertToDate {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Date convertDate(String dateString) throws ParseException {
        Date date = dateFormat.parse(dateString);
        return date;
    }

    public Date convertDateTime(String dateString) throws ParseException{
        Date date = dateTimeFormat.parse(dateString);
        return date;
    }
}
