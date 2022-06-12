package net.danh.mythicccore;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Utils.File;
import net.danh.mythicccore.Commands.*;
import net.danh.mythicccore.Compatible.Placeholder;
import net.danh.mythicccore.Events.*;
import net.danh.mythicccore.Utils.Resources;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

import static net.danh.dcore.DCore.RegisterDCore;
import static net.danh.mythicccore.Utils.Resources.*;

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
        getLogger().log(Level.INFO, "Detected server version: " + nmsAssistant.getNMSVersion());
        if (getServer().getPluginManager().getPlugin("RealisticSeasons") != null) {
            getLogger().log(Level.INFO, "Hooked onto RealisticSeasons v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("RealisticSeasons")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Can not found RealisticSeasons");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            getLogger().log(Level.INFO, "Hooked onto MMOItems v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("MMOItems")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Can not found MMOItems");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("MMOCore") != null) {
            getLogger().log(Level.INFO, "Hooked onto MMOCore v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("MMOCore")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Can not found MMOCore");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            getLogger().log(Level.INFO, "Hooked onto MythicMobs v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("MythicMobs")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Can not found MythicMobs");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().log(Level.INFO, "Hooked onto PlaceholderAPI v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("PlaceholderAPI")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Can not found PlaceholderAPI");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new EXP(), this);
        getServer().getPluginManager().registerEvents(new Season(), this);
        getServer().getPluginManager().registerEvents(new Inventory(), this);
        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new EnchantItem(), this);
        new MythiccCMD(this);
        new MythiccItems(this);
        new OpenRegeneratorGui(this);
        new OpenUpgradeGui(this);
        new Born(this);
        new Placeholder().register();
        RegisterDCore(this);
        Resources.createfiles();
        File.updateFile(MythiccCore.get(), getconfigfile(), "config.yml");
        File.updateFile(MythiccCore.get(), getguifile(), "gui.yml");
        File.updateFile(MythiccCore.get(), getitemfile(), "items.yml");
        File.updateFile(MythiccCore.get(), getlanguagefile(), "language.yml");
        File.updateFile(MythiccCore.get(), getmobfile(), "mobs.yml");
        File.updateFile(MythiccCore.get(), getsettingfile(), "setting.yml");
        File.updateFile(MythiccCore.get(), getupgradefile(), "upgrade.yml");
    }

    @Override
    public void onDisable() {
        Resources.saveconfig();
        Resources.savelanguage();
        Resources.savegui();
        Resources.savemob();
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
