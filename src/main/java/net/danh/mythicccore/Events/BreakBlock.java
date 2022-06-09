package net.danh.mythicccore.Events;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.danh.dcore.Random.RString;
import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Items;
import net.danh.dcore.Utils.Progress;
import net.danh.mythicccore.MythiccCore;
import net.danh.mythicccore.Utils.Resources;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static net.danh.dcore.Enchant.Lore.getEnchantLevel;
import static net.danh.dcore.Utils.Player.sendPlayerMessageType;
import static net.danh.mythicccore.Utils.Resources.*;

public class BreakBlock implements Listener {

    public static HashMap<Location, Integer> blocks = new HashMap<>();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (e.isCancelled()) {
            if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(MythiccCore.get(), "TOOLS"), PersistentDataType.STRING)) {
                ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                int toolpower = getEnchantLevel(MythiccCore.get(), "POWER".toUpperCase(), p.getInventory().getItemInMainHand());
                if (toolpower == 0 || meta == null) {
                    sendPlayerMessageType(p, null, getlangString("UNKNOWN_TOOLS"));
                    e.setCancelled(true);
                    return;
                }
                String tool = meta.getPersistentDataContainer().get(new NamespacedKey(MythiccCore.get(), "TOOLS"), PersistentDataType.STRING);
                if (tool == null || meta.getLore() == null) {
                    sendPlayerMessageType(p, null, getlangString("UNKNOWN_TOOLS"));
                    e.setCancelled(true);
                    return;
                }
                for (String block : Resources.getsettingfile().getStringList("BLOCKS")) {
                    if (e.getBlock().getType().equals(Material.getMaterial(block))) {
                        int blockpower = Resources.getsettingfile().getInt("MINE." + e.getBlock().getType() + ".POWER");
                        Material blocktype = e.getBlock().getType();
                        Location location = e.getBlock().getLocation();
                        int count = toolpower - blockpower;
                        if (count < 0) {
                            sendPlayerMessageType(p, null, getlangString("LOW_POWER"));
                            e.setCancelled(true);
                            return;
                        }
                        String scount = String.valueOf(count);
                        if (blocks.containsKey(location)) {
                            int i = blocks.get(location);
                            int o = 10 - i;
                            e.setCancelled(true);
                            if (i <= 10 && i > 0) {
                                blocks.put(location, i - 1);
                                Objects.requireNonNull(meta).getPersistentDataContainer().set(new NamespacedKey(MythiccCore.get(), "POWER".toUpperCase()), PersistentDataType.INTEGER, count);
                                List<String> lore = meta.getLore();
                                lore.clear();
                                lore = getitemfile().getStringList("ITEMS." + tool.toUpperCase() + ".LORE").stream().map(a -> a.replaceAll("%level%", String.format("%,d", Integer.parseInt(scount)))).collect(Collectors.toList());
                                meta.setLore(Items.Lore(lore));
                                p.getInventory().getItemInMainHand().setItemMeta(meta);
                                p.sendTitle(Progress.getProgressBar(o, 10, 50, '|', "&a", "&7"), Chat.colorize("&e" + Progress.getPercentages(o, 10) + "%"), 10, 10, 10);
                            } else {
                                blocks.put(location, 0);
                                p.sendTitle(Progress.getProgressBar(10, 10, 50, '|', "&a", "&7"), Chat.colorize("&e" + Progress.getPercentages(10, 10) + "%"), 10, 10, 10);
                                e.getBlock().setType(Material.AIR);
                                MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(getsettingfile().getString("MINE." + blocktype + ".DROP.ID")), RString.getRandomString(getsettingfile().getStringList("MINE." + blocktype + ".DROP.TYPE")));
                                if (mmoitem != null) {
                                    ItemStack item = mmoitem.newBuilder().build();
                                    if (item != null) {
                                        e.getBlock().breakNaturally();
                                        p.getWorld().dropItem(e.getBlock().getLocation(), item);
                                        e.setDropItems(false);
                                        e.setCancelled(true);
                                    }
                                }
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        e.getBlock().setType(blocktype);
                                    }
                                }.runTaskTimer(MythiccCore.get(), 120 * 20L, 120 * 20L);
                            }
                        } else {
                            blocks.put(location, 9);
                            Objects.requireNonNull(meta).getPersistentDataContainer().set(new NamespacedKey(MythiccCore.get(), "POWER".toUpperCase()), PersistentDataType.INTEGER, count);
                            List<String> lore = meta.getLore();
                            lore.clear();
                            lore = getitemfile().getStringList("ITEMS." + tool.toUpperCase() + ".LORE").stream().map(a -> a.replaceAll("%level%", String.format("%,d", Integer.parseInt(scount)))).collect(Collectors.toList());
                            meta.setLore(Items.Lore(lore));
                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                            p.sendTitle(Progress.getProgressBar(0, 10, 50, '|', "&a", "&7"), Chat.colorize("&e" + Progress.getPercentages(0, 10) + "%"), 10, 10, 10);
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}
