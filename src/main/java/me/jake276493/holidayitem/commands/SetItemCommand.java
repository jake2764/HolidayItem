package me.jake276493.holidayitem.commands;

import me.jake276493.holidayitem.HolidayItem;
import me.jake276493.holidayitem.util.ItemSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


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

        if(args.length > 0){
            plugin.getConfig().set("EventMessage", Arrays.toString(args));
            plugin.saveConfig();
        }

        ItemSerializer.serializeItemStack(p.getInventory().getItemInMainHand(), plugin.getConfig().createSection("NBTData"));
        ItemSerializer.saveToFile(plugin.getConfig(), plugin.getDataFolder() + "/config.yml");
        p.sendMessage("Item set successfully");
        return true;
    }
}