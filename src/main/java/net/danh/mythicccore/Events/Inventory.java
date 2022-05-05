package net.danh.mythicccore.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

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
                ItemStack itemStack = e.getInventory().getItem(1);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(2) != null) {
                ItemStack itemStack = e.getInventory().getItem(2);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(3) != null) {
                ItemStack itemStack = e.getInventory().getItem(3);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(4) != null) {
                ItemStack itemStack = e.getInventory().getItem(4);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(5) != null) {
                ItemStack itemStack = e.getInventory().getItem(5);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(6) != null) {
                ItemStack itemStack = e.getInventory().getItem(6);
                p.getInventory().addItem(itemStack);
            }
            if (e.getInventory().getItem(7) != null) {
                ItemStack itemStack = e.getInventory().getItem(7);
                p.getInventory().addItem(itemStack);
            }
        }
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
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
                            /* mmoitem.setData(ItemStats.ATTACK_DAMAGE, new DoubleData(Double.parseDouble(mmoitem.getData(ItemStats.ATTACK_DAMAGE).toString()) + 0.5));
                             */
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
