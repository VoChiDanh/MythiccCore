package net.danh.mythicccore.Data;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.stat.data.DoubleData;
import net.danh.dcore.List.Contain;
import net.danh.dcore.Random.RString;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

import static net.danh.dcore.Enchant.Lore.*;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.*;

public class Item {

    public static void enchantItem(Player p, ItemStack target, ItemStack enchant, InventoryClickEvent e) {
        if (target == null) {
            return;
        }
        if (enchant == null) {
            return;
        }
        ItemMeta targetmeta = target.getItemMeta();
        ItemMeta enchantmeta = enchant.getItemMeta();
        if (targetmeta == null) {
            return;
        }
        if (enchantmeta == null) {
            return;
        }
        if (enchant.hasItemMeta() && enchantmeta.getPersistentDataContainer().has(new NamespacedKey(MythiccCore.get(), "ENCHANTMENT_BOOK"), PersistentDataType.STRING)) {
            if (targetmeta.hasLore() && target.hasItemMeta()) {
                String name = enchantmeta.getPersistentDataContainer().get(new NamespacedKey(MythiccCore.get(), "ENCHANTMENT_BOOK"), PersistentDataType.STRING);
                if (name == null) {
                    e.setCancelled(true);
                    return;
                }
                String lore = getitemfile().getString("ENCHANTS." + name + ".NAME");
                String key = getitemfile().getString("ENCHANTS." + name + ".KEY");
                String defaultlore = getitemfile().getString("ENCHANTS.DEFAULT.LORE");
                int limited = getitemfile().getInt("ITEMS." + name + ".LIMITED");
                if (key == null) {
                    e.setCancelled(true);
                    return;
                }
                Integer level = enchantmeta.getPersistentDataContainer().getOrDefault(new NamespacedKey(MythiccCore.get(), key), PersistentDataType.INTEGER, 1);
                if (getEnchantLevel(MythiccCore.get(), key, target) <= limited && level <= limited) {
                    if (enchantmeta.getPersistentDataContainer().getOrDefault(new NamespacedKey(MythiccCore.get(), key), PersistentDataType.INTEGER, 1) > getEnchantLevel(MythiccCore.get(), key, target)) {
                        addEnchant(MythiccCore.get(), key, p, target, lore, level, defaultlore);
                        if (!isFull()) {
                            p.setItemOnCursor(null);
                        } else {
                            e.setCancelled(true);
                            sendPlayerMessage(p, getlangString("FULL_SLOT"));
                        }
                    } else {
                        e.setCancelled(true);
                        sendPlayerMessage(p, getlangString("LOWER_ENCHANT"));
                    }
                } else {
                    sendPlayerMessage(p, getlangString("LIMITED_ENCHANT").replaceAll("%enchant%", key));
                }
            }
        }
    }

    public static void upgradeItem(Player p, Inventory inventory, ItemStack weapon, ItemStack upgradestone1, ItemStack upgradestone2, ItemStack upgradestone3, ItemStack upgradestone4, ItemStack upgradestone5, ItemStack upgradestone6, ItemStack upgradestone7, ItemStack upgradestone8, Integer cost) {
        if (MythiccCore.getEconomy().getBalance(p) >= cost) {
            if (weapon != null && upgradestone1 != null && upgradestone2 != null && upgradestone3 != null && upgradestone4 != null && upgradestone5 != null && upgradestone6 != null && upgradestone7 != null && upgradestone8 != null) {
                if (weapon.getAmount() != 1 || upgradestone1.getAmount() != 1 || upgradestone2.getAmount() != 1 || upgradestone3.getAmount() != 1 || upgradestone4.getAmount() != 1 || upgradestone5.getAmount() != 1 || upgradestone6.getAmount() != 1 || upgradestone7.getAmount() != 1 || upgradestone8.getAmount() != 1) {
                    sendPlayerMessage(p, getlangString("MAX"));
                    return;
                }
                NBTItem nbtweapon = NBTItem.get(weapon);
                NBTItem nbtupgradestone1 = NBTItem.get(upgradestone1);
                NBTItem nbtupgradestone2 = NBTItem.get(upgradestone2);
                NBTItem nbtupgradestone3 = NBTItem.get(upgradestone3);
                NBTItem nbtupgradestone4 = NBTItem.get(upgradestone4);
                NBTItem nbtupgradestone5 = NBTItem.get(upgradestone5);
                NBTItem nbtupgradestone6 = NBTItem.get(upgradestone6);
                NBTItem nbtupgradestone7 = NBTItem.get(upgradestone7);
                NBTItem nbtupgradestone8 = NBTItem.get(upgradestone8);
                if (nbtweapon.hasType() && Objects.equals(nbtweapon.getType(), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".WEAPON_MATERIAL"))) {
                    if (Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone2.getString("MMOITEMS_ITEM_ID")) && Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone3.getString("MMOITEMS_ITEM_ID")) && Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone4.getString("MMOITEMS_ITEM_ID")) && Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone5.getString("MMOITEMS_ITEM_ID")) && Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone6.getString("MMOITEMS_ITEM_ID")) && Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone7.getString("MMOITEMS_ITEM_ID")) && Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), nbtupgradestone8.getString("MMOITEMS_ITEM_ID"))) {
                        if (Objects.equals(nbtupgradestone1.getType(), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE_MATERIAL")) && Objects.equals(nbtupgradestone2.getType(), nbtupgradestone1.getType()) && Objects.equals(nbtupgradestone3.getType(), nbtupgradestone1.getType()) && Objects.equals(nbtupgradestone4.getType(), nbtupgradestone1.getType()) && Objects.equals(nbtupgradestone5.getType(), nbtupgradestone1.getType()) && Objects.equals(nbtupgradestone6.getType(), nbtupgradestone1.getType()) && Objects.equals(nbtupgradestone7.getType(), nbtupgradestone1.getType()) && Objects.equals(nbtupgradestone8.getType(), nbtupgradestone1.getType())) {
                            if (Contain.inList(getupgradefile().getStringList(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"), nbtupgradestone1.getString("MMOITEMS_ITEM_ID"))) {
                                MMOItem mmoitem = new LiveMMOItem(nbtweapon);
                                if (Objects.equals(nbtweapon.getType(), "WAND")) {
                                    if (mmoitem.hasData(ItemStats.ATTACK_DAMAGE)) {
                                        mmoitem.setData(ItemStats.ATTACK_DAMAGE, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.ATTACK_DAMAGE).toString()) + 0.5));
                                    }
                                    if (mmoitem.hasData(ItemStats.PVP_DAMAGE)) {
                                        mmoitem.setData(ItemStats.PVP_DAMAGE, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.PVE_DAMAGE).toString()) + 0.3));
                                    }
                                    if (mmoitem.hasData(ItemStats.PVE_DAMAGE)) {
                                        mmoitem.setData(ItemStats.PVE_DAMAGE, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.PVE_DAMAGE).toString()) + 0.2));
                                    }
                                    if (mmoitem.hasData(ItemStats.MANA_COST)) {
                                        mmoitem.setData(ItemStats.MANA_COST, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.MANA_COST).toString()) + 0.1));
                                    }
                                    if (mmoitem.hasData(ItemStats.MAX_DURABILITY)) {
                                        mmoitem.setData(ItemStats.MAX_DURABILITY, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.CUSTOM_DURABILITY).toString()) + 100));
                                    }
                                }
                                if (Objects.equals(nbtweapon.getType(), "ARMOR")) {
                                    if (mmoitem.hasData(ItemStats.ARMOR)) {
                                        mmoitem.setData(ItemStats.ARMOR, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.ARMOR).toString()) + 0.5));
                                    }
                                    if (mmoitem.hasData(ItemStats.MAX_HEALTH)) {
                                        mmoitem.setData(ItemStats.MAX_HEALTH, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.MAX_HEALTH).toString()) + 0.3));
                                    }
                                    if (mmoitem.hasData(ItemStats.BLOCK_POWER)) {
                                        mmoitem.setData(ItemStats.BLOCK_POWER, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.BLOCK_POWER).toString()) + 0.2));
                                    }
                                    if (mmoitem.hasData(ItemStats.RESTORE_HEALTH)) {
                                        mmoitem.setData(ItemStats.RESTORE_HEALTH, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.RESTORE_HEALTH).toString()) + 0.1));
                                    }
                                    if (mmoitem.hasData(ItemStats.RESTORE_MANA)) {
                                        mmoitem.setData(ItemStats.RESTORE_MANA, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.RESTORE_MANA).toString()) + 0.1));
                                    }
                                    if (mmoitem.hasData(ItemStats.MAX_DURABILITY)) {
                                        mmoitem.setData(ItemStats.MAX_DURABILITY, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.CUSTOM_DURABILITY).toString()) + 100));
                                    }
                                }
                                NBTItem result = mmoitem.newBuilder().buildNBT();
                                Objects.requireNonNull(inventory.getItem(22)).setItemMeta(result.toItem().getItemMeta());
                                inventory.setItem(12, null);
                                inventory.setItem(13, null);
                                inventory.setItem(14, null);
                                inventory.setItem(21, null);
                                inventory.setItem(23, null);
                                inventory.setItem(30, null);
                                inventory.setItem(31, null);
                                inventory.setItem(32, null);
                                MythiccCore.getEconomy().withdrawPlayer(p, cost);
                            } else {
                                sendPlayerMessage(p, getlangString("CAN_NOT_UPGRADE_WITH_THIS_STONE"));
                            }
                        } else {
                            sendPlayerMessage(p, getlangString("NOT_SAME_UPGRADE_STONE"));
                        }
                    } else {
                        sendPlayerMessage(p, getlangString("NOT_CORRECT_UPGRADE_STONE"));
                    }
                } else {
                    sendPlayerMessage(p, getlangString("CAN_NOT_UPGRADE"));
                }
            } else {
                sendPlayerMessage(p, getlangString("NEED_UPGRADE_STONE"));
            }
        } else {
            sendPlayerMessage(p, getlangString("NOT_ENOUGH_COST").replaceAll("%cost%", String.format("%,d", cost)));
        }
    }

    public static void unLockItem(Player p, ItemStack itemStack, Inventory inv, Integer slot) {
        NBTItem nbt = NBTItem.get(itemStack);
        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
            if (Objects.requireNonNull(inv.getItem(slot)).getAmount() != 1) {
                sendPlayerMessage(p, getlangString("MAX"));
                return;
            }
            String id = RString.getRandomString(getconfigfile().getStringList(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST"));
            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), id);
            if (mmoitem == null) {
                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", id));
                return;
            }
            ItemStack item = mmoitem.newBuilder().build();
            inv.setItem(slot, item);
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
        } else {
            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
        }
    }
}
