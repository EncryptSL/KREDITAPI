package org.EncryptSL.kredit.api.COMMANDS;

import org.EncryptSL.kredit.api.API.FROM_CONFIG;
import org.EncryptSL.kredit.api.API.INTEGER_CONTROLER;
import org.EncryptSL.kredit.api.Kredit.SQL_SELECTOR;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MAIN_MENU_COMMAND implements CommandExecutor {

    private static INTEGER_CONTROLER integerControler = new INTEGER_CONTROLER();
    private static SQL_SELECTOR kredits = new SQL_SELECTOR();
    private static FROM_CONFIG config = new FROM_CONFIG();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("kredit")) {
            if(commandSender instanceof Player ) {
                Player player = (Player) commandSender;
                UUID uuid = player.getUniqueId();
                int current = kredits.GET_PLAYER_KREDIT(uuid);
                if (strings.length == 0) {
                    if(kredits.GET_PLAYER_EXIST(uuid)) {
                        config.B_CONFIG_MESSAGE("PLAYER.BALANCE", player, player, current, 0);
                    } else {
                                config.C_CONFIG_STRING_MESSAGE_NORMAL("PLAYER.ACCOUNT_NO_EXIST", player);
                    }
                } else if (strings.length == 1) {
                    if (commandSender.hasPermission("KreditAPI.admin.*")) {
                        if (strings[0].equalsIgnoreCase("admin")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.MENU_COMMAND", player);
                        }else if(strings[0].equalsIgnoreCase("add")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_FIRST.ADD_COMMAND", player);
                        }else if(strings[0].equalsIgnoreCase("set")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_FIRST.SET_COMMAND", player);
                        }else if(strings[0].equalsIgnoreCase("remove")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_FIRST.REMOVE_COMMAND", player);
                        } else if(strings[0].equalsIgnoreCase("create")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_FIRST.CREATE_COMMAND", player);
                        }
                    } else {
                        config.C_CONFIG_STRING_MESSAGE_NORMAL("PLUGIN_PREFIX", player);
                                config.A_CONFIG_COMMANDS("COMMANDS.NO_PERMISSIONS", player);
                    }
                }else if (strings.length == 2) {
                    if (commandSender.hasPermission("KreditAPI.admin.*")) {
                        if(strings[0].equalsIgnoreCase("add")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_FIRST.CREATE_COMMAND", player);
                        } else if(strings[0].equalsIgnoreCase("set")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_SECOND.SET_COMMAND", player);
                        } else if(strings[0].equalsIgnoreCase("remove")) {
                                    config.A_CONFIG_COMMANDS("COMMANDS.WRONG_USAGE.WRONG_SECOND.REMOVE_COMMAND", player);
                        } else if(strings[0].equalsIgnoreCase("create")) {
                            Player target = Bukkit.getPlayer(strings[0]);
                            if(target != null) {
                                if(kredits.GET_PLAYER_EXIST(target.getUniqueId())) {
                                    config.C_CONFIG_STRING_MESSAGE_NORMAL("PLAYER.PLAYER_ACCOUNT_EXIST", player);
                                } else {
                                    kredits.INSERT_PLAYER(target, target.getUniqueId(), 0);
                                    config.B_CONFIG_MESSAGE("ADMIN.YOU_CREATE_KREDIT_ACCOUNT", player, target, 0, 0);
                                    config.B_CONFIG_MESSAGE("PLAYER.NOTIFY_CREATE_PLAYER_ACCOUNT", player, target, 0, 0);
                                }
                                } else {
                                config.C_CONFIG_STRING_MESSAGE_NORMAL("ADMIN.PLAYER_OFFLINE", player);
                            }
                        }
                    } else {
                                config.A_CONFIG_COMMANDS("COMMANDS.NO_PERMISSIONS", player);
                    }
                } else if(strings.length == 3) {
                    if (commandSender.hasPermission("KreditAPI.admin.*")) {
                        if(strings[0].equalsIgnoreCase("add")) {
                        Player target = Bukkit.getPlayer(strings[1]);
                            if(target != null) {
                                if(integerControler.isInt(strings[2])) {
                                    int value = Integer.parseInt(strings[2]);
                                    int new_balance = kredits.NEW_BALANCE(uuid, value);
                                    kredits.ADD_KREDIT(target.getUniqueId(), value);
                                    config.B_CONFIG_MESSAGE("ADMIN.YOU_ADD_KREDIT", player, target, value, new_balance);
                                    config.B_CONFIG_MESSAGE("PLAYER.NOTIFY_ADDED_PLAYER_KREDIT", player, target, value, new_balance);
                                } else {
                                    player.sendMessage("Must be number no string !");
                                }
                            } else {
                                        config.C_CONFIG_STRING_MESSAGE_NORMAL("ADMIN.PLAYER_OFFLINE", player);
                            }
                    } else if(strings[0].equalsIgnoreCase("set")) {
                        Player target = Bukkit.getPlayer(strings[1]);
                        if(target != null) {
                            if(integerControler.isInt(strings[2])) {
                                int value = Integer.parseInt(strings[2]);
                                int new_balance = kredits.NEW_BALANCE(uuid, value);
                                kredits.SET_KREDIT(target.getUniqueId(), value);
                                config.B_CONFIG_MESSAGE("ADMIN.YOU_SET_KREDIT", player, target, value, new_balance);
                                config.B_CONFIG_MESSAGE("PLAYER.NOTIFY_SET_PLAYER_KREDIT", player, target, value, new_balance);
                            } else {
                                player.sendMessage("Must be number no string !");
                            }
                        } else {
                                    config.C_CONFIG_STRING_MESSAGE_NORMAL("ADMIN.PLAYER_OFFLINE", player);
                        }
                    }else if(strings[0].equalsIgnoreCase("remove")) {
                        Player target = Bukkit.getPlayer(strings[1]);
                        if (target != null) {
                            if(integerControler.isInt(strings[2])) {
                                int value = Integer.parseInt(strings[2]);
                                int new_balance = kredits.NEW_BALANCE(uuid, value);
                                kredits.REMOVE_KREDIT(target.getUniqueId(), value);
                                config.B_CONFIG_MESSAGE("ADMIN.YOU_REMOVE_KREDIT", player, target, value, new_balance);
                                config.B_CONFIG_MESSAGE("PLAYER.NOTIFY_REMOVE_PLAYER_KREDIT", player, target, value, new_balance);
                            } else {
                                player.sendMessage("Must be number no string !");
                            }
                        } else {
                                    config.C_CONFIG_STRING_MESSAGE_NORMAL("ADMIN.PLAYER_OFFLINE", player);
                        }
                    }
                    } else {
                                config.A_CONFIG_COMMANDS("COMMANDS.NO_PERMISSIONS", player);                    }
                }
            } else {
                commandSender.sendMessage("[KREDITAPI] This command only for Players in Game !");
            }
        }
        return false;
    }
}
