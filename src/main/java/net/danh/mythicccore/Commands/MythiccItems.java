package net.danh.mythicccore.Commands;

import net.danh.dcore.Commands.CMDBase;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.danh.dcore.Enchant.Lore.*;
import static net.danh.dcore.Random.Number.isInteger;
import static net.danh.dcore.Utils.Items.makeItem;
import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.getitemfile;
import static net.danh.mythicccore.Utils.Resources.getlangString;
import static org.bukkit.Material.valueOf;

public class MythiccItems extends CMDBase {

    public MythiccItems(JavaPlugin core) {
        super(core, "MythiccItems");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("Mythicc.Admin")) {
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("enchant")) {
                    if (isInteger(args[3])) {
                        String name = args[2].toUpperCase();
                        String lore = getitemfile().getString("ENCHANTS." + name + ".NAME");
                        String key = getitemfile().getString("ENCHANTS." + name + ".KEY");
                        if (key == null || lore == null) {
                            sendPlayerMessage(p, getlangString("UNKNOWN_KEY"));
                            return;
                        }
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target == null) {
                            sendPlayerMessage(p, getlangString("UNKNOWN_PLAYER"));
                            return;
                        }
                        ItemStack item = target.getInventory().getItemInMainHand();
                        String defaultlore = getitemfile().getString("ENCHANTS.DEFAULT.LORE");
                        addEnchant(MythiccCore.get(), key, target, item, lore, Integer.parseInt(args[3]), defaultlore);
                    }
                }
            }
            if (args.length == 5) {
                if (args[0].equalsIgnoreCase("item")) {
                    String items = args[2].toUpperCase();
                    Player target = Bukkit.getPlayer(args[1]);
                    if (getitemfile().getString("ITEMS." + items) == null) {
                        sendPlayerMessage(p, getlangString("UNKNOWN_ITEM").replace("%id%", items));
                        return;
                    }
                    if (target == null) {
                        sendPlayerMessage(p, getlangString("UNKNOWN_PLAYER"));
                        return;
                    }
                    List<String> lore = getitemfile().getStringList("ITEMS." + items + ".LORE").stream().map(a -> a.replaceAll("%level%", String.format("%,d", Integer.parseInt(args[3])))).collect(Collectors.toList());
                    String name = Objects.requireNonNull(getitemfile().getString("ITEMS." + items + ".NAME")).replace("%level%", formatLevel(Integer.parseInt(args[3])));
                    Integer amount = Integer.parseInt(args[4]);
                    Material material = valueOf(getitemfile().getString("ITEMS." + items + ".MATERIAL"));
                    Integer level = Integer.parseInt(args[3]);
                    String key = getitemfile().getString("ITEMS." + items + ".TYPE");
                    String key2 = getitemfile().getString("ENCHANTS." + items + ".KEY");
                    if (key2 == null || key == null) {
                        return;
                    }
                    ItemStack itemStack = makeItem(MythiccCore.get(), key2, PersistentDataType.INTEGER, level, material, amount, true, true, true, name, lore);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    Objects.requireNonNull(itemMeta).getPersistentDataContainer().set(new NamespacedKey(MythiccCore.get(), key), PersistentDataType.STRING, items);
                    itemStack.setItemMeta(itemMeta);
                    target.getInventory().addItem(itemStack);
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("enchant")) {
                if (isInteger(args[3])) {
                    String name = args[2].toUpperCase();
                    String lore = getitemfile().getString("ENCHANTS." + name + ".NAME");
                    String key = getitemfile().getString("ENCHANTS." + name + ".KEY");
                    if (key == null || lore == null) {
                        sendConsoleMessage(c, getlangString("UNKNOWN_KEY"));
                        return;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        sendConsoleMessage(c, getlangString("UNKNOWN_PLAYER"));
                        return;
                    }
                    ItemStack item = target.getInventory().getItemInMainHand();
                    String defaultlore = getitemfile().getString("ENCHANTS.DEFAULT.LORE");
                    addEnchant(MythiccCore.get(), key, target, item, lore, Integer.parseInt(args[3]), defaultlore);
                }
            }
        }

        if (args.length == 5) {
            if (args[0].equalsIgnoreCase("item")) {
                String items = args[2].toUpperCase();
                Player target = Bukkit.getPlayer(args[1]);
                if (getitemfile().getString("ITEMS." + items) == null) {
                    sendConsoleMessage(c, getlangString("UNKNOWN_ITEM").replace("%id%", items));
                    return;
                }
                if (target == null) {
                    sendConsoleMessage(c, getlangString("UNKNOWN_PLAYER"));
                    return;
                }
                List<String> lore = getitemfile().getStringList("ITEMS." + items + ".LORE").stream().map(a -> a.replaceAll("%level%", String.format("%,d", Integer.parseInt(args[3])))).collect(Collectors.toList());
                String name = Objects.requireNonNull(getitemfile().getString("ITEMS." + items + ".NAME")).replace("%level%", formatLevel(Integer.parseInt(args[3])));
                Integer amount = Integer.parseInt(args[4]);
                Material material = valueOf(getitemfile().getString("ITEMS." + items + ".MATERIAL"));
                Integer level = Integer.parseInt(args[3]);
                String key = getitemfile().getString("ITEMS." + items + ".TYPE");
                String key2 = getitemfile().getString("ENCHANTS." + items + ".KEY");
                if (key2 == null || key == null) {
                    return;
                }
                ItemStack itemStack = makeItem(MythiccCore.get(), key2, PersistentDataType.INTEGER, level, material, amount, true, true, true, name, lore);
                ItemMeta itemMeta = itemStack.getItemMeta();
                Objects.requireNonNull(itemMeta).getPersistentDataContainer().set(new NamespacedKey(MythiccCore.get(), key), PersistentDataType.STRING, items);
                itemStack.setItemMeta(itemMeta);
                target.getInventory().addItem(itemStack);
            }
        }
    }
}

