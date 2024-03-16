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

        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command.");
            return true;
        }


        Player p = (Player)sender;

        if(!p.hasPermission("holidayitem.setitem")){
            return true;
        }

        if(args.length > 0){
            plugin.getConfig().set("EventMessage", arrayToString(args));
        } else {
            plugin.getConfig().set("EventMessage", "");
        }

        //Serializes and Saves item currently held in hand to the config.yml file then saves the config
        ItemSerializer.serializeItemStack(p.getInventory().getItemInMainHand(), plugin.getConfig().createSection("NBTData"));
        ItemSerializer.saveToFile(plugin.getConfig(), plugin.getDataFolder() + "/config.yml");
        p.sendMessage("Item set successfully");
        plugin.saveConfig();
        return true;
    }

    private String arrayToString(String[] stringArray){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < stringArray.length; i++){
            sb.append(stringArray[i]);
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}