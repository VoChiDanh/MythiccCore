package net.danh.mythicccore;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Utils.Chat;
import net.danh.mythicccore.Commands.*;
import net.danh.mythicccore.Compatible.Placeholder;
import net.danh.mythicccore.Data.Storage;
import net.danh.mythicccore.Events.Death;
import net.danh.mythicccore.Events.Inventory;
import net.danh.mythicccore.Events.Join;
import net.danh.mythicccore.Events.Quit;
import net.danh.mythicccore.Utils.Resources;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

import static net.danh.dcore.DCore.*;
import static net.danh.mythicccore.Data.Storage.savePlayerData;

public final class MythiccCore extends JavaPlugin {

    private static MythiccCore instance;


    public static MythiccCore get() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        if (getServer().getOnlinePlayers().size() > 0) {
            for (Player p : getServer().getOnlinePlayers()) {
                p.kickPlayer("Rejoin in few second");
            }
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
        new MythiccCMD(this);
        new SoulPoints(this);
        new MythiccItems(this);
        getLogger().log(Level.INFO, Chat.colorize("&a✓&f SoulPoints features"));
        getLogger().log(Level.INFO, "--------------------------------------------");
        RegisterDCore(this);
        Resources.createfiles();
        (new BukkitRunnable() {
            public void run() {
                for (Player p : getServer().getOnlinePlayers()) {
                    net.danh.mythicccore.Data.SoulPoints.addSoulPoints(p, 1);
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

}
