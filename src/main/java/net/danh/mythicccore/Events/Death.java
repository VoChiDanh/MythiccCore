package net.danh.mythicccore.Events;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import me.casperge.realisticseasons.api.SeasonsAPI;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.experience.EXPSource;
import net.danh.mythicccore.MythiccCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.dcore.Enchant.Lore.hasEnchant;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.*;

public class Death implements Listener {

    @EventHandler
    public void onMythicMobDeath(MythicMobDeathEvent e) {
        SeasonsAPI seasonsapi = SeasonsAPI.getInstance();
        if (e.getKiller() instanceof Player p) {
            ItemStack item = p.getInventory().getItemInMainHand();
            if (hasEnchant(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.MONEY.KEY")).toUpperCase(), item)) {
                MythiccCore.getEconomy().depositPlayer(p, getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.MONEY.KEY")).toUpperCase(), item));
            }
            double mobLevel = e.getMobLevel();
            int intmoblevel = (int) mobLevel;
            int mobxp = getmobfile().getInt("MOBS." + e.getMobType().getInternalName().toUpperCase() + ".XP");
            if (seasonsapi.getDate(p.getWorld()).getDay() < 30) {
                int xp_r = intmoblevel * mobxp;
                PlayerData.get(p).giveExperience(xp_r, EXPSource.SOURCE);
            } else {
                int xp_r = 2 * (intmoblevel * mobxp);
                PlayerData.get(p).giveExperience(xp_r, EXPSource.SOURCE);
            }
        }
    }

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        Player p = e.getEntity();
        ItemStack items = p.getInventory().getItemInMainHand();
        if (!hasEnchant(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 2);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        } else if (getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) == 1 && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 4);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        } else if (getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) == 2 && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 6);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        } else if (getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) == 3 && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 8);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        } else if (getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) == 4 && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 10);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        } else if (getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) == 5 && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 20);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        } else if (getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.PURSE.KEY")).toUpperCase(), items) >= 6 && !p.hasPermission("death.purse")) {
            EconomyResponse ee = MythiccCore.getEconomy().withdrawPlayer(p, MythiccCore.getEconomy().getBalance(p) / 50);
            if (ee.transactionSuccess()) {
                sendPlayerMessage(p, getlangString("MONEY_DEATH").replaceAll("%money%", String.valueOf(MythiccCore.getEconomy().getBalance(p))));
            }
        }
    }
}
