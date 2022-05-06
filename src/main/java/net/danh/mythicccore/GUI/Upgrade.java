package net.danh.mythicccore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

import static net.danh.mythicccore.Utils.Resources.*;

public class Upgrade {

    public static Inventory UpgradeGui(Player p) {
        Inventory upgrade = Bukkit.createInventory(p, 45, getguiString("GUI.UPGRADE.TITLE"));

        ItemStack decorate = new ItemStack(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.DECORATE.MATERIAL"))), getguiInt("GUI.UPGRADE.DECORATE.AMOUNT"));
        ItemMeta decoratemeta = decorate.getItemMeta();
        Objects.requireNonNull(decoratemeta).setDisplayName(getguiString("GUI.UPGRADE.DECORATE.DISPLAY"));
        decorate.setItemMeta(decoratemeta);

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

        upgrade.setItem(getguiInt("GUI.UPGRADE.BUTTON.SLOT"), button);
        upgrade.setItem(getguiInt("GUI.UPGRADE.TUTORIAL.SLOT"), tutorial);
        List<Integer> slot = getguifile().getIntegerList("GUI.UPGRADE.DECORATE.SLOT");
        for (Integer slots : slot) {
            upgrade.setItem(slots, decorate);
        }
        return upgrade;
    }
}
