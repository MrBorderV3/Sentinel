package me.border.sentinel.commands;

import me.border.sentinel.Sentinel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.border.sentinel.utils.Utils.*;
public class Fly implements CommandExecutor {
    private Sentinel plugin;
    public Fly(Sentinel plugin){
        this.plugin = plugin;
        plugin.getCommand("fly").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean flyMode;
        if (args.length == 0){
            if (!playerCheck(sender)) return true;
            Player p = (Player) sender;
            if (!permCheck(p, "sentinel.fly")) return true;
            flyMode = p.getAllowFlight();
            if (flyMode){
                p.setAllowFlight(false);
                p.setFlying(false);
                sendMsg(p, "Flight.disabled");
                return true;
            }
            p.setAllowFlight(true);
            p.setFlying(true);
            sendMsg(p, "Flight.enabled");
        } else {
            if (!argsCheck(sender, 1, args)) return true;
            if (!permCheck(sender, "sentinel.fly.others")) return true;
            Player target = Bukkit.getPlayerExact(args[0]);
            if (!offlineCheck(target, sender, args[0])) return true;
            flyMode = target.getAllowFlight();
            if (flyMode){
                target.setAllowFlight(false);
                target.setFlying(false);
                sendMsg(target, "Flight.disabled");
                sendMsg(sender, "Flight.disabledOthers", "%target%", args[0]);
                return true;
            }
            target.setAllowFlight(true);
            target.setFlying(true);
            sendMsg(target, "Flight.enabled");
            sendMsg(sender, "Flight.enabledOthers", "%target%", args[0]);
        }
        return false;
    }
}
