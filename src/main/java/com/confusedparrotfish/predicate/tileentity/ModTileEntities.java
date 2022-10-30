package com.confusedparrotfish.predicate.tileentity;

import com.confusedparrotfish.predicate.Predicate;
import com.confusedparrotfish.predicate.block.ModBlocks;
import com.confusedparrotfish.predicate.tileentity.custom.pipes.pipeinputile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;;;

public class ModTileEntities {
    public static DeferredRegister<TileEntityType<?>> tiles =
        DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Predicate.modid);

    //entities

    public static RegistryObject<TileEntityType<pipeinputile>> PIPE_INPUT_TILE =
        tiles.register("pipeinputile", ()-> TileEntityType.Builder.create(
            pipeinputile::new , ModBlocks.PIPE_INPUT.get()).build(null));

    //end of entities

    public static void register(IEventBus eventBus) {
        tiles.register(eventBus);
    }
}
