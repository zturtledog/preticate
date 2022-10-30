package com.confusedparrotfish.predicate.block.custom.pipes;

import java.util.Random;

import com.confusedparrotfish.predicate.Predicate;
import com.confusedparrotfish.predicate.lib.blocknetwork.connectionrules;
import com.confusedparrotfish.predicate.tileentity.ModTileEntities;
import com.confusedparrotfish.predicate.tileentity.custom.pipes.pipeinputile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class pipeinput extends Block {

    public pipeinput(Properties properties) {
        super(properties);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {


        // if (world.isRemote) {
        world.setDayTime(0);
        if (world.getTileEntity(pos) != null) {
            ((pipeinputile) world.getTileEntity(pos)).update(world, pos);
        }

        world.getPendingBlockTicks().scheduleTick(pos, this, 10);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.PIPE_INPUT_TILE.get().create();
    }

    @Override
		public void onBlockAdded(BlockState blockstate, World world, BlockPos pos, BlockState oldState, boolean moving) {
			world.getPendingBlockTicks().scheduleTick(pos, this, 10);
		}
}
