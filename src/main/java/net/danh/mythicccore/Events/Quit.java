package net.danh.mythicccore.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.danh.mythicccore.Data.Storage.savePlayerData;

public class Quit implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        savePlayerData(e.getPlayer());
    }
}
