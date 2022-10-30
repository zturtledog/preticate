package com.confusedparrotfish.predicate.block.custom.pipes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class pipe extends Block {
    public static final IntegerProperty LEVEL = IntegerProperty.create("mode", 0, 4);

    public pipe(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockRayTraceResult hit) {
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int lvl = 2+random.nextInt(3);
        // System.out.println(lvl+"::"+state.get(LEVEL));
        if (state.get(LEVEL) == 1) {
            world.setBlockState(pos, state.with(LEVEL, lvl), 2);
        }
    }

    // public static final VoxelShape SHAPE = Stream.of(
    //         Block.makeCuboidShape(0, 0, 0, 16, 7, 16),
    //         Block.makeCuboidShape(1, 7, 1, 15, 8, 15),
    //         Block.makeCuboidShape(2, 8, 5, 5, 9, 11),
    //         Block.makeCuboidShape(11, 8, 5, 14, 9, 11),
    //         Block.makeCuboidShape(5, 8, 2, 11, 9, 3),
    //         Block.makeCuboidShape(5, 8, 13, 11, 9, 14),
    //         Block.makeCuboidShape(3, 8, 3, 13, 9, 5),
    //         Block.makeCuboidShape(3, 8, 11, 13, 9, 13),
    //         Block.makeCuboidShape(5, 8, 10, 6, 9, 11),
    //         Block.makeCuboidShape(5, 8, 5, 6, 9, 6),
    //         Block.makeCuboidShape(10, 8, 5, 11, 9, 6),
    //         Block.makeCuboidShape(10, 8, 10, 11, 9, 11))
    //         .reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    // @Override
    // public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    //     return SHAPE;
    // }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}
