package net.danh.mythicccore.Commands;

import net.danh.mythicccore.GUI.ReGenerate;
import net.danh.mythicccore.GUI.Upgrade;
import net.danh.mythicccore.MythiccCore;
import net.danh.mythicccore.Utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static net.danh.mythicccore.Utils.Chat.sendPlayerMessage;

public class OpenUpgradeGui extends CMDBase {

    public OpenUpgradeGui(MythiccCore core) {
        super(core, "upgradegui");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 0) {
            if (p.hasPermission("upgradegui")) {
                p.openInventory(Upgrade.UpgradeGui(p));
            } else {
                sendPlayerMessage(p, "&cBạn không có quyền mở gui này bằng lệnh, hãy đến gặp NPC để có thể mở", "&7", "&6Để có thể dùng lệnh này, bạn phải mua quyền tại shop quyền lợi");
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                c.sendMessage(Chat.colorize("&cNgười chơi không tồn tại!"));
                return;
            }
            p.openInventory(ReGenerate.RegenerateInventory(p));
        }
    }
}
