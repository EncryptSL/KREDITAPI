package org.EncryptSL.kredit.api;

import org.EncryptSL.kredit.api.COMMANDS.MAIN_MENU_COMMAND;
import org.EncryptSL.kredit.api.LISTENERS.PlayerJoinClassEvent;
import org.EncryptSL.kredit.api.MySQL.Connector;
import org.bukkit.plugin.java.JavaPlugin;
import org.fusesource.jansi.Ansi;

import java.util.logging.Level;

public class KreditMainClass extends JavaPlugin {

    public static KreditMainClass kreditMainClass;
    public Connector connector = new Connector();

    @Override
    public void onEnable() {
        kreditMainClass = this;
        if(getDescription().getAuthors().contains("EncryptSL")) {
            getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.YELLOW).toString() + "Plugin enabled !");
            getConfig().options().copyDefaults(true);
            saveConfig();
            connector.ConnectToDatabase();
            getServer().getPluginManager().registerEvents(new PlayerJoinClassEvent(), this);
            getCommand("kredit").setExecutor(new MAIN_MENU_COMMAND());
            getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.GREEN).toString() + "[" + getDescription().getName() + "]" + "Plugin working normal without, rewriting !");
            getPluginLoader().enablePlugin(this);
        } else {
            getPluginLoader().disablePlugin(this);
            getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.RED).toString() + "Plugin not working, because you rewrite authors !");
        }
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.YELLOW).toString() + "[" + getDescription().getName() + "]" + "Plugin disabled !");
        connector.CloseConnection();
    }

    public static KreditMainClass getKreditMainClass() {
        return kreditMainClass;
    }

}
