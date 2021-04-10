package de.thewulff.bungeeslowchat.utils;

import de.thewulff.bungeeslowchat.BungeeSlowChat;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class ConfigManager {


    private static BungeeSlowChat instance = BungeeSlowChat.getInstance();

    private static File fileMessages = null;
    private static File fileConfig = null;

    public static void setDefaultDatas(){
        try {
            if(!instance.getDataFolder().exists()){
                instance.getDataFolder().mkdir();
            }

            fileMessages = new File(instance.getDataFolder().getPath(), "messages.yml");
            fileConfig = new File(instance.getDataFolder().getPath(), "config.yml");

            if(!fileMessages.exists()){
                fileMessages.createNewFile();
            }

            if(!fileConfig.exists()){
                fileConfig.createNewFile();
            }

            setMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setMessages(){
        try {
            Configuration cfgMessages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileMessages);
            Configuration cfgConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileConfig);

            if(!cfgMessages.contains("prefix"))
                cfgMessages.set("prefix", "&bChat &8»");

            if(!cfgMessages.contains("waitXSeconds"))
                cfgMessages.set("waitXSeconds", "%prefix% &7Du musst noch &e%seconds% Sekunden &7warten um eine weitere Nachricht senden zu können.");

            if(!cfgMessages.contains("waitOneSecond"))
                cfgMessages.set("waitOneSecond", "%prefix% &7Du musst noch &e%seconds% Sekunde &7warten um eine weitere Nachricht senden zu können.");

            if(!cfgMessages.contains("slowChatEnabled"))
                cfgMessages.set("slowChatEnabled", "%prefix% &7Der Langsame Chat wurde aktiviert, du darfst nur noch alle %cooldown% Sekunden eine Nachricht senden.");

            if(!cfgMessages.contains("slowChatDisabled"))
                cfgMessages.set("slowChatDisabled", "%prefix% &7Der Langsame Chat wurde deaktiviert.");

/***************************************************************************/

            if(!cfgConfig.contains("cooldown"))
                cfgConfig.set("cooldown", 5);

            if(!cfgConfig.contains("minPlayers"))
                cfgConfig.set("minPlayers", 60);

            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgMessages, fileMessages);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfgConfig, fileConfig);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String value){
        try{
            Configuration cfgConfig = ConfigurationProvider.getProvider(YamlConfiguration.class).load(fileConfig);

            return String.valueOf(cfgConfig.get(value));

        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
