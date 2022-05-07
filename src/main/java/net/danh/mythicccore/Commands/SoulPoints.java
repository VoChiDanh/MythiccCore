package net.danh.mythicccore.Commands;

import net.danh.mythicccore.MythiccCore;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static net.danh.mythicccore.Data.SoulPoints.*;
import static net.danh.mythicccore.Utils.Chat.colorize;
import static net.danh.mythicccore.Utils.Chat.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.getlangString;

public class SoulPoints extends CMDBase {

    public SoulPoints(MythiccCore core) {
        super(core, "soul");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 0) {
            sendPlayerMessage(p, getlangString("CHECK_SOUL_POINTS").replace("%soul%", String.valueOf(getSoulPoints(p))));
        }
        if (args.length == 3) {
            Player target = Bukkit.getPlayer(args[1]);
            int soul = Integer.parseInt(args[2]);
            if (target == null) {
                sendPlayerMessage(p, "&cNgười chơi không tồn tại");
                return;
            }
            if (soul < 0) {
                sendPlayerMessage(p, "&cCon số phải lớn hơn 0");
                return;
            }
            if (args[0].equalsIgnoreCase("add")) {
                addSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("remove")) {
                removeSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("set")) {
                setSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("add_max")) {
                addMaxSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("remove_max")) {
                removeMaxSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("set_max")) {
                setMaxSoulPoints(target, soul);
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                c.sendMessage(colorize("&cNgười chơi không tồn tại"));
                return;
            }
            c.sendMessage(colorize(getlangString("CHECK_SOUL_POINTS").replace("%soul%", String.valueOf(getSoulPoints(target)))));
        }
        if (args.length == 3) {
            Player target = Bukkit.getPlayer(args[1]);
            int soul = Integer.parseInt(args[2]);
            if (target == null) {
                c.sendMessage(colorize("&cNgười chơi không tồn tại"));
                return;
            }
            if (soul < 0) {
                c.sendMessage(colorize("&cCon số phải lớn hơn 0"));
                return;
            }
            if (args[0].equalsIgnoreCase("add")) {
                addSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("remove")) {
                removeSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("set")) {
                setSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("add_max")) {
                addMaxSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("remove_max")) {
                removeMaxSoulPoints(target, soul);
            }
            if (args[0].equalsIgnoreCase("set_max")) {
                setMaxSoulPoints(target, soul);
            }
        }
    }
}
