package net.danh.mythicccore.Events;

import me.casperge.realisticseasons.calendar.Date;
import me.casperge.realisticseasons.calendar.DayChangeEvent;
import net.danh.dcore.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

import static net.danh.mythicccore.Utils.Resources.getlanguagefile;

public class Season implements Listener {

    @EventHandler
    public void onSeasonChange(DayChangeEvent e) {
        Date to = e.getTo();
        if (to.getDay() >= 30) {
            Bukkit.broadcastMessage(Chat.colorize(Objects.requireNonNull(getlanguagefile().getString("BONUS_XP_DAYS"))));
        }
    }
}
