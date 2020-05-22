package me.border.sentinel.commands;

import me.border.sentinel.Sentinel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.border.sentinel.utils.Utils.*;
public class Help implements CommandExecutor {
    private Sentinel plugin;
    public Help(Sentinel plugin){
        this.plugin = plugin;
        plugin.getCommand("help").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!permCheck(sender, "sentinel.help")) return true;
        sendMessageList(sender, "Help");
        return false;
    }
}
