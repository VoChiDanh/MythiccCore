package net.danh.mythicccore.Compatible;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.mythicccore.Data.Born;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.mythicccore.Data.SoulPoints.getMaxSoulPoints;
import static net.danh.mythicccore.Data.SoulPoints.getSoulPoints;
import static net.danh.mythicccore.Utils.Resources.getitemfile;

public class Placeholder extends PlaceholderExpansion {

    @Override
    public @NotNull
    String getIdentifier() {
        return "mythicc";
    }

    @Override
    public @NotNull
    String getAuthor() {
        return MythiccCore.get().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull
    String getVersion() {
        return MythiccCore.get().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String identifier) {
        if (p == null) {
            return "0";
        }
        if (identifier.equalsIgnoreCase("year")) {
            return String.valueOf(Born.getYear(p));
        }
        if (identifier.equalsIgnoreCase("age")) {
            return String.valueOf(Born.getAge(p));
        }
        if (identifier.startsWith("stats_")) {
            String stats = identifier.substring(6).toUpperCase();
            ItemStack item = p.getInventory().getItemInMainHand();
            String key = getitemfile().getString("ENCHANTS." + stats + ".KEY");
            return String.valueOf(getEnchantLevel(MythiccCore.get(), key, item));
        }
        if (identifier.equalsIgnoreCase("soulpoints")) {
            return String.valueOf(getSoulPoints(p));
        }
        if (identifier.equalsIgnoreCase("max_soulpoints")) {
            return String.valueOf(getMaxSoulPoints(p));
        }
        return null;
    }
}
