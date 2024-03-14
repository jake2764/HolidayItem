package me.jake276493.holidayitem.commands;

import me.jake276493.holidayitem.HolidayItem;
import me.jake276493.holidayitem.util.ItemSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;



public class SetItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        HolidayItem plugin = HolidayItem.getPlugin();

        if(!(sender instanceof  Player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player p = (Player)sender;

        if(!p.hasPermission("holidayitem.setitem")){
            return true;
        }

        ItemSerializer.serializeItemStack(p.getInventory().getItemInMainHand(), plugin.getConfig().createSection("NBTData"));
        ItemSerializer.saveToFile(plugin.getConfig(), plugin.getDataFolder() + "/config.yml");
        //plugin.getConfig().set("ItemNBT", p.getInventory().getItemInMainHand());
        //plugin.saveConfig();
        p.sendMessage("Item set successfully");
        return true;
    }
}