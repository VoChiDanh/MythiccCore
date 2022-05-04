package net.danh.mythicccore.Commands;

import net.danh.mythicccore.GUI.ReGenerate;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;

public class OpenRegeneratorGui extends CMDBase {
    public OpenRegeneratorGui(MythiccCore core) {
        super(core, "openregeneratorgui");
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length == 0) {
            p.openInventory(ReGenerate.RegenerateInventory(p));
        }
    }
}
