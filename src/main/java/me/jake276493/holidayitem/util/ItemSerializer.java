package me.jake276493.holidayitem.util;

import me.jake276493.holidayitem.HolidayItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemSerializer {

    private static HolidayItem plugin = HolidayItem.getPlugin();

    public static void serializeItemStack(ItemStack itemStack, ConfigurationSection configSection){
        configSection.set("item", itemStack);
    }

    public static ItemStack deserializeItemStack(ConfigurationSection configSection){
        plugin.getLogger().info("Deserializing Item");
        return configSection.getItemStack("item");
    }


    public static void saveToFile(FileConfiguration config, String path){
        try{
            config.save(path);
        } catch (IOException e){
            e.printStackTrace();
        }
        plugin.getLogger().info("Serializing Item");
    }
}
