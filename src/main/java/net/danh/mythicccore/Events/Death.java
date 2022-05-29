package net.danh.mythicccore.Events;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.experience.EXPSource;
import net.danh.mythicccore.MythiccCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.dcore.Enchant.Lore.hasEnchant;
import static net.danh.dcore.Random.Number.getRandomInt;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Data.SoulPoints.getSoulPoints;
import static net.danh.mythicccore.Data.SoulPoints.removeSoulPoints;
import static net.danh.mythicccore.Utils.Resources.*;

public class Death implements Listener {

    @EventHandler
    public void onMythicMobDeath(MythicMobDeathEvent e) {
        Player p = (Player) e.getKiller();
        if (p == null) {
            return;
        }
        ItemStack item = p.getInventory().getItemInMainHand();
        if (hasEnchant(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.MONEY.KEY")).toUpperCase(), item)) {
            MythiccCore.getEconomy().depositPlayer(p, getEnchantLevel(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ENCHANTS.MONEY.KEY")).toUpperCase(), item));
        }
        double mobLevel = e.getMobLevel();
        int intmoblevel = (int) mobLevel;
        int mobxp = getmobfile().getInt("MOBS." + e.getMobType().getInternalName().toUpperCase() + ".XP");
        PlayerData.get(p).giveExperience(intmoblevel * mobxp, EXPSource.OTHER);
    }

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        Player p = e.getEntity();
        Bukkit.getScheduler().scheduleSyncDelayedTask(MythiccCore.get(), () -> p.spigot().respawn(), 2);
        ItemStack items = p.getInventory().getItemInMainHand();
        removeSoulPoints(p, 1);
        sendPlayerMessage(p, getlangString("SOULPOINTS_DEATH").replaceAll("%soul%", String.valueOf(getSoulPoints(p))));
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
        if (getSoulPoints(p) <= getsettingfile().getInt("SETTINGS.MINIMUM_SOUL_POINTS")) {
            List<Integer> fullSlots = new ArrayList<>();
            PlayerInventory playerInventory = p.getInventory();
            for (int i = 1; i <= playerInventory.getSize(); i++) {
                if (playerInventory.getItem(i) != null) {
                    fullSlots.add(i);
                }
            }
            if (fullSlots.size() == 0) {
                return;
            }
            int slot = getRandomInt(1, fullSlots.size());
            if (playerInventory.getItem(slot) != null) {
                if (Objects.requireNonNull(playerInventory.getItem(slot)).getItemMeta() != null) {
                    String item = Objects.requireNonNull(Objects.requireNonNull(playerInventory.getItem(slot)).getItemMeta()).getDisplayName();
                    int amount = Objects.requireNonNull(playerInventory.getItem(slot)).getAmount();
                    sendPlayerMessage(p, getlangString("LOSE_ITEM").replaceAll("%item%", item).replaceAll("%amount%", String.valueOf(amount)));
                    playerInventory.setItem(slot, null);
                }
            }
        }
    }
}
