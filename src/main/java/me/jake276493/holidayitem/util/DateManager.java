package me.jake276493.holidayitem.util;

import me.jake276493.holidayitem.HolidayItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date[] getDates(){

        HolidayItem plugin = HolidayItem.getPlugin();
        Date[] dates = new Date[2];


        try{
            dates[0] = dateFormat.parse(plugin.getConfig().getString("StartDate"));
            dates[1] = dateFormat.parse(plugin.getConfig().getString("EndDate"));
        } catch (ParseException error) {
            error.printStackTrace();
            return null;
        }
        return dates;
    }

    public static void setDate(String startDate, String endDate){

        HolidayItem plugin = HolidayItem.getPlugin();


        //Makes sure dates are valid
        try {
            Date eventItemStart = dateFormat.parse(startDate);
            Date eventItemEnd = dateFormat.parse(endDate);
            //Makes sure start date is before end date
            if(eventItemStart.before(eventItemEnd)) return;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        plugin.getConfig().set("StartDate", startDate);
        plugin.getConfig().set("EndDate", endDate);
        plugin.saveConfig();
    }
}