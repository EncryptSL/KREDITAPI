package org.EncryptSL.kredit.api.API;

import org.EncryptSL.kredit.api.KreditMainClass;
import org.bukkit.entity.Player;

public class FROM_CONFIG {

    private static COLOR_REPLACE color = new COLOR_REPLACE();

    public String prefix = KreditMainClass.getKreditMainClass().getConfig().getString("PluginAPI.MESSAGE.SETTINGS.PLUGIN_PREFIX");

    public String IO_CONFIG_GET_STRING_PLACEHOLDERS(String path, Player target, int Value, int New_Balance) {
        path = KreditMainClass.getKreditMainClass().getConfig().getString(path).replace("[target]", target.getName())
                .replace("[kredit]", String.valueOf(Value))
                .replace("[new_balance]", String.valueOf(New_Balance));
        String chat = color.COLOR_REPLACE(prefix + path);
        return chat;
    }

    public String IO_CONFIG_GET_NORMAL_STRING(String path) {
        path = KreditMainClass.getKreditMainClass().getConfig().getString(path);
        String chat = color.COLOR_REPLACE(path);
        return chat;
    }

    public String IO_CONFIG_GET_STRINGLIST(String path) {
        path = String.valueOf(KreditMainClass.getKreditMainClass().getConfig().getStringList(String.valueOf(path)));
        String chat = color.COLOR_REPLACE(path);
        return chat;
    }

    public void A_CONFIG_COMMANDS(String config_message, Player player) {
        switch (config_message) {
            case "COMMANDS.NO_PERMISSIONS":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.NO_PERMISSIONS"));
                break;
            case "COMMANDS.MENU_COMMAND":
                player.sendMessage(IO_CONFIG_GET_STRINGLIST("PluginAPI.MESSAGE.COMMANDS.MENU_COMMAND") + "\n");
                break;
            case "COMMANDS.WRONG_USAGE.WRONG_FIRST.ADD_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_FIRST.ADD_COMMAND"));
                break;
            case "COMMANDS.WRONG_USAGE.WRONG_FIRST.SET_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_FIRST.SET_COMMAND"));
                break;
            case "COMMANDS.WRONG_USAGE.WRONG_FIRST.REMOVE_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_FIRST.REMOVE_COMMAND"));
                break;
            case "COMMANDS.WRONG_USAGE.WRONG_FIRST.CREATE_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_FIRST.CREATE_COMMAND"));
                break;
            case "COMMANDS.WRONG_USAGE.WRONG_SECOND.ADD_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_SECOND.ADD_COMMAND"));
                break;
            case "COMMANDS.WRONG_USAGE.WRONG_SECOND.SET_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_SECOND.SET_COMMAND"));
                break;
            case "COMMANDS.WRONG_USAGE.WROND_SECOND.REMOVE_COMMAND":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.COMMANDS.WRONG_USAGE.WRONG_SECOND.REMOVE_COMMAND"));
                break;
        }
    }

    public void B_CONFIG_MESSAGE(String config_message, Player player, Player target, int Value, int New_Balance) {
        switch (config_message) {
            case "PLAYER.BALANCE":
                target.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.PLAYER.BALANCE", target, Value, New_Balance));
                break;
            case "PLAYER.NOTIFY_ADDED_PLAYER_KREDIT":
                target.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.PLAYER.NOTIFY_ADDED_PLAYER_KREDIT", target, Value, New_Balance));
                break;
            case "PLAYER.NOTIFY_SET_PLAYER_KREDIT":
                target.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.PLAYER.NOTIFY_SET_PLAYER_KREDIT", target, Value, New_Balance));
                break;
            case "PLAYER.NOTIFY_REMOVE_PLAYER_KREDIT":
                target.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.PLAYER.NOTIFY_REMOVE_PLAYER_KREDIT", target, Value, New_Balance));
                break;
            case "PLAYER.NOTIFY_CREATE_PLAYER_ACCOUNT":
                target.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.PLAYER.NOTIFY_CREATE_PLAYER_KREDIT", target, Value, New_Balance));
                break;
            case "ADMIN.YOU_ADD_KREDIT":
                player.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.ADMIN.YOU_ADD_KREDIT", target, Value, New_Balance));
                break;
            case "ADMIN.YOU_SET_KREDIT":
                player.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.ADMIN.YOU_ADD_KREDIT", target, Value, New_Balance));
                break;
            case "ADMIN.YOU_REMOVE_KREDIT":
                player.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.ADMIN.YOU_REMOVE_KREDIT", target, Value, New_Balance));
                break;
            case "ADMIN.YOU_CREATE_KREDIT_ACCOUNT":
                player.sendMessage(IO_CONFIG_GET_STRING_PLACEHOLDERS("PluginAPI.MESSAGE.ADMIN.YOU_CREATE_KREDIT_ACCOUNT", target, Value, New_Balance));
                break;
        }
    }

    public void C_CONFIG_STRING_MESSAGE_NORMAL(String config_messaage, Player player) {
        switch (config_messaage) {
            case "PLUGIN_PREFIX":
                IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.SETTINGS.PLUGIN_PREFIX");
                break;
            case "PLAYER.ACCOUNT_NO_EXIST":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.PLAYER.ACCOUNT_NO_EXIST"));
                break;
            case "PLAYER.PLAYER_ACCOUNT_EXIST":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.PLAYER.PLAYER_ACCOUNT_EXIST"));
            case "ADMIN.PLAYER_OFFLINE":
                player.sendMessage(IO_CONFIG_GET_NORMAL_STRING("PluginAPI.MESSAGE.ADMIN.PLAYER_OFFLINE"));
                break;
        }
    }

}
