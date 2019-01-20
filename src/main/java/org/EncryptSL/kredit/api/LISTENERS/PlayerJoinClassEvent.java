package org.EncryptSL.kredit.api.LISTENERS;

import org.EncryptSL.kredit.api.Kredit.SQL_SELECTOR;
import org.EncryptSL.kredit.api.KreditMainClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.fusesource.jansi.Ansi;

import java.util.UUID;
import java.util.logging.Level;

public class PlayerJoinClassEvent implements Listener {

    private SQL_SELECTOR sql_selector = new SQL_SELECTOR();

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {
            if (sql_selector.GET_PLAYER_EXIST(uuid)) {
                KreditMainClass.getKreditMainClass().getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.YELLOW).toString() + "[" + KreditMainClass.getKreditMainClass().getDescription().getName() + "]" + "Kredit ucet hrace " + player.getName() + " jiz existuje !");
            } else {
                sql_selector.INSERT_PLAYER(player, uuid, 0);
                KreditMainClass.getKreditMainClass().getLogger().log(Level.INFO, Ansi.ansi().fg(Ansi.Color.YELLOW).toString() + "[" + KreditMainClass.getKreditMainClass().getDescription().getName() + "]" + "Vytvarim Kredit ucet hrace " + player.getName() + " !");
            }
        }

    }

}
