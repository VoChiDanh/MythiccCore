package net.danh.mythicccore;

import net.danh.mythicccore.Commands.MythiccCMD;
import net.danh.mythicccore.Commands.OpenRegeneratorGui;
import net.danh.mythicccore.Events.Inventory;
import net.danh.mythicccore.Utils.NMS.NMSAssistant;
import net.danh.mythicccore.Utils.Resources;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

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
        getServer().getPluginManager().registerEvents(new Inventory(), this);
        new OpenRegeneratorGui(this);
        new MythiccCMD(this);
        NMSAssistant nmsAssistant = new NMSAssistant();
        getLogger().log(Level.INFO, "Server version: " + nmsAssistant.getNMSVersion());
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
    }
}
