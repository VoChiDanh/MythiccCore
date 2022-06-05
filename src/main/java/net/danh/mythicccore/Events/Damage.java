package net.danh.mythicccore.Events;

import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.core.mobs.ActiveMob;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static net.danh.dcore.Utils.Player.sendPlayerMessageType;
import static net.danh.mythicccore.Utils.Resources.getlangString;
import static net.danh.mythicccore.Utils.Resources.getmobfile;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player p) {
            Entity entity = e.getEntity();
            BukkitAPIHelper bukkitAPIHelper = new BukkitAPIHelper();
            if (bukkitAPIHelper.isMythicMob(entity)) {
                ActiveMob mob = bukkitAPIHelper.getMythicMobInstance(entity);
                int level = PlayerData.get(p).getLevel();
                int mob_level_max = getmobfile().getInt("MOBS." + mob.getType().getInternalName().toUpperCase() + ".LEVEL.MAX");
                int mob_level_min = getmobfile().getInt("MOBS." + mob.getType().getInternalName().toUpperCase() + ".LEVEL.MIN");
                if (mob_level_max == 0 && mob_level_min == 0) {
                    e.setDamage(0);
                    mob.getEntity().setHealth(mob.getEntity().getMaxHealth());
                    e.setCancelled(true);
                    mob.getEntity().setHealth(mob.getEntity().getMaxHealth());
                    return;
                }
                if (level >= mob_level_max) {
                    e.setDamage(0);
                    mob.getEntity().setHealth(mob.getEntity().getMaxHealth());
                    e.setCancelled(true);
                    mob.getEntity().setHealth(mob.getEntity().getMaxHealth());
                    sendPlayerMessageType(p, null, getlangString("LEVEL_SO_HIGH").replaceAll("%level%", String.valueOf(mob_level_max)).replaceAll("%mob%", mob.getDisplayName()));
                } else if (level < mob_level_min) {
                    e.setDamage(0);
                    mob.getEntity().setHealth(mob.getEntity().getMaxHealth());
                    e.setCancelled(true);
                    mob.getEntity().setHealth(mob.getEntity().getMaxHealth());
                    sendPlayerMessageType(p, null, getlangString("LEVEL_SO_LOW").replaceAll("%level%", String.valueOf(mob_level_min)).replaceAll("%mob%", mob.getDisplayName()));
                }
            }
        }
    }
}
