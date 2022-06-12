package net.danh.mythicccore.Events;

import net.danh.dcore.Events.EnchantItemEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.getlanguagefile;

public class EnchantItem implements Listener {

    @EventHandler
    public void onEnchant(EnchantItemEvent e) {
        Player p = e.getPlayer();
        String enchant = e.getEnchant();
        Integer level = e.getLevel();
        ItemStack item = e.getItem();
        if (item.getItemMeta() != null) {
            sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("ENCHANT_SUCCESS"))
                    .replaceAll("%enchant%", enchant)
                    .replaceAll("%level%", String.format("%,d", level))
                    .replaceAll("%item%", item.getItemMeta().getDisplayName()));
        }
    }
}
