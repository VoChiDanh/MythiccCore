package net.danh.mythicccore.Commands;

import net.danh.mythicccore.GUI.Upgrade;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.entity.Player;

public class OpenUpgradeGui extends CMDBase {

    public OpenUpgradeGui(MythiccCore core) {
        super(core, "upgradegui");
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args.length == 0) {
            p.openInventory(Upgrade.UpgradeGui(p));
        }
    }
}
