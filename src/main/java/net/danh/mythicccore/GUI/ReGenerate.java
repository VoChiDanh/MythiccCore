package net.danh.mythicccore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.danh.dcore.Utils.Items.makeItem;
import static net.danh.mythicccore.Utils.Resources.*;

public class ReGenerate {

    @NotNull
    public static Inventory RegenerateInventory(Player p) {
        Inventory regeninv = Bukkit.createInventory(p, 9, getguiString("GUI.REGENERATE.TITLE"));
        ItemStack button = makeItem(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.REGENERATE.BUTTON.MATERIAL"))), getguiInt("GUI.REGENERATE.BUTTON.AMOUNT"), true, true, true, getguiString("GUI.REGENERATE.BUTTON.DISPLAY"), getguiStringList("GUI.REGENERATE.BUTTON.LORE"));
        regeninv.setItem(8, button);
        return regeninv;
    }

}
