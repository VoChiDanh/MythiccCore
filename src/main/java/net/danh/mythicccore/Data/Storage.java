package net.danh.mythicccore.Data;

import net.danh.mythicccore.Utils.Resources;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.danh.mythicccore.Utils.Resources.getdatafile;

public class Storage {

    public static void savePlayerData(@NotNull Player p) {
        getdatafile().set("PLAYERS." + p.getName() + ".SOUL_POINTS.HAVE", SoulPoints.getSoulPoints(p));
        getdatafile().set("PLAYERS." + p.getName() + ".SOUL_POINTS.MAXIMUM", SoulPoints.getMaxSoulPoints(p));
        Resources.savedata();
    }

    public static void loadPlayerData(Player p) {
        SoulPoints.setSoulPoints(p, SoulPoints.getSPData(p));
        SoulPoints.setMaxSoulPoints(p, SoulPoints.getMaxSPData(p));
    }
}
