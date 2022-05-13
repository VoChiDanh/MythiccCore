package net.danh.mythicccore.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.danh.dcore.Random.Number.getRandomInt;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Data.SoulPoints.getSoulPoints;
import static net.danh.mythicccore.Data.SoulPoints.removeSoulPoints;
import static net.danh.mythicccore.Utils.Resources.getsettingfile;

public class Death implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        Player p = e.getEntity();
        removeSoulPoints(p, 1);
        sendPlayerMessage(p, "&6Bạn đã chết và còn lại " + getSoulPoints(p) + " SoulPoints");
        if (getSoulPoints(p) <= getsettingfile().getInt("SETTINGS.MINIMUM_SOUL_POINTS")) {
            List<Integer> fullSlots = new ArrayList<>();
            PlayerInventory playerInventory = p.getInventory();
            for (int i = 0; i <= playerInventory.getSize(); i++) {
                if (playerInventory.getItem(i) != null)
                    fullSlots.add(i);
            }
            if (fullSlots.size() == 0) {
                return;
            }
            int slot = getRandomInt(0, fullSlots.size());
            if (playerInventory.getItem(slot) != null) {
                if (playerInventory.getItem(slot).getItemMeta() != null) {
                    String item = playerInventory.getItem(slot).getItemMeta().getDisplayName();
                    playerInventory.setItem(slot, null);
                    sendPlayerMessage(p, "&cBạn đã bị mất vật phẩm: " + item);
                }
            }
        }
    }
}
