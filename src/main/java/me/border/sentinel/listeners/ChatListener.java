package me.border.sentinel.listeners;

import me.border.sentinel.Sentinel;
import me.border.sentinel.commands.StaffChat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import static me.border.sentinel.utils.Utils.*;

public class ChatListener implements Listener {
    private Sentinel plugin;
    public ChatListener(Sentinel plugin){
        this.plugin =plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        boolean isStaffChat = getStaffChat().contains(p);
        if (isStaffChat){
            e.setCancelled(true);
            sendStaffChatMessage(p, e.getMessage());
        }
    }

    public void sendStaffChatMessage(Player p, String message){
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission("sentinel.staffchat")) {
                sendMsg(all, "StaffChat.format", "%name%", p.getName(), "%message%", message);
            }
        }
    }

    private ArrayList<Player> getStaffChat(){
        return StaffChat.staffChat;
    }
}
