package za.co.simm.treefall_spigot.treefall_spigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Treefall_spigotBlockListener implements Listener {

    public Treefall_spigotBlockListener(final Treefall_spigot instance) {
        Bukkit.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler(ignoreCancelled = true)
    @SuppressWarnings("unused")
    void onBlockBreak(final BlockBreakEvent event) {
        Block block = event.getBlock();
        if (isLog(block)) {
            new Tree(block);
        }
    }

    boolean isLog(final Block block) {
        return block.getType() == Material.ACACIA_LOG || block.getType() == Material.BIRCH_LOG ||
                block.getType() == Material.DARK_OAK_LOG || block.getType() == Material.JUNGLE_LOG ||
                block.getType() == Material.OAK_LOG || block.getType() == Material.SPRUCE_LOG;
    }

}
