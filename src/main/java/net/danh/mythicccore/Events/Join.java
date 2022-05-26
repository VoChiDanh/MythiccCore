package net.danh.mythicccore.Events;

import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import static net.danh.mythicccore.Data.Storage.loadPlayerData;

public class Join implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player player = e.getPlayer();
        loadPlayerData(player);
        for (int i = 0; i < MythiccCore.getInvisible_list().size(); i++) {
            player.hidePlayer(MythiccCore.get(), MythiccCore.getInvisible_list().get(i));
        }
    }
}
