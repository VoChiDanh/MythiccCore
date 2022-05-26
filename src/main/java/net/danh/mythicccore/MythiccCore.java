package net.danh.mythicccore;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Utils.Chat;
import net.danh.mythicccore.Commands.*;
import net.danh.mythicccore.Compatible.Placeholder;
import net.danh.mythicccore.Data.Storage;
import net.danh.mythicccore.Events.*;
import net.danh.mythicccore.Utils.Resources;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.logging.Level;

import static net.danh.dcore.DCore.RegisterDCore;
import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.dcore.Enchant.Lore.hasEnchant;
import static net.danh.mythicccore.Data.Storage.loadPlayerData;
import static net.danh.mythicccore.Data.Storage.savePlayerData;
import static net.danh.mythicccore.Utils.Resources.getitemfile;

public final class MythiccCore extends JavaPlugin {

    private static final ArrayList<Player> invisible_list = new ArrayList<>();
    private static MythiccCore instance;
    private static Economy econ;

    public static Economy getEconomy() {
        return econ;
    }

    public static MythiccCore get() {
        return instance;
    }

    public static ArrayList<Player> getInvisible_list() {
        return invisible_list;
    }


    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        NMSAssistant nmsAssistant = new NMSAssistant();
        getLogger().log(Level.INFO, Chat.colorize("&6Server version:&3 " + nmsAssistant.getNMSVersion()));
        getLogger().log(Level.INFO, "--------------------------------------------");
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            getServer().getPluginManager().registerEvents(new Inventory(), this);
            new OpenRegeneratorGui(this);
            new OpenUpgradeGui(this);
            getLogger().log(Level.INFO, "Hooking into MMOItems");
            getLogger().log(Level.INFO, Chat.colorize("&a✓&f Upgrade items features"));
            getLogger().log(Level.INFO, Chat.colorize("&a✓&f Identify items features"));
        } else {
            getLogger().log(Level.INFO, "Can not found MMOItems");
            getLogger().log(Level.INFO, Chat.colorize("&c✘&f Upgrade items features"));
            getLogger().log(Level.INFO, Chat.colorize("&c✘&f Identify items features"));
        }
        getLogger().log(Level.INFO, "--------------------------------------------");
        if (getServer().getPluginManager().getPlugin("MMOCore") != null) {
            getServer().getPluginManager().registerEvents(new EXP(), this);
            getLogger().log(Level.INFO, "Hooking into MMOCore");
            getLogger().log(Level.INFO, Chat.colorize("&a✓&f Experience Enchantments"));
        } else {
            getLogger().log(Level.INFO, "Can not found MMOCore");
            getLogger().log(Level.INFO, Chat.colorize("&a✓&f Experience Enchantments"));
        }
        getLogger().log(Level.INFO, "--------------------------------------------");
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().log(Level.INFO, "Hooking into PlaceholderAPI");
            getLogger().log(Level.INFO, Chat.colorize("&a✓&f Placeholder features"));
            new Placeholder().register();
        } else {
            getLogger().log(Level.INFO, "Can not found PlaceholderAPI");
            getLogger().log(Level.INFO, Chat.colorize("&c✘&f Placeholder features"));
        }
        getLogger().log(Level.INFO, "--------------------------------------------");
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Sprint(), this);
        new MythiccCMD(this);
        new SoulPoints(this);
        new MythiccItems(this);
        getLogger().log(Level.INFO, Chat.colorize("&a✓&f SoulPoints features"));
        getLogger().log(Level.INFO, "--------------------------------------------");
        RegisterDCore(this);
        Resources.createfiles();
        if (getServer().getOnlinePlayers().size() > 0) {
            for (Player p : getServer().getOnlinePlayers()) {
                loadPlayerData(p);
                for (int i = 0; i < MythiccCore.getInvisible_list().size(); i++) {
                    p.hidePlayer(MythiccCore.get(), MythiccCore.getInvisible_list().get(i));
                }
            }
        }
        (new BukkitRunnable() {
            public void run() {
                for (Player p : getServer().getOnlinePlayers()) {
                    ItemStack i = p.getInventory().getItemInMainHand();
                    if (!hasEnchant(MythiccCore.get(), getitemfile().getString("ENCHANTS.SOULPOINTS.KEY"), i)) {
                        net.danh.mythicccore.Data.SoulPoints.addSoulPoints(p, 1);
                    } else {
                        net.danh.mythicccore.Data.SoulPoints.addSoulPoints(p, getEnchantLevel(MythiccCore.get(), getitemfile().getString("ENCHANTS.SOULPOINTS.KEY"), i));
                    }
                }
            }
        }).runTaskTimer(this, 1800 * 20L, 1800 * 20L);
        (new BukkitRunnable() {
            public void run() {
                for (Player p : getServer().getOnlinePlayers()) {
                    Storage.savePlayerData(p);
                }
            }
        }).runTaskTimer(this, 3600 * 20L, 3600 * 20L);

    }

    @Override
    public void onDisable() {
        for (Player p : getServer().getOnlinePlayers()) {
            p.closeInventory();
            savePlayerData(p);
        }
        Resources.saveconfig();
        Resources.savelanguage();
        Resources.savegui();
        Resources.saveupgrade();
        Resources.savesetting();
        Resources.saveitem();
        Resources.savedata();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

}
