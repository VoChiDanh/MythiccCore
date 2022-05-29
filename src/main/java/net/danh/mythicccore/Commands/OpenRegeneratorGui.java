package net.danh.mythicccore.Commands;

import net.danh.dcore.Commands.CMDBase;
import net.danh.mythicccore.GUI.ReGenerate;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.getlangString;
import static net.danh.mythicccore.Utils.Resources.getlangStringList;

public class OpenRegeneratorGui extends CMDBase {

    public OpenRegeneratorGui(MythiccCore core) {
        super(core, "openregeneratorgui");
    }

    @Override
    public void playerexecute(Player p, @NotNull String[] args) {
        if (args.length == 0) {
            if (p.hasPermission("openregeneratorgui")) {
                p.openInventory(ReGenerate.RegenerateInventory(p));
            } else {
                sendPlayerMessage(p, getlangStringList("NO_PERMISSION_GUI"));
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, @NotNull String[] args) {
        if (args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                sendConsoleMessage(c, getlangString("UNKNOWN_PLAYER"));
                return;
            }
            p.openInventory(ReGenerate.RegenerateInventory(p));
        }
    }
}
