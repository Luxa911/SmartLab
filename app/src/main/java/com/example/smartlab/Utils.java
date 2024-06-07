package com.example.smartlab;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils
{
    private static final String SERVER_DATE_FORMAT = "yyyy-MM-dd";
    private static final String CLIENT_DATE_FORMAT = "d/M/yyyy";
    public static String formateDateForServer(String dateString){
    SimpleDateFormat sdfClient = new SimpleDateFormat(CLIENT_DATE_FORMAT, Locale.getDefault());
    SimpleDateFormat sdfServer = new SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault());
    try{
        Date date = sdfClient.parse(dateString);
        return sdfServer.format(date);
    }
    catch (ParseException e){
        e.printStackTrace();
        return null;
    }
}
public  static String FormatDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault());
        return sdf.format(date);
}
}
