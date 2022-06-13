package net.danh.mythicccore.Events;

import net.Indyuce.mmocore.api.event.PlayerExperienceGainEvent;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.dcore.Enchant.Lore.hasEnchant;
import static net.danh.mythicccore.Utils.Resources.getitemfile;

public class EXP implements Listener {

    @EventHandler
    public void onExpGain(PlayerExperienceGainEvent e) {
        Player p = e.getPlayer();
        double exp = e.getExperience();
        ItemStack i = p.getInventory().getItemInMainHand();
        if (hasEnchant(MythiccCore.get(), getitemfile().getString("ENCHANTS.EXPERIENCE.KEY"), i)) {
            e.setExperience((int) (exp * getEnchantLevel(MythiccCore.get(), getitemfile().getString("ENCHANTS.EXPERIENCE.KEY"), i)));
        }
    }
}
