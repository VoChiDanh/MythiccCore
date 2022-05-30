package net.danh.mythicccore.Events;

import me.casperge.realisticseasons.api.SeasonsAPI;
import net.danh.mythicccore.Data.Born;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Data.Storage.loadPlayerData;
import static net.danh.mythicccore.Utils.Resources.getlangString;
import static net.danh.mythicccore.Utils.Resources.getlanguagefile;

public class Join implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player player = e.getPlayer();
        SeasonsAPI seasonsapi = SeasonsAPI.getInstance();
        loadPlayerData(player);
        for (int i = 0; i < MythiccCore.getInvisible_list().size(); i++) {
            player.hidePlayer(MythiccCore.get(), MythiccCore.getInvisible_list().get(i));
        }
        if (seasonsapi.getDate(player.getWorld()).getDay() >= 30) {
            sendPlayerMessage(player, getlanguagefile().getString("BONUS_XP_DAYS"));
        }
        if (Born.YearIsNull(player)) {
            sendPlayerMessage(player, getlangString("DO_NOT_HAVE_YEAR"));
        }
    }
}
