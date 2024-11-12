package com.buletjournal.project.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;
public class CustomDateConverter implements Converter<String, Date> {
    private String datePattern;

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public Date convert(String s) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.datePattern);
        try {
            Date date = dateFormat.parse(s);
            return date;
        }catch (ParseException e){
          e.getMessage();
        }
        return null;
    }

}
