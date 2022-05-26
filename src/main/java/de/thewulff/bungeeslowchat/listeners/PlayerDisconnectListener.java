package de.thewulff.bungeeslowchat.listeners;

import de.thewulff.bungeeslowchat.BungeeSlowChat;
import de.thewulff.bungeeslowchat.utils.ConfigManager;
import de.thewulff.bungeeslowchat.utils.Methods;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event){
        System.out.println(ProxyServer.getInstance().getPlayers().size());
        if(ProxyServer.getInstance().getPlayers().size() < Integer.valueOf(ConfigManager.getValue("minPlayers"))){
            if(Methods.slowChat){
                Methods.slowChat = false;
                if(!BungeeSlowChat.getInstance().getConfigMessages("slowChatDisabled").equalsIgnoreCase("")
                        && !BungeeSlowChat.getInstance().getConfigMessages("slowChatDisabled").equalsIgnoreCase(" ")
                        && BungeeSlowChat.getInstance().getConfigMessages("slowChatDisabled") != null)
                    ProxyServer.getInstance().broadcast(BungeeSlowChat.getInstance().getConfigMessages("slowChatDisabled").replace("%cooldown%", ConfigManager.getValue("cooldown")));
            }
        }
    }
}