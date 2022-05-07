package net.danh.mythicccore.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.stat.data.DoubleData;
import net.danh.mythicccore.MythiccCore;
import net.danh.mythicccore.Utils.Chat;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

import static net.danh.mythicccore.Utils.Chat.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.*;
import static net.danh.mythicccore.Utils.Utils.getRandomInt;

public class Inventory implements Listener {

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getView().getTitle().equals(getguiString("GUI.REGENERATE.TITLE"))) {
            if (e.getInventory().getItem(0) != null) {
                ItemStack itemStack = e.getInventory().getItem(0);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(1) != null) {
                p.getInventory().addItem(e.getInventory().getItem(1));
            }
            if (e.getInventory().getItem(2) != null) {
                p.getInventory().addItem(e.getInventory().getItem(2));
            }
            if (e.getInventory().getItem(3) != null) {
                p.getInventory().addItem(e.getInventory().getItem(3));
            }
            if (e.getInventory().getItem(4) != null) {
                p.getInventory().addItem(e.getInventory().getItem(4));
            }
            if (e.getInventory().getItem(5) != null) {
                p.getInventory().addItem(e.getInventory().getItem(5));
            }
            if (e.getInventory().getItem(6) != null) {
                p.getInventory().addItem(e.getInventory().getItem(6));
            }
            if (e.getInventory().getItem(7) != null) {
                p.getInventory().addItem(e.getInventory().getItem(7));
            }
        }
        if (e.getView().getTitle().equals(getguiString("GUI.UPGRADE.TITLE"))) {
            ItemStack weapon = e.getInventory().getItem(22);
            ItemStack upgradestone1 = e.getInventory().getItem(12);
            ItemStack upgradestone2 = e.getInventory().getItem(13);
            ItemStack upgradestone3 = e.getInventory().getItem(14);
            ItemStack upgradestone4 = e.getInventory().getItem(21);
            ItemStack upgradestone5 = e.getInventory().getItem(23);
            ItemStack upgradestone6 = e.getInventory().getItem(30);
            ItemStack upgradestone7 = e.getInventory().getItem(31);
            ItemStack upgradestone8 = e.getInventory().getItem(32);
            if (weapon != null) {
                p.getInventory().addItem(weapon);
            }
            if (upgradestone1 != null) {
                p.getInventory().addItem(upgradestone1);
            }
            if (upgradestone2 != null) {
                p.getInventory().addItem(upgradestone2);
            }
            if (upgradestone3 != null) {
                p.getInventory().addItem(upgradestone3);
            }
            if (upgradestone4 != null) {
                p.getInventory().addItem(upgradestone4);
            }
            if (upgradestone5 != null) {
                p.getInventory().addItem(upgradestone5);
            }
            if (upgradestone6 != null) {
                p.getInventory().addItem(upgradestone6);
            }
            if (upgradestone7 != null) {
                p.getInventory().addItem(upgradestone7);
            }
            if (upgradestone8 != null) {
                p.getInventory().addItem(upgradestone8);
            }
        }
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(getguiString("GUI.UPGRADE.TITLE"))) {
            if (e.getCurrentItem() != null && Objects.equals(Objects.requireNonNull(e.getCurrentItem()).getType().toString(), getguiString("GUI.UPGRADE.DECORATE.MATERIAL"))) {
                e.setCancelled(true);
            }

            ItemStack tutorial = new ItemStack(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.TUTORIAL.MATERIAL"))), getguiInt("GUI.UPGRADE.TUTORIAL.AMOUNT"));
            ItemMeta tutorialmeta = tutorial.getItemMeta();
            Objects.requireNonNull(tutorialmeta).setDisplayName(getguiString("GUI.UPGRADE.TUTORIAL.DISPLAY"));
            List<String> tutoriallore = getguiStringList("GUI.UPGRADE.TUTORIAL.LORE");
            tutorialmeta.setLore(tutoriallore);
            tutorial.setItemMeta(tutorialmeta);

            ItemStack button = new ItemStack(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.BUTTON.MATERIAL"))), getguiInt("GUI.UPGRADE.BUTTON.AMOUNT"));
            ItemMeta meta = button.getItemMeta();
            Objects.requireNonNull(meta).setDisplayName(getguiString("GUI.UPGRADE.BUTTON.DISPLAY"));
            List<String> lore = getguiStringList("GUI.UPGRADE.BUTTON.LORE");
            meta.setLore(lore);
            button.setItemMeta(meta);
            if (e.getCurrentItem() != null && e.getSlot() == getguiInt("GUI.UPGRADE.TUTORIAL.SLOT") && e.getCurrentItem().isSimilar(tutorial)) {
                e.setCancelled(true);
            }
            if (e.getCurrentItem() != null && e.getSlot() == getguiInt("GUI.UPGRADE.BUTTON.SLOT") && e.getCurrentItem().isSimilar(button)) {
                e.setCancelled(true);
                if (e.getClick() == ClickType.LEFT) {
                    ItemStack weapon = e.getInventory().getItem(22);
                    ItemStack upgradestone1 = e.getInventory().getItem(12);
                    ItemStack upgradestone2 = e.getInventory().getItem(13);
                    ItemStack upgradestone3 = e.getInventory().getItem(14);
                    ItemStack upgradestone4 = e.getInventory().getItem(21);
                    ItemStack upgradestone5 = e.getInventory().getItem(23);
                    ItemStack upgradestone6 = e.getInventory().getItem(30);
                    ItemStack upgradestone7 = e.getInventory().getItem(31);
                    ItemStack upgradestone8 = e.getInventory().getItem(32);
                    if (weapon != null
                            && upgradestone1 != null
                            && upgradestone2 != null
                            && upgradestone3 != null
                            && upgradestone4 != null
                            && upgradestone5 != null
                            && upgradestone6 != null
                            && upgradestone7 != null
                            && upgradestone8 != null) {
                        if (weapon.getAmount() != 1
                                || upgradestone1.getAmount() != 1
                                || upgradestone2.getAmount() != 1
                                || upgradestone3.getAmount() != 1
                                || upgradestone4.getAmount() != 1
                                || upgradestone5.getAmount() != 1
                                || upgradestone6.getAmount() != 1
                                || upgradestone7.getAmount() != 1
                                || upgradestone8.getAmount() != 1) {
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
                            if (nbtupgradestone1.hasType()
                                    && nbtupgradestone2.hasType()
                                    && nbtupgradestone3.hasType()
                                    && nbtupgradestone4.hasType()
                                    && nbtupgradestone5.hasType()
                                    && nbtupgradestone6.hasType()
                                    && nbtupgradestone7.hasType()
                                    && nbtupgradestone8.hasType()) {
                                if (Objects.equals(nbtupgradestone1.getType(), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE_MATERIAL"))
                                        && Objects.equals(nbtupgradestone2.getType(), nbtupgradestone1.getType())
                                        && Objects.equals(nbtupgradestone3.getType(), nbtupgradestone1.getType())
                                        && Objects.equals(nbtupgradestone4.getType(), nbtupgradestone1.getType())
                                        && Objects.equals(nbtupgradestone5.getType(), nbtupgradestone1.getType())
                                        && Objects.equals(nbtupgradestone6.getType(), nbtupgradestone1.getType())
                                        && Objects.equals(nbtupgradestone7.getType(), nbtupgradestone1.getType())
                                        && Objects.equals(nbtupgradestone8.getType(), nbtupgradestone1.getType())) {
                                    if (Objects.equals(nbtupgradestone1.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone2.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone3.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone4.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone5.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone6.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone7.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))
                                            && Objects.equals(nbtupgradestone8.getString("MMOITEMS_ITEM_ID"), getupgradeString(nbtweapon.getString("MMOITEMS_ITEM_ID") + ".UPGRADE_STONE"))) {
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
                                        Objects.requireNonNull(e.getInventory().getItem(22)).setItemMeta(result.toItem().getItemMeta());
                                        e.getInventory().setItem(12, null);
                                        e.getInventory().setItem(13, null);
                                        e.getInventory().setItem(14, null);
                                        e.getInventory().setItem(21, null);
                                        e.getInventory().setItem(23, null);
                                        e.getInventory().setItem(30, null);
                                        e.getInventory().setItem(31, null);
                                        e.getInventory().setItem(32, null);
                                        MythiccCore.get().getAdvancementManager().sendToast(new ItemStack(Material.EXPERIENCE_BOTTLE), p.getUniqueId(), Chat.colorize(getlangString("UPGRADE_SUCCESSFUL")), "Custom");
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
                }
            }
        }
        if (e.getView().getTitle().equals(getguiString("GUI.REGENERATE.TITLE"))) {
            if (e.getSlot() == 8) {
                e.setCancelled(true);
                if (e.getClick() == ClickType.LEFT) {
                    ItemStack item1 = e.getInventory().getItem(0);
                    ItemStack item2 = e.getInventory().getItem(1);
                    ItemStack item3 = e.getInventory().getItem(2);
                    ItemStack item4 = e.getInventory().getItem(3);
                    ItemStack item5 = e.getInventory().getItem(4);
                    ItemStack item6 = e.getInventory().getItem(5);
                    ItemStack item7 = e.getInventory().getItem(6);
                    ItemStack item8 = e.getInventory().getItem(7);
                    if (item1 != null) {
                        NBTItem nbt = NBTItem.get(item1);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(0, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item2 != null) {
                        NBTItem nbt = NBTItem.get(item2);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(1, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item3 != null) {
                        NBTItem nbt = NBTItem.get(item3);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(2, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item4 != null) {
                        NBTItem nbt = NBTItem.get(item4);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(3, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item5 != null) {
                        NBTItem nbt = NBTItem.get(item5);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(4, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item6 != null) {
                        NBTItem nbt = NBTItem.get(item6);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(5, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item7 != null) {
                        NBTItem nbt = NBTItem.get(item7);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(6, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                    if (item8 != null) {
                        NBTItem nbt = NBTItem.get(item8);
                        if (nbt.hasType() && Objects.equals(nbt.getType(), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".MATERIAL_TYPE"))) {
                            if (Objects.requireNonNull(e.getInventory().getItem(0)).getAmount() != 1) {
                                sendPlayerMessage(p, getlangString("MAX"));
                                return;
                            }
                            int number = getRandomInt(1, getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX"));
                            MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".TYPE")), getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number));
                            if (mmoitem == null) {
                                sendPlayerMessage(p, Objects.requireNonNull(getlanguagefile().getString("UNKNOWN_ITEM")).replace("%id%", Objects.requireNonNull(getconfigfile().getString(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.ID" + number))));
                                return;
                            }
                            ItemStack item = Objects.requireNonNull(mmoitem).newBuilder().build();
                            e.getInventory().setItem(7, item);
                            if (number == getconfigInt(nbt.getString("MMOITEMS_ITEM_ID") + ".LIST.MAX")) {
                                sendPlayerMessage(p, getlangString("OPEN_MAX").replace("%id%", Objects.requireNonNull(Objects.requireNonNull(item).getItemMeta()).getDisplayName()));
                                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 2, 1);
                            }
                        } else {
                            sendPlayerMessage(p, getlanguagefile().getString("CAN_NOT_REGENERATE"));
                        }
                    }
                }
            }
        }
    }
}
