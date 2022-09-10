package io.paper.globalchat.listeners;

import io.paper.globalchat.Main;
import io.paper.globalchat.managers.ConfigManager;
import io.paper.globalchat.utils.ColorUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {
    @EventHandler
    public void onPlayerChat(final ChatEvent e) {
        if (e.getSender() instanceof ProxiedPlayer) {
            final ProxiedPlayer p = (ProxiedPlayer) e.getSender();
            if (e.getMessage().startsWith(ConfigManager.getMainConfig().getString("GlobalChatPrefix"))) {
                if (!e.getMessage().contentEquals(ConfigManager.getMainConfig().getString("GlobalChatPrefix"))) {
                    if (p.hasPermission("globalchatbungee.globalchat.use")) {
                        e.setCancelled(true);
                        final TextComponent broadcast = new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("GlobalFormat").replace("%prefix%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("Prefix"))).replace("%serverNameFormat%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("ServerNameFormat").replace("%serverName%", p.getServer().getInfo().getName()))).replace("%player_name%", ColorUtils.color(p.getDisplayName())).replace("%message%", ColorUtils.color(e.getMessage().replace("!", "")))));
                        broadcast.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ConfigManager.getMainConfig().getString("CommandClickEvent").replace("%player%", p.getDisplayName()).replace("%target%", p.getServer().getInfo().getName())));
                        broadcast.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Content[]{(Content) new Text(ColorUtils.color(ConfigManager.getMainConfig().getString("TextHoverEvent")))}));
                        for (final ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                            player.sendMessage((BaseComponent) broadcast);
                        }
                        Main.INSTANCE.getProxy().getLogger().info(String.valueOf(broadcast));
                    } else {
                        e.setCancelled(true);
                        p.sendMessage((BaseComponent) new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("NoPermsMSG").replace("%prefix%", ConfigManager.getMessagesConfig().getString("Prefix")))));
                    }
                } else {
                    e.setCancelled(true);
                    p.sendMessage((BaseComponent) new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("OnlyPrefixMSG").replace("%prefix%", ConfigManager.getMessagesConfig().getString("Prefix")))));
                }
            }

        }
    }
}
