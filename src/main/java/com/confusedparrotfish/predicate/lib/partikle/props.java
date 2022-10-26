package com.confusedparrotfish.predicate.lib.partikle;

import com.confusedparrotfish.predicate.block.ModBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class props {
    public static Properties stone_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.ROCK)
            .harvestLevel(lvl).harvestTool(ToolType.PICKAXE)
            .setRequiresTool().hardnessAndResistance(lvl+2);
    }

    public static Properties wood_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.WOOD)
            .harvestLevel(lvl).harvestTool(ToolType.AXE)
            .setRequiresTool().hardnessAndResistance(lvl+1);
    }

    public static Properties glass_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.GLASS)
            .hardnessAndResistance(lvl).sound(SoundType.GLASS)
            .notSolid().setAllowsSpawn(ModBlocks::neverAllowSpawn).setOpaque(ModBlocks::isntSolid)
            .setSuffocates(ModBlocks::isntSolid).setBlocksVision(ModBlocks::isntSolid);
    }

    public static Properties metal_prop(int lvl) {
        return AbstractBlock.Properties.create(Material.IRON)
            .harvestLevel(lvl).harvestTool(ToolType.PICKAXE)
            .setRequiresTool().hardnessAndResistance(lvl+1);
    }
}
