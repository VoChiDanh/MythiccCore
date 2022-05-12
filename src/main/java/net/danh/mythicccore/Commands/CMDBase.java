package net.danh.mythicccore.Commands;

import net.danh.mythicccore.MythiccCore;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class CMDBase implements CommandExecutor {

    protected MythiccCore core;

    public CMDBase(@NotNull MythiccCore core, String name) {
        this.core = core;
        PluginCommand pluginCommand = core.getCommand(name);
        Objects.requireNonNull(pluginCommand).setExecutor(this);
    }

    public abstract void playerexecute(Player p, String[] args);

    public abstract void consoleexecute(ConsoleCommandSender c, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            playerexecute((Player) sender, args);
        }
        if (sender instanceof ConsoleCommandSender) {
            consoleexecute((ConsoleCommandSender) sender, args);
        }
        return true;
    }
}
