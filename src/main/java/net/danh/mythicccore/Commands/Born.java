package net.danh.mythicccore.Commands;

import net.danh.dcore.Commands.CMDBase;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static net.danh.dcore.Random.Number.isInteger;
import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.getlangString;

public class Born extends CMDBase {

    public Born(JavaPlugin core) {
        super(core, "namsinh");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 1) {
            if (isInteger(args[0]) && Integer.parseInt(args[0]) > 0) {
                if (net.danh.mythicccore.Data.Born.YearIsNull(p)) {
                    net.danh.mythicccore.Data.Born.setYear(p, Integer.parseInt(args[0]));
                    sendPlayerMessage(p, getlangString("CHOOSE_YEAR")
                            .replaceAll("%year%", String.valueOf(net.danh.mythicccore.Data.Born.getYear(p)))
                            .replaceAll("%age%", String.valueOf(net.danh.mythicccore.Data.Born.getAge(p))));
                } else {
                    sendPlayerMessage(p, getlangString("ALREADY_HAVE_YEAR"));
                }
            } else {
                sendPlayerMessage(p, getlangString("IS_NOT_NUMBER"));
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);
            if (p == null) {
                sendConsoleMessage(c, getlangString("UNKNOWN_PLAYER"));
                return;
            }
            if (isInteger(args[0]) && Integer.parseInt(args[0]) > 0) {
                if (net.danh.mythicccore.Data.Born.YearIsNull(p)) {
                    net.danh.mythicccore.Data.Born.setYear(p, Integer.parseInt(args[0]));
                    sendPlayerMessage(p, getlangString("CHOOSE_YEAR")
                            .replaceAll("%year%", String.valueOf(net.danh.mythicccore.Data.Born.getYear(p)))
                            .replaceAll("%age%", String.valueOf(net.danh.mythicccore.Data.Born.getAge(p))));
                } else {
                    sendConsoleMessage(c, getlangString("ALREADY_HAVE_YEAR"));
                }
            } else {
                sendConsoleMessage(c, getlangString("IS_NOT_NUMBER"));
            }
        }
    }
}
