package net.danh.mythicccore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class Sprint implements Listener {

    @EventHandler
    public void onSprint(PlayerToggleSprintEvent e) {
        Player p = e.getPlayer();
        if (p.isSprinting()) {
            e.setCancelled(true);
        }
    }
}
