package de.thewulff.bungeeslowchat.listeners;

import de.thewulff.bungeeslowchat.BungeeSlowChat;
import de.thewulff.bungeeslowchat.utils.ConfigManager;
import de.thewulff.bungeeslowchat.utils.Methods;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if (Methods.slowChat) {
                if(!player.hasPermission("bungeeslowchat.ignore")){
                    if (Methods.cooldownMap.containsKey(player)){
                        if(System.currentTimeMillis() > Methods.cooldownMap.get(player)){
                            Methods.cooldownMap.remove(player);
                            event.setCancelled(false);
                            Methods.cooldownMap.put(player, System.currentTimeMillis() + Long.valueOf(ConfigManager.getValue("cooldown"))*1000);
                        } else {
                            event.setCancelled(true);
                            long seconds = (Methods.cooldownMap.get(player)-System.currentTimeMillis())/1000;
                            if(seconds == 1){
                                player.sendMessage(BungeeSlowChat.getInstance().getConfigMessages("waitOneSecond").replace("%seconds%", seconds + ""));
                            } else {
                                player.sendMessage(BungeeSlowChat.getInstance().getConfigMessages("waitXSeconds").replace("%seconds%", seconds + ""));
                            }
                        }
                    } else {
                        Methods.cooldownMap.put(player, System.currentTimeMillis() + Long.parseLong(ConfigManager.getValue("cooldown"))*1000);
                    }
                }
            }
        }
    }
}