package net.danh.mythicccore.Compatible;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.danh.mythicccore.Data.SoulPoints.getMaxSoulPoints;
import static net.danh.mythicccore.Data.SoulPoints.getSoulPoints;

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
    public String onPlaceholderRequest(Player p, @NotNull String identifier) {
        if (p == null) {
            return "0";
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
