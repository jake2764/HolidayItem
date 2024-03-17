package me.jake276493.holidayitem.commands;

import me.jake276493.holidayitem.util.EventManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetEventCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;

            if(!p.hasPermission("holidayitem.resetevent"))return true;

            EventManager.resetEvent();
        }
        return true;
    }
}
