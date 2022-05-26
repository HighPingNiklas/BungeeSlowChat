package de.thewulff.bungeeslowchat.listeners;

import de.thewulff.bungeeslowchat.BungeeSlowChat;
import de.thewulff.bungeeslowchat.utils.ConfigManager;
import de.thewulff.bungeeslowchat.utils.Methods;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;


public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PostLoginEvent event){
        if(ProxyServer.getInstance().getPlayers().size() >= Integer.valueOf(ConfigManager.getValue("minPlayers"))){
            if(!Methods.slowChat){
                Methods.slowChat = true;
                if(!BungeeSlowChat.getInstance().getConfigMessages("slowChatEnabled").equalsIgnoreCase("")
                        && !BungeeSlowChat.getInstance().getConfigMessages("slowChatEnabled").equalsIgnoreCase(" ")
                        && BungeeSlowChat.getInstance().getConfigMessages("slowChatEnabled") != null)
                    ProxyServer.getInstance().broadcast(BungeeSlowChat.getInstance().getConfigMessages("slowChatEnabled").replace("%cooldown%", ConfigManager.getValue("cooldown")));
            }
        }
    }
}