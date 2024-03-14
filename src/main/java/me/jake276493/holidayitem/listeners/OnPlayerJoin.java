package me.jake276493.holidayitem.listeners;


import me.jake276493.holidayitem.HolidayItem;
import me.jake276493.holidayitem.util.DateManager;
import me.jake276493.holidayitem.util.ItemSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Date;
import java.util.List;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){


        HolidayItem plugin = HolidayItem.getPlugin();

        Player p = e.getPlayer();

        ItemStack item = ItemSerializer.deserializeItemStack(plugin.getConfig().getConfigurationSection("NBTData"));
        p.getInventory().addItem(item);


        Date currentDate = new Date();
        List<String> UUIDReceived = plugin.getConfig().getStringList("UUIDReceived");

        Date[] eventItemDates = DateManager.getDates();

        if(currentDate.before(eventItemDates[0]) && (currentDate.after(eventItemDates[1])) && !UUIDReceived.contains(p.getUniqueId().toString())){

            //Item Give Logic
            //item = ItemSerializer.deserializeItemStack(plugin.getConfig().getConfigurationSection("NBTData"));
           // p.getInventory().addItem(item);

            UUIDReceived.add(p.getUniqueId().toString());
            plugin.getConfig().set("UUIDReceived", UUIDReceived);
            plugin.saveConfig();


        }
    }
}