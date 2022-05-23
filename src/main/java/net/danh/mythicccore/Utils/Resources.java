package net.danh.mythicccore.Utils;

import net.danh.dcore.Utils.Chat;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static net.danh.dcore.Utils.Items.Lore;
import static net.danh.mythicccore.MythiccCore.get;

public class Resources {

    private static File configFile, languageFile, guiFile, upgradeFile, dataFile, settingFile, itemFile;
    private static FileConfiguration config, language, gui, upgrade, data, setting, item;

    public static void createfiles() {
        configFile = new File(get().getDataFolder(), "config.yml");
        languageFile = new File(get().getDataFolder(), "language.yml");
        guiFile = new File(get().getDataFolder(), "gui.yml");
        upgradeFile = new File(get().getDataFolder(), "upgrade.yml");
        dataFile = new File(get().getDataFolder(), "data.yml");
        settingFile = new File(get().getDataFolder(), "setting.yml");
        itemFile = new File(get().getDataFolder(), "items.yml");

        if (!configFile.exists()) get().saveResource("config.yml", false);
        if (!languageFile.exists()) get().saveResource("language.yml", false);
        if (!guiFile.exists()) get().saveResource("gui.yml", false);
        if (!upgradeFile.exists()) get().saveResource("upgrade.yml", false);
        if (!settingFile.exists()) get().saveResource("setting.yml", false);
        if (!itemFile.exists()) get().saveResource("items.yml", false);
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        gui = new YamlConfiguration();
        upgrade = new YamlConfiguration();
        data = new YamlConfiguration();
        setting = new YamlConfiguration();
        item = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            gui.load(guiFile);
            upgrade.load(upgradeFile);
            data.load(dataFile);
            setting.load(settingFile);
            item.load(itemFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getconfigfile() {
        return config;
    }

    public static FileConfiguration getlanguagefile() {
        return language;
    }

    public static FileConfiguration getguifile() {
        return gui;
    }

    public static FileConfiguration getupgradefile() {
        return upgrade;
    }

    public static FileConfiguration getdatafile() {
        return data;
    }

    public static FileConfiguration getsettingfile() {
        return setting;
    }

    public static FileConfiguration getitemfile() {
        return item;
    }

    public static String getguiString(String path) {
        return Chat.colorize(Objects.requireNonNull(getguifile().getString(path)));
    }

    public static String getlangString(String path) {
        return Chat.colorize(Objects.requireNonNull(getlanguagefile().getString(path)));
    }

    public static String getconfigString(String path) {
        return Chat.colorize(Objects.requireNonNull(getconfigfile().getString(path)));
    }

    public static String getupgradeString(String path) {
        return Chat.colorize(Objects.requireNonNull(getupgradefile().getString(path)));
    }

    public static List<String> getguiStringList(String path) {
        return Lore(getguifile().getStringList(path));
    }

    public static List<String> getlangStringList(String path) {
        return Lore(getlanguagefile().getStringList(path));
    }

    public static List<String> getconfigStringList(String path) {
        return Lore(getconfigfile().getStringList(path));
    }

    public static List<String> getupgradeStringList(String path) {
        return Lore(getupgradefile().getStringList(path));
    }

    public static int getguiInt(String path) {
        return getguifile().getInt(path);
    }

    public static int getlangInt(String path) {
        return getlanguagefile().getInt(path);
    }

    public static int getconfigInt(String path) {
        return getconfigfile().getInt(path);
    }

    public static int getupgradeInt(String path) {
        return getupgradefile().getInt(path);
    }

    public static boolean getguiboolean(String path) {
        return getguifile().getBoolean(path);
    }

    public static boolean getlangboolean(String path) {
        return getlanguagefile().getBoolean(path);
    }

    public static boolean getconfigboolean(String path) {
        return getconfigfile().getBoolean(path);
    }

    public static boolean getupgradeboolean(String path) {
        return getupgradefile().getBoolean(path);
    }

    public static void reloadfiles() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
        gui = YamlConfiguration.loadConfiguration(guiFile);
        upgrade = YamlConfiguration.loadConfiguration(upgradeFile);
        setting = YamlConfiguration.loadConfiguration(settingFile);
        item = YamlConfiguration.loadConfiguration(itemFile);
    }

    public static void saveconfig() {
        try {
            config.save(configFile);
        } catch (IOException ignored) {
        }
    }

    public static void savelanguage() {
        try {
            language.save(languageFile);
        } catch (IOException ignored) {
        }
    }

    public static void savegui() {
        try {
            gui.save(guiFile);
        } catch (IOException ignored) {
        }
    }

    public static void saveupgrade() {
        try {
            upgrade.save(upgradeFile);
        } catch (IOException ignored) {
        }
    }

    public static void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    public static void savesetting() {
        try {
            setting.save(settingFile);
        } catch (IOException ignored) {
        }
    }

    public static void saveitem() {
        try {
            item.save(itemFile);
        } catch (IOException ignored) {
        }
    }

}
