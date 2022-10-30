package com.confusedparrotfish.predicate.tileentity.custom.pipes;

import com.confusedparrotfish.predicate.block.ModBlocks;
import com.confusedparrotfish.predicate.lib.blocknetwork;
import com.confusedparrotfish.predicate.lib.blocknetwork.connectionmanager;
import com.confusedparrotfish.predicate.lib.blocknetwork.connectionrules;
import com.confusedparrotfish.predicate.tileentity.ModTileEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Iterator;
import java.util.Map.Entry;

public class pipeinputile extends TileEntity {
    public blocknetwork networc = new blocknetwork();

    public pipeinputile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);

        //init
        networc.rules.put(ModBlocks.PIPE.get(), new connectionmanager() {
            @Override
            public connectionrules generateconection(ServerWorld world, BlockPos pos) {
                return new connectionrules(true, true, true, true, true, true);
            }
        });
        networc.rules.put(ModBlocks.PIPE_INPUT.get(), new connectionmanager() {
            @Override
            public connectionrules generateconection(ServerWorld world, BlockPos pos) {
               connectionrules cnk = new connectionrules();///.fromrot(world.getBlockState(pos).get(FACING));
               cnk.pass=true;

               return cnk;
            }
        });
    }

    public pipeinputile() {
        this(ModTileEntities.PIPE_INPUT_TILE.get());
    }

    public CompoundNBT write(CompoundNBT compound) {
        //TODO: save
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        //TODO: load
    }

    public void update(ServerWorld world, BlockPos pos) {
        // networc.update(world, pos, new connectionrules(true,true,true,true,true,true));
        networc.calculate(world, pos, new connectionrules(true,true,true,true,true,true));

        System.out.println(networc.toString());

        Iterator<Entry<BlockPos, connectionrules>> entries = networc.path.entrySet().iterator();

        while (entries.hasNext()) {
            Entry<BlockPos, connectionrules> entry = entries.next();
                
            world.setBlockState(entry.getKey(), Blocks.ANDESITE.getDefaultState(), 3);
        }
    }
}