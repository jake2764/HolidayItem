package me.jake276493.holidayitem.commands;

import me.jake276493.holidayitem.HolidayItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length != 2){
            sender.sendMessage("Usage: /setitem <eventName> <NBT>");
            return true;
        }

        return true;
    }
}