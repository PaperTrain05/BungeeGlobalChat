package io.paper.globalchat.commands;

import io.paper.globalchat.Main;
import io.paper.globalchat.managers.ConfigManager;
import io.paper.globalchat.utils.ColorUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;
import java.util.UUID;

public class Gc extends Command {
    public static List<UUID> GlobalToggle;

    public Gc() {
        super("gc");
    }

    public void execute(final CommandSender sender, final String[] args) {
        if (sender instanceof ProxiedPlayer) {
            final ProxiedPlayer player = (ProxiedPlayer)sender;
            if (!player.hasPermission("globalx.globalchat.use")) {
                player.sendMessage((BaseComponent)new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("NoPermsMSG").replace("%prefix%", ConfigManager.getMessagesConfig().getString("Prefix")))));
                return;
            }
            if (this.isNullArgument(args, 0)) {
                final TextComponent insuffArgs = new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("InsuffArgs").replace("%prefix%", ConfigManager.getMessagesConfig().getString("Prefix"))));
                insuffArgs.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Content[] { (Content)new Text(ColorUtils.color(ConfigManager.getMessagesConfig().getString("InsuffArgsSuggestionHover"))) }));
                insuffArgs.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/global <message>"));
                player.sendMessage((BaseComponent)insuffArgs);
                return;
            }
            String message = "";
            for (final String arg : args) {
                if (message.equals("")) {
                    message = arg;
                }
                else {
                    message = message + " " + arg;
                }
            }
            final BaseComponent broadcast = (BaseComponent)new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("GlobalFormat").replace("%prefix%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("Prefix"))).replace("%serverNameFormat%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("ServerNameFormat").replace("%serverName%", player.getServer().getInfo().getName()))).replace("%player_name%", ColorUtils.color(player.getDisplayName())).replace("%message%", message)));
            broadcast.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ConfigManager.getMainConfig().getString("CommandClickEvent").replace("%player%", player.getDisplayName()).replace("%target%", player.getServer().getInfo().getName())));
            broadcast.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Content[] { (Content)new Text(ColorUtils.color(ConfigManager.getMainConfig().getString("TextHoverEvent"))) }));
            for (final ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                p.sendMessage(broadcast);
            }
            Main.INSTANCE.getProxy().getLogger().info(String.valueOf(broadcast));
        }
        else {
            if (this.isNullArgument(args, 0)) {
                Main.INSTANCE.getProxy().getLogger().info(ColorUtils.color(ConfigManager.getMessagesConfig().getString("InsuffArgs").replace("%prefix%", ConfigManager.getMessagesConfig().getString("Prefix"))));
                return;
            }
            String message2 = "";
            for (final String arg2 : args) {
                if (message2.equals("")) {
                    message2 = arg2;
                }
                else {
                    message2 = message2 + " " + arg2;
                }
            }
            final BaseComponent broadcast2 = (BaseComponent)new TextComponent(ColorUtils.color(ConfigManager.getMessagesConfig().getString("GlobalFormat").replace("%prefix%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("Prefix"))).replace("%serverNameFormat%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("ServerNameFormat").replace("%serverName%", ConfigManager.getMessagesConfig().getString("ConsoleServer")))).replace("%player_name%", ColorUtils.color(ConfigManager.getMessagesConfig().getString("ConsoleNameFormat"))).replace("%message%", ColorUtils.color(message2))));
            for (final ProxiedPlayer p2 : ProxyServer.getInstance().getPlayers()) {
                p2.sendMessage(broadcast2);
            }
            Main.INSTANCE.getProxy().getLogger().info(String.valueOf(broadcast2));
        }
    }

    private boolean isNullArgument(final String[] args, final int index) {
        try {
            final String var10000 = args[index];
            return false;
        }
        catch (IndexOutOfBoundsException var10001) {
            return true;
        }
    }
}
