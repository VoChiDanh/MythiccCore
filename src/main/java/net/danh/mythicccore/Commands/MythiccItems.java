package net.danh.mythicccore.Commands;

import net.danh.dcore.Commands.CMDBase;
import net.danh.mythicccore.MythiccCore;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static net.danh.dcore.Utils.Items.makeItem;
import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.mythicccore.Utils.Resources.getitemfile;
import static org.bukkit.Material.valueOf;

public class MythiccItems extends CMDBase {

    public MythiccItems(JavaPlugin core) {
        super(core, "MythiccItems");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("Mythicc.Admin")) {
            if (args.length == 2) {
                String items = args[0].toUpperCase();
                Player target = Bukkit.getPlayer(args[1]);
                if (getitemfile().getString("ITEMS." + items) == null) {
                    sendPlayerMessage(p, "&cVật phẩm không tồn tại");
                    return;
                }
                if (target == null) {
                    sendPlayerMessage(p, "&cNgười chơi không tồn tại");
                    return;
                }
                ItemStack itemStack = makeItem(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ITEMS." + items + ".TYPE")), PersistentDataType.INTEGER, getitemfile().getInt("POWER." + items), valueOf(getitemfile().getString("ITEMS." + items + ".MATERIAL")), getitemfile().getInt("ITEMS." + items + ".AMOUNT"), true, true, true, getitemfile().getString("ITEMS." + items + ".NAME"), getitemfile().getStringList("ITEMS." + items + ".LORE"));
                target.getInventory().addItem(itemStack);
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 2) {
            String items = args[0].toUpperCase();
            Player target = Bukkit.getPlayer(args[1]);
            if (getitemfile().getString("ITEMS." + items) == null) {
                sendConsoleMessage(c, "&cVật phẩm không tồn tại");
                return;
            }
            if (target == null) {
                sendConsoleMessage(c, "&cNgười chơi không tồn tại");
                return;
            }
            ItemStack itemStack = makeItem(MythiccCore.get(), Objects.requireNonNull(getitemfile().getString("ITEMS." + items + ".TYPE")), PersistentDataType.INTEGER, getitemfile().getInt("POWER." + items), valueOf(getitemfile().getString("ITEMS." + items + ".MATERIAL")), getitemfile().getInt("ITEMS." + items + ".AMOUNT"), true, true, true, getitemfile().getString("ITEMS." + items + ".NAME"), getitemfile().getStringList("ITEMS." + items + ".LORE"));
            target.getInventory().addItem(itemStack);
        }
    }
}

