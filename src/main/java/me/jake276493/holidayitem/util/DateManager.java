package me.jake276493.holidayitem.util;

import me.jake276493.holidayitem.HolidayItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManager {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date[] getDates(){

        HolidayItem plugin = HolidayItem.getPlugin();
        Date[] dates = new Date[2];

        //Reads values from Config and gives error if values in config are invalid (should not be possible)
        try{
            dates[0] = dateFormat.parse(plugin.getConfig().getString("StartDate"));
            dates[1] = dateFormat.parse(plugin.getConfig().getString("EndDate"));
        } catch (ParseException error) {
            error.printStackTrace();
            return null;
        }
        return dates;
    }

    public static void setDate(String startDate, String endDate, Player sender){

        HolidayItem plugin = HolidayItem.getPlugin();

        //Makes sure dates are valid format
        try {
            Date eventItemStart = dateFormat.parse(startDate);
            Date eventItemEnd = dateFormat.parse(endDate);
            //Makes sure start date is before end date
            if(eventItemEnd.before(eventItemStart)) return;
        } catch (ParseException e) {
            sender.sendMessage("Unknown Error");
            Bukkit.getLogger().info("Error parsing user sent date");
            throw new RuntimeException(e);
        }

        //Saves new validated dates into config file
        plugin.getConfig().set("StartDate", startDate);
        plugin.getConfig().set("EndDate", endDate);
        plugin.saveConfig();
        sender.sendMessage("Dates set successfully");
    }
}