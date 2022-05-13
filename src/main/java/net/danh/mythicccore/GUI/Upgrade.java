package net.danh.mythicccore.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static net.danh.dcore.Utils.Items.makeItem;
import static net.danh.mythicccore.Utils.Resources.*;

public class Upgrade {

    @NotNull
    public static Inventory UpgradeGui(Player p) {
        Inventory upgrade = Bukkit.createInventory(p, 45, getguiString("GUI.UPGRADE.TITLE"));

        ItemStack decorate = makeItem(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.DECORATE.MATERIAL"))), getguiInt("GUI.UPGRADE.DECORATE.AMOUNT"), false, true, getguiString("GUI.UPGRADE.DECORATE.DISPLAY"), null);

        ItemStack tutorial = makeItem(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.TUTORIAL.MATERIAL"))), getguiInt("GUI.UPGRADE.TUTORIAL.AMOUNT"), true, true, getguiString("GUI.UPGRADE.TUTORIAL.DISPLAY"), getguiStringList("GUI.UPGRADE.TUTORIAL.LORE"));

        ItemStack button = makeItem(Objects.requireNonNull(Material.getMaterial(getguiString("GUI.UPGRADE.BUTTON.MATERIAL"))), getguiInt("GUI.UPGRADE.BUTTON.AMOUNT"), true, true, getguiString("GUI.UPGRADE.BUTTON.DISPLAY"), getguiStringList("GUI.UPGRADE.BUTTON.LORE"));

        upgrade.setItem(getguiInt("GUI.UPGRADE.BUTTON.SLOT"), button);
        upgrade.setItem(getguiInt("GUI.UPGRADE.TUTORIAL.SLOT"), tutorial);
        List<Integer> slot = getguifile().getIntegerList("GUI.UPGRADE.DECORATE.SLOT");
        for (Integer slots : slot) {
            upgrade.setItem(slots, decorate);
        }
        return upgrade;
    }
}
