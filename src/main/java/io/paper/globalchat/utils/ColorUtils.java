package io.paper.globalchat.utils;

import net.md_5.bungee.api.ChatColor;

public class ColorUtils {
    public static String color(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String colorLogs(final String message) {
        return ChatColor.translateAlternateColorCodes('§', message);
    }
}
