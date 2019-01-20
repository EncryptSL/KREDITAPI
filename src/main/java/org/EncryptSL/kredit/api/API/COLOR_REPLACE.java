package org.EncryptSL.kredit.api.API;

import org.bukkit.ChatColor;

public class COLOR_REPLACE {

    public String COLOR_REPLACE(String text) {
        text = ChatColor.translateAlternateColorCodes('&', text);
        return text;
    }

}
