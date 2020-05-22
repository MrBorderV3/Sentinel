package me.border.sentinel.commands;

import me.border.sentinel.Sentinel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

import static me.border.sentinel.utils.Utils.*;
public class Ping implements CommandExecutor {
    private Sentinel plugin;
    public Ping(Sentinel plugin){
        this.plugin = plugin;
        plugin.getCommand("ping").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            if (!playerCheck(sender)) return true;
            Player p = (Player) sender;
            if (!permCheck(p, "sentinel.ping")) return true;
            try {
                Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
                int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                sendMsg(p, "Ping.self", "%ping%", String.valueOf(ping));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        } else {
            if (!argsCheck(sender, 1, args)) return true;
            if (!permCheck(sender, "sentinel.ping.others")) return true;
            Player target = Bukkit.getPlayerExact(args[0]);
            if (!offlineCheck(target, sender, args[0])) return true;
            try {
                Object entityPlayer = target.getClass().getMethod("getHandle").invoke(target);
                int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                sendMsg(sender, "Ping.others", "%ping%", String.valueOf(ping), "%target%", args[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
