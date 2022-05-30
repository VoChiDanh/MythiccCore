package net.danh.mythicccore.Data;

import me.casperge.realisticseasons.api.SeasonsAPI;
import net.danh.mythicccore.Utils.Resources;
import org.bukkit.entity.Player;

public class Born {

    public static void setYear(Player p, int years) {
        SeasonsAPI seasonsAPI = SeasonsAPI.getInstance();
        if (years < seasonsAPI.getDate(p.getWorld()).getYear() && years > 0) {
            if (YearIsNull(p)) {
                Resources.getdatafile().set("PLAYERS." + p.getName() + ".YEARS", years);
                Resources.savedata();
            }
        }
    }

    public static int getAge(Player p) {
        SeasonsAPI seasonsAPI = SeasonsAPI.getInstance();
        if (!YearIsNull(p)) {
            return seasonsAPI.getDate(p.getWorld()).getYear() - getYear(p);
        }
        return 0;
    }

    public static int getYear(Player p) {
        return Resources.getdatafile().getInt("PLAYERS." + p.getName() + ".YEARS");
    }

    public static boolean YearIsNull(Player p) {
        return Resources.getdatafile().getInt("PLAYERS." + p.getName() + ".YEARS") == 0;
    }
}
