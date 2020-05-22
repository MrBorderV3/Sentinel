package me.border.sentinel;

import me.border.sentinel.commands.Fly;
import me.border.sentinel.commands.Help;
import me.border.sentinel.commands.Ping;
import me.border.sentinel.commands.StaffChat;
import me.border.sentinel.listeners.ChatListener;
import me.border.sentinel.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Sentinel extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        new Utils(this);
        new Help(this);
        new Fly(this);
        new Ping(this);
        new StaffChat(this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }
}
