package io.paper.globalchat.managers;

import io.paper.globalchat.Main;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

public class ConfigManager {
    static net.md_5.bungee.config.Configuration config;
    static Configuration messagesConfig;

    public static void createMainConfig() {
        if (!Main.INSTANCE.getDataFolder().exists()) {
            Main.INSTANCE.getDataFolder().mkdir();
        }
        final File file = new File(Main.INSTANCE.getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                final InputStream in = Main.INSTANCE.getResourceAsStream("config.yml");
                Files.copy(in, file.toPath(), new CopyOption[0]);
            }
            catch (IOException var3) {
                var3.printStackTrace();
            }
        }
    }

    public static void registerMainConfig() {
        try {
            ConfigManager.config = ConfigurationProvider.getProvider((Class) YamlConfiguration.class).load(new File(Main.INSTANCE.getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static net.md_5.bungee.config.Configuration getMainConfig() {
        return ConfigManager.config;
    }

    public static void createMessageConfig() {
        if (!Main.INSTANCE.getDataFolder().exists()) {
            Main.INSTANCE.getDataFolder().mkdir();
        }
        final File file = new File(Main.INSTANCE.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            try {
                final InputStream in = Main.INSTANCE.getResourceAsStream("messages.yml");
                Files.copy(in, file.toPath(), new CopyOption[0]);
            }
            catch (IOException var3) {
                var3.printStackTrace();
            }
        }
    }

    public static void registerMessageConfig() {
        try {
            ConfigManager.messagesConfig = ConfigurationProvider.getProvider((Class)YamlConfiguration.class).load(new File(Main.INSTANCE.getDataFolder(), "messages.yml"));
        }
        catch (IOException var1) {
            var1.printStackTrace();
        }
    }

    public static net.md_5.bungee.config.Configuration getMessagesConfig() {
        return ConfigManager.messagesConfig;
    }
}
