package de.thewulff.bungeeslowchat.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class Methods {

    public static boolean slowChat = false;
    public static HashMap<ProxiedPlayer, Long> cooldownMap = new HashMap<>();

    public static void checkPlayers(){
        if(ProxyServer.getInstance().getPlayers().size() >= Integer.valueOf(ConfigManager.getValue("minPlayers"))){
            slowChat = true;
        }
    }
}
