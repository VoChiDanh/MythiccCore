package net.danh.mythicccore.Data;

import org.bukkit.entity.Player;

import java.util.HashMap;

import static net.danh.mythicccore.Utils.Resources.getdatafile;
import static net.danh.mythicccore.Utils.Resources.getsettingfile;

public class SoulPoints {

    private static final HashMap<String, Integer> sp = new HashMap<>();

    public static int getSPData(Player p) {
        return getdatafile().getInt("PLAYERS." + p.getName() + ".SOUL_POINTS.HAVE");
    }

    public static int getMaxSPData(Player p) {
        return getdatafile().getInt("PLAYERS." + p.getName() + ".SOUL_POINTS.MAXIMUM");
    }

    public static int getSoulPoints(Player p) {
        return sp.get(p.getName() + "_sp");
    }

    public static int getMaxSoulPoints(Player p) {
        return sp.get(p.getName() + "_max_sp");
    }

    public static void setSoulPoints(Player p, Integer amount) {
        sp.put(p.getName() + "_sp", Math.max(amount, 0));
    }

    public static void setMaxSoulPoints(Player p, Integer amount) {
        sp.put(p.getName() + "_max_sp", Math.max(amount, getsettingfile().getInt("SETTINGS.DEFAULT_MAX_SOUL_POINTS")));
    }

    public static void addSoulPoints(Player p, Integer amount) {
        if (amount > 0) {
            if (getSoulPoints(p) + amount < getMaxSoulPoints(p)) {
                sp.replace(p.getName() + "_sp", getSoulPoints(p) + amount);
            } else {
                sp.put(p.getName() + "_sp", getMaxSoulPoints(p));
            }
        }
    }

    public static void addMaxSoulPoints(Player p, Integer amount) {
        if (amount > 0) {
            sp.replace(p.getName() + "_max_sp", getMaxSoulPoints(p) + amount);
        }
    }

    public static void removeSoulPoints(Player p, Integer amount) {
        if (amount > 0) {
            if (getSoulPoints(p) + amount > 0) {
                sp.replace(p.getName() + "_sp", getSoulPoints(p) - amount);
            } else {
                sp.put(p.getName() + "_sp", 0);
            }
        }
    }

    public static void removeMaxSoulPoints(Player p, Integer amount) {
        if (amount > 0) {
            if (getMaxSoulPoints(p) - amount > getsettingfile().getInt("SETTINGS.DEFAULT_MAX_SOUL_POINTS")) {
                sp.replace(p.getName() + "_max_sp", getMaxSoulPoints(p) - amount);
            } else {
                sp.put(p.getName() + "_max_sp", getsettingfile().getInt("SETTINGS.DEFAULT_MAX_SOUL_POINTS"));
            }
        }
    }
}
