package me.jake276493.holidayitem.listeners;


import me.jake276493.holidayitem.HolidayItem;
import me.jake276493.holidayitem.util.DateManager;
import me.jake276493.holidayitem.util.EventManager;
import me.jake276493.holidayitem.util.ItemSerializer;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        HolidayItem plugin = HolidayItem.getPlugin();
        Date[] eventItemDates = DateManager.getDates();
        Date currentDate = new Date();

        //Resets the event if the current date is after the event ends
        if(currentDate.after(eventItemDates[1]) && eventItemDates[1].after(new Date(2024, Calendar.JANUARY, 1))) {
            EventManager.resetEvent();
        }

        //Makes sure there is an item to be given in the config
        if(plugin.getConfig().getConfigurationSection("NBTData") != null){

            Player p = e.getPlayer();
            Inventory inventory = p.getInventory();
            World world = p.getWorld();
            List<String> UUIDReceived = plugin.getConfig().getStringList("UUIDReceived");
            boolean allowMultiple = plugin.getConfig().getBoolean("AllowMultipleItems");


            if(currentDate.before(eventItemDates[1]) && (currentDate.after(eventItemDates[0]))){

                if(allowMultiple || !UUIDReceived.contains(p.getUniqueId().toString())){
                    //Attempts to deserialize item from Config.yml
                    ItemStack item = ItemSerializer.deserializeItemStack(Objects.requireNonNull(plugin.getConfig().getConfigurationSection("NBTData")));

                    //If Inventory Full logic, Inventory.firstEmpty() returns -1 if there are no empty slots in an inventory
                    //Else adds item to inventory directly
                    if(inventory.firstEmpty() == -1){
                        world.dropItem(p.getLocation(), item);
                        plugin.getLogger().info("Item DROPPED " + item);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lWARNING: Your item has been dropped at your location since your inventory is full"));
                    } else {
                        inventory.addItem(item);
                        plugin.getLogger().info("Item GIVEN " + item);
                    }

                    //Send Message Logic
                    if(plugin.getConfig().getString("EventMessage") != null){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("EventMessage"))));
                    }
                }

                //Adds player to UUIDReceived if only 1 item per person and saves config
                //Else does not add UUID to config
                if(!allowMultiple){
                    UUIDReceived.add(p.getUniqueId().toString());
                    plugin.getConfig().set("UUIDReceived", UUIDReceived);
                    plugin.saveConfig();
                }
            }
        }
    }
}