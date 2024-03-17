package me.jake276493.holidayitem;
import me.jake276493.holidayitem.commands.ResetEventCommand;
import me.jake276493.holidayitem.commands.SetDatesCommand;
import me.jake276493.holidayitem.commands.SetItemCommand;
import me.jake276493.holidayitem.listeners.OnPlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;


public final class HolidayItem extends JavaPlugin {

    private static HolidayItem plugin;
    @Override
    public void onEnable() {

        saveDefaultConfig();
        plugin = this;

        getCommand("setdates").setExecutor(new SetDatesCommand());
        getCommand("setitem").setExecutor(new SetItemCommand());
        getCommand("resetevent").setExecutor(new ResetEventCommand());
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);

    }
    @Override
    public void onDisable() {

    }

    public static HolidayItem getPlugin() {
        return plugin;
    }
}
