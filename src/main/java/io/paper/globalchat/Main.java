package io.paper.globalchat;

import io.paper.globalchat.commands.Gc;
import io.paper.globalchat.listeners.ChatListener;
import io.paper.globalchat.managers.ConfigManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public final class Main extends Plugin {

    public static Main INSTANCE;

    public void onEnable() {
        Main.INSTANCE = this;
        ConfigManager();
        Commands();
    }
    public void ConfigManager(){
        ConfigManager.createMainConfig();
        ConfigManager.registerMainConfig();
        ConfigManager.createMessageConfig();
        ConfigManager.registerMessageConfig();
    }

    public void Commands(){
        ProxyServer.getInstance().getPluginManager().registerCommand((Plugin)this, (Command)new Gc());
        ProxyServer.getInstance().getPluginManager().registerListener((Plugin)this, (Listener)new ChatListener());
    }
}
