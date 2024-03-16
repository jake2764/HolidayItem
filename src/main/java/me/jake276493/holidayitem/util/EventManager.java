package me.jake276493.holidayitem.util;

import me.jake276493.holidayitem.HolidayItem;

public class EventManager {

    public static void resetEvent(){
        HolidayItem plugin = HolidayItem.getPlugin();

        plugin.getConfig().set("StartDate", "");
        plugin.getConfig().set("EndDate", "");
        plugin.getConfig().set("NBTData", "");
        plugin.getConfig().set("EventMessage", "");
        plugin.getConfig().set("UUIDReceived", "");
        plugin.saveConfig();
    }
}
