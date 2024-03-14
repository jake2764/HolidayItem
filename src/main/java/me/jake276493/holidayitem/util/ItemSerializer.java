package me.jake276493.holidayitem.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ItemSerializer {

    public static void serializeItemStack(ItemStack itemStack, ConfigurationSection configSection){
        configSection.set("item", itemStack);
    }

    public static ItemStack deserializeItemStack(ConfigurationSection configSection){
        return configSection.getItemStack("item");
    }


    public static void saveToFile(FileConfiguration config, String path){
        try{
            config.save(path);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
