package me.border.sentinel.commands;

import me.border.sentinel.Sentinel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import static me.border.sentinel.utils.Utils.*;
public class StaffChat implements CommandExecutor {
    private Sentinel plugin;

    public StaffChat(Sentinel plugin) {
        this.plugin = plugin;
        plugin.getCommand("staffchat").setExecutor(this);
    }

    public static ArrayList<Player> staffChat = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!playerCheck(sender)) return true;
        Player p = (Player) sender;
        if (!permCheck(sender, "sentinel.staffchat")) return true;
        if (args.length == 0) {
            boolean isStaffChat = staffChat.contains(p);
            if (isStaffChat) {
                staffChat.remove(p);
                sendMsg(p, "StaffChat.toggle", "%mode%", colorize("&cOFF"));
            } else {
                staffChat.add(p);
                sendMsg(p, "StaffChat.toggle", "%mode%", colorize("&aON"));
            }
            return true;
        }
        sendStaffChatMessage(p, args);
        return false;
    }

    public void sendStaffChatMessage(Player p, String[] args){
        StringBuilder message = new StringBuilder();
        for (String s : args) {
            message.append(s).append(" ");
        }
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("sentinel.staffchat")) {
                sendMsg(all, "StaffChat.format", "%name%", p.getName(), "%message%", message.toString());
            }
        }
    }
}
