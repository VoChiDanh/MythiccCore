package net.danh.mythicccore.Commands;

import net.danh.mythicccore.MythiccCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class CMDBase implements CommandExecutor {

    protected MythiccCore core;

    public CMDBase(MythiccCore core, String name) {
        this.core = core;
        PluginCommand pluginCommand = core.getCommand(name);
        Objects.requireNonNull(pluginCommand).setExecutor(this);
    }

    public abstract void execute(Player p, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            execute((Player) sender, args);
        }
        return true;
    }
}
