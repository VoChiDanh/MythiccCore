package net.danh.mythicccore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import static net.danh.mythicccore.Data.Storage.savePlayerData;

public class Quit implements Listener {


    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent e) {
        savePlayerData(e.getPlayer());
    }
}
