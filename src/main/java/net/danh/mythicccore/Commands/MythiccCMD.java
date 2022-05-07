package net.danh.mythicccore.Commands;

import net.danh.mythicccore.MythiccCore;
import net.danh.mythicccore.Utils.Chat;
import net.danh.mythicccore.Utils.Resources;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static net.danh.mythicccore.Utils.Chat.sendPlayerMessage;

public class MythiccCMD extends CMDBase {

    public MythiccCMD(MythiccCore core) {
        super(core, "MythiccCore");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 1) {
            if (p.hasPermission("MythiccCore.admin")) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Resources.reloadfiles();
                    sendPlayerMessage(p, "&aĐã tải lại files");
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                Resources.reloadfiles();
                c.sendMessage(Chat.colorize("&aĐả tải lại files"));
            }
        }
    }
}
