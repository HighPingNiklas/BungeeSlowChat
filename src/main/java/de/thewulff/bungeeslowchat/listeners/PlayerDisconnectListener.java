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
        if(ProxyServer.getInstance().getPlayers().size()-1 < Integer.valueOf(ConfigManager.getValue("minPlayers"))){
            Methods.slowChat = false;
            ProxyServer.getInstance().broadcast(BungeeSlowChat.getInstance().getConfigMessages("slowChatDisabled").replace("%cooldown%", ConfigManager.getValue("cooldown")));
        }
    }
}
