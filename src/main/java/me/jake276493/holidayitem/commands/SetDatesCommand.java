package me.jake276493.holidayitem.commands;

import me.jake276493.holidayitem.util.DateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetDatesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if(!p.hasPermission("holidayitem.setdates")) return true;

            if (args.length != 2) {
                sender.sendMessage("Usage: /setdates <yyyy-MM-dd> <yyyy-MM-dd>");
                return true;
            }

            String startDate = args[0];
            String endDate = args[1];
            DateManager.setDate(startDate, endDate, p);
        }
        return true;
    }
}