package de.thewulff.bungeeslowchat.listeners;

import de.thewulff.bungeeslowchat.BungeeSlowChat;
import de.thewulff.bungeeslowchat.utils.ConfigManager;
import de.thewulff.bungeeslowchat.utils.Methods;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ClientConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(ClientConnectEvent event){
        if(ProxyServer.getInstance().getPlayers().size()+1 >= Integer.valueOf(ConfigManager.getValue("minPlayers"))){
            if(!Methods.slowChat){
                ProxyServer.getInstance().broadcast(BungeeSlowChat.getInstance().getConfigMessages("slowChatEnabled").replace("%cooldown%", ConfigManager.getValue("cooldown")));
                Methods.slowChat = true;
            }
        }
    }
}