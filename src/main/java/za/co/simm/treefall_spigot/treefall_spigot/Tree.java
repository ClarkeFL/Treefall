package za.co.simm.treefall_spigot.treefall_spigot;


import com.google.common.collect.ImmutableList;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ExperienceOrb;

import java.util.ArrayList;
import java.util.Collection;

final class Tree {

    final static Collection<BlockFace> directions = ImmutableList.of(
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.EAST,
            BlockFace.SOUTH,
            BlockFace.WEST,
            BlockFace.NORTH_EAST,
            BlockFace.SOUTH_EAST,
            BlockFace.NORTH_WEST,
            BlockFace.SOUTH_WEST
    );
    final static int max = 1024;
    final Collection<Block> visited = new ArrayList<>();
    int leaves = 0;
    int call = 0;

    public Tree(final Block block) {
        get(block);
        if (isTree()) {
            destroy(block);
        }
    }

    void get(final Block block) {

        call++;

        if (call >= max) {
            return;
        }

        if (block.getType() == Material.AIR) {
            return;
        }

        if (visited.contains(block)) {
            return;
        }

        if (block.getType() == Material.ACACIA_LOG || block.getType() == Material.BIRCH_LOG ||
                block.getType() == Material.DARK_OAK_LOG || block.getType() == Material.JUNGLE_LOG ||
                block.getType() == Material.OAK_LOG || block.getType() == Material.SPRUCE_LOG) {
            visited.add(block);

            for(BlockFace face : BlockFace.values()) {
                //Check if the adjacent block relative to this face is a furnace
                if(block.getRelative(face).getType() == Material.ACACIA_LOG ||
                        block.getRelative(face).getType() == Material.BIRCH_LOG ||
                        block.getRelative(face).getType() == Material.DARK_OAK_LOG ||
                        block.getRelative(face).getType() == Material.JUNGLE_LOG ||
                        block.getRelative(face).getType() == Material.OAK_LOG ||
                        block.getRelative(face).getType() == Material.SPRUCE_LOG) {
                    visited.add(block);
                    break;
                }
            }

            for (BlockFace direction : directions) {
                get(block.getRelative(direction));
            }
            return;
        }


        if (block.getType() == Material.ACACIA_LEAVES || block.getType() == Material.BIRCH_LEAVES ||
                block.getType() == Material.DARK_OAK_LEAVES || block.getType() == Material.JUNGLE_LEAVES ||
                block.getType() == Material.OAK_LEAVES || block.getType() == Material.SPRUCE_LEAVES) {
            visited.add(block);

//            for(BlockFace face : BlockFace.values()) {
//                //Check if the adjacent block relative to this face is a furnace
//                if(block.getRelative(face).getType() == Material.ACACIA_LEAVES ||
//                        block.getRelative(face).getType() == Material.BIRCH_LEAVES ||
//                        block.getRelative(face).getType() == Material.DARK_OAK_LEAVES ||
//                        block.getRelative(face).getType() == Material.JUNGLE_LEAVES ||
//                        block.getRelative(face).getType() == Material.OAK_LEAVES ||
//                        block.getRelative(face).getType() == Material.SPRUCE_LEAVES) {
//                    visited.add(block);
//                    break;
//                }
//            }
//
//            for (BlockFace direction : directions) {
//                get(block.getRelative(direction));
//            }

            leaves++;
        }

    }

    boolean isTree() {
        return leaves > 1;
    }

    void destroy(final Block block) {
        visited.forEach((Block) -> block.getWorld().spawn(block.getLocation().add(0, 1, 0), ExperienceOrb.class).setExperience(1));
        visited.forEach(Block::breakNaturally);
    }
}

