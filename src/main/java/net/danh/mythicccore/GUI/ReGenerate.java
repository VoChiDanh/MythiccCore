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

public class ReGenerate {

    public static Inventory RegenerateInventory(Player p) {
        Inventory regeninv = Bukkit.createInventory(p, 9, getguiString("GUI.REGENERATE.TITLE"));
        ItemStack button = new ItemStack(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.REGENERATE.BUTTON.MATERIAL"))), getguiInt("GUI.REGENERATE.BUTTON.AMOUNT"));
        ItemMeta meta = button.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(getguiString("GUI.REGENERATE.BUTTON.DISPLAY"));
        List<String> lore = getguiStringList("GUI.REGENERATE.BUTTON.LORE");
        meta.setLore(lore);
        button.setItemMeta(meta);
        regeninv.setItem(8, button);
        return regeninv;
    }

}
