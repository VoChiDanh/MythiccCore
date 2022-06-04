package net.danh.mythicccore.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.danh.dcore.Utils.Items.makeItem;
import static net.danh.mythicccore.Data.Item.*;
import static net.danh.mythicccore.Utils.Resources.*;

public class Inventory implements Listener {

    @EventHandler
    public void onCloseInventory(@NotNull InventoryCloseEvent e) {
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
    public void OnInventoryClick(@NotNull InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
            ItemStack target = e.getCurrentItem();
            ItemStack enchant = e.getCursor();
            enchantItem(p, target, enchant, e);
        }
        if (e.getView().getTitle().equals(getguiString("GUI.UPGRADE.TITLE"))) {
            if (e.getCurrentItem() != null && Objects.equals(Objects.requireNonNull(e.getCurrentItem()).getType().toString(), getguiString("GUI.UPGRADE.DECORATE.MATERIAL"))) {
                e.setCancelled(true);
            }
            ItemStack tutorial = makeItem(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.TUTORIAL.MATERIAL"))), null, getguiInt("GUI.UPGRADE.TUTORIAL.AMOUNT"), true, true, true, getguiString("GUI.UPGRADE.TUTORIAL.DISPLAY"), getguiStringList("GUI.UPGRADE.TUTORIAL.LORE"));

            ItemStack button = makeItem(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.BUTTON.MATERIAL"))), null, getguiInt("GUI.UPGRADE.BUTTON.AMOUNT"), true, true, true, getguiString("GUI.UPGRADE.BUTTON.DISPLAY"), getguiStringList("GUI.UPGRADE.BUTTON.LORE"));

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
                    upgradeItem(p, e.getInventory(), weapon, upgradestone1, upgradestone2, upgradestone3, upgradestone4, upgradestone5, upgradestone6, upgradestone7, upgradestone8);
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
                        unLockItem(p, item1, e.getInventory(), 0);
                    }
                    if (item2 != null) {
                        unLockItem(p, item2, e.getInventory(), 1);
                    }
                    if (item3 != null) {
                        unLockItem(p, item3, e.getInventory(), 2);
                    }
                    if (item4 != null) {
                        unLockItem(p, item4, e.getInventory(), 3);
                    }
                    if (item5 != null) {
                        unLockItem(p, item5, e.getInventory(), 4);
                    }
                    if (item6 != null) {
                        unLockItem(p, item6, e.getInventory(), 5);
                    }
                    if (item7 != null) {
                        unLockItem(p, item7, e.getInventory(), 6);
                    }
                    if (item8 != null) {
                        unLockItem(p, item8, e.getInventory(), 7);
                    }
                }
            }
        }
    }
}
