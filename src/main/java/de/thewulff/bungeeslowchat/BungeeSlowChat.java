package de.thewulff.bungeeslowchat;

import de.thewulff.bungeeslowchat.listeners.ChatListener;
import de.thewulff.bungeeslowchat.listeners.LoginListener;
import de.thewulff.bungeeslowchat.listeners.PlayerDisconnectListener;
import de.thewulff.bungeeslowchat.utils.ConfigManager;
import de.thewulff.bungeeslowchat.utils.Methods;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;

public class BungeeSlowChat extends Plugin {

    private static BungeeSlowChat instane;

    public static BungeeSlowChat getInstance(){
        return instane;
    }

    @Override
    public void onEnable() {
        instane = this;
        registerListener();
        ConfigManager.setDefaultDatas();
        Methods.checkPlayers();
        getLogger().info("enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("disabled.");
    }

    public void registerListener(){
        PluginManager pm = ProxyServer.getInstance().getPluginManager();
        pm.registerListener(this, new LoginListener());
        pm.registerListener(this, new ChatListener());
        pm.registerListener(this, new PlayerDisconnectListener());
    }

    public String getConfigMessages(String message){

        File fileMessaages = new File(getDataFolder().getPath(), "messages.yml");
        Configuration cfg = null;
        try {
            cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileMessaages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(cfg != null)
        return ChatColor.translateAlternateColorCodes('&', cfg.getString(message).replace("%prefix%", cfg.getString("prefix")));
        getLogger().warning("Ein Fehler in der messages.yml. Mindestens eine Nachricht wurde falsch gesetzt. " +
                "Gegebenenfalls löschen sie die messages.yml und restarten/reloaden sie den Server um die Datei neu zu generieren.");
        return "&4Ein Fehler in der messages.yml. Bitte melden sie sich bei einem Teammitglied und schildern sie, " +
                "was geschehen ist, bevor diese Nachricht ausgegeben wurde.";
    }
}
