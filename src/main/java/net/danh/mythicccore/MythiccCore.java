package net.danh.mythicccore;

import net.danh.mythicccore.Commands.MythiccCMD;
import net.danh.mythicccore.Commands.OpenRegeneratorGui;
import net.danh.mythicccore.Commands.OpenUpgradeGui;
import net.danh.mythicccore.Events.Inventory;
import net.danh.mythicccore.Manager.AdvancementManager;
import net.danh.mythicccore.Utils.Resources;
import net.danh.mythicccore.Utils.VersionChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

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
        new OpenRegeneratorGui(this);
        new MythiccCMD(this);
        new OpenUpgradeGui(this);
        getLogger().log(Level.INFO, "Server version: " + VersionChecker.getServerVersion());
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            getLogger().log(Level.INFO, "Hooking into MMOItems");
        }
        if (getServer().getPluginManager().getPlugin("MythicLib") != null) {
            getLogger().log(Level.INFO, "Hooking into MythicLib");
        }
        Resources.createfiles();
    }

    @Override
    public void onDisable() {
        for (Player p : getServer().getOnlinePlayers()) {
            p.closeInventory();
        }
        Resources.saveconfig();
        Resources.savelanguage();
        Resources.savegui();
        Resources.saveupgrade();
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
