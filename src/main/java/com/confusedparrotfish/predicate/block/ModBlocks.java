package com.confusedparrotfish.predicate.block;

import java.util.function.Supplier;

import com.confusedparrotfish.predicate.item.ModItems;
import com.confusedparrotfish.predicate.lib.partikle.props;
import com.confusedparrotfish.predicate.item.ModItemGroup;
import com.confusedparrotfish.predicate.Predicate;
import com.confusedparrotfish.predicate.block.custom.pipes.pipe;
import com.confusedparrotfish.predicate.block.custom.pipes.pipeinput;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
// import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS,
            Predicate.modid);

    // blocks

    public static final RegistryObject<Block> PIPE = registerblock("pipe",
            () -> new pipe(props.metal_prop(1)));

    public static final RegistryObject<Block> PIPE_INPUT = registerblock("pipe_input",
            () -> new pipeinput(props.metal_prop(1)));

    // end of blocks

    public static <T extends Block> RegistryObject<T> registerblock(String name, Supplier<T> block) {
        RegistryObject<T> retval = blocks.register(name, block);

        registerblockitem(name, retval);

        return retval;
    }

    public static <T extends Block> void registerblockitem(String name, RegistryObject<T> block) {
        ModItems.items.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.predicatetab)));
    }

    public static void register(IEventBus eventBus) {
        blocks.register(eventBus);
    }

    // properties default

    public static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return (boolean) false;
    }
}
