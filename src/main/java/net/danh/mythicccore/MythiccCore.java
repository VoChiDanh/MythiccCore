package net.danh.mythicccore;

import net.danh.mythicccore.Commands.MythiccCMD;
import net.danh.mythicccore.Commands.OpenRegeneratorGui;
import net.danh.mythicccore.Commands.OpenUpgradeGui;
import net.danh.mythicccore.Commands.SoulPoints;
import net.danh.mythicccore.Compatible.Placeholder;
import net.danh.mythicccore.Events.Death;
import net.danh.mythicccore.Events.Inventory;
import net.danh.mythicccore.Events.Join;
import net.danh.mythicccore.Events.Quit;
import net.danh.mythicccore.Manager.AdvancementManager;
import net.danh.mythicccore.Utils.Resources;
import net.danh.mythicccore.Utils.VersionChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static net.danh.mythicccore.Data.Storage.savePlayerData;

public final class MythiccCore extends JavaPlugin {

    private static MythiccCore instance;
    private AdvancementManager advancementManager;


    public static MythiccCore get() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        VersionChecker.checkServerVersion();
    }

    @Override
    public void onEnable() {
        if (!initialSetupSuccessful()) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new Inventory(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        new OpenRegeneratorGui(this);
        new MythiccCMD(this);
        new OpenUpgradeGui(this);
        new SoulPoints(this);
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            getLogger().log(Level.INFO, "Hooking into MMOItems");
        }
        if (getServer().getPluginManager().getPlugin("MythicLib") != null) {
            getLogger().log(Level.INFO, "Hooking into MythicLib");
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Placeholder().register();
            getLogger().log(Level.INFO, "Hooking into PlaceholderAPI");
        }
        Resources.createfiles();
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
        Resources.savedata();
    }

    private boolean initialSetupSuccessful() {
        if (!VersionChecker.isSupportedVersion()) {
            return false;
        }

        VersionChecker.registerClasses();
        return true;
    }

    public AdvancementManager getAdvancementManager() {
        return advancementManager;
    }

    public void setAdvancementManager(AdvancementManager advancementManager) {
        this.advancementManager = advancementManager;
    }
}
