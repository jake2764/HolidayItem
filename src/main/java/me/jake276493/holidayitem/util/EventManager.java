package me.jake276493.holidayitem.util;

import me.jake276493.holidayitem.HolidayItem;

public class EventManager {

    public static void resetEvent(){
        HolidayItem plugin = HolidayItem.getPlugin();

        plugin.getLogger().info("Resetting Event...");
        plugin.getConfig().set("StartDate", "1970-01-01");
        plugin.getConfig().set("EndDate", "1970-01-02");
        plugin.getConfig().set("NBTData", "");
        plugin.getConfig().set("EventMessage", "");
        plugin.getConfig().set("UUIDReceived", "");
        plugin.saveConfig();
    }
}
