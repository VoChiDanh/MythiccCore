package net.danh.mythicccore.Events;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.dcore.Enchant.Lore.hasEnchant;
import static net.danh.dcore.Random.Number.getRandomInt;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Data.SoulPoints.getSoulPoints;
import static net.danh.mythicccore.Data.SoulPoints.removeSoulPoints;
import static net.danh.mythicccore.Utils.Resources.getitemfile;
import static net.danh.mythicccore.Utils.Resources.getsettingfile;

public class Death implements Listener {

    @EventHandler
    public void onMythicMobDeath(MythicMobDeathEvent e) {
        Player p = (Player) e.getKiller();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (hasEnchant(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.MONEY.KEY")).toUpperCase(), item)) {
            MythiccCore.getEconomy().depositPlayer(p, getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.MONEY.KEY")).toUpperCase(), item));
        }
    }

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        Player p = e.getEntity();
        removeSoulPoints(p, 1);
        sendPlayerMessage(p, "&6Bạn đã chết và còn lại " + getSoulPoints(p) + " SoulPoints");
        if (getSoulPoints(p) <= getsettingfile().getInt("SETTINGS.MINIMUM_SOUL_POINTS")) {
            List<Integer> fullSlots = new ArrayList<>();
            PlayerInventory playerInventory = p.getInventory();
            for (int i = 1; i <= playerInventory.getSize(); i++) {
                if (playerInventory.getItem(i) != null) {
                    fullSlots.add(i);
                }
            }
            if (fullSlots.size() == 0) {
                return;
            }
            int slot = getRandomInt(1, fullSlots.size());
            if (playerInventory.getItem(slot) != null) {
                if (Objects.requireNonNull(playerInventory.getItem(slot)).getItemMeta() != null) {
                    String item = Objects.requireNonNull(Objects.requireNonNull(playerInventory.getItem(slot)).getItemMeta()).getDisplayName();
                    sendPlayerMessage(p, "&cBạn đã bị mất vật phẩm: " + item);
                    playerInventory.setItem(slot, null);
                }
            }
        }
    }
}
