
package com.confusedparrotfish.predicate.lib.partikle;

import java.util.Random;

import net.minecraft.particles.IParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class particle {
    public static void oreblock(World world, BlockPos worldIn, IParticleData particle) {
        Random random = world.rand;

        for (Direction direction : Direction.values()) {
            BlockPos blockpos = worldIn.offset(direction);
            if (!world.getBlockState(blockpos).isOpaqueCube(world, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double) direction.getXOffset()
                        : (double) random.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double) direction.getYOffset()
                        : (double) random.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double) direction.getZOffset()
                        : (double) random.nextFloat();
                world.addParticle(particle, (double) worldIn.getX() + d1, (double) worldIn.getY() + d2,
                        (double) worldIn.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    public static void plane(World world, BlockPos pos, IParticleData particle, double off, int distr) {
        Random random = world.rand;

        for (int i = 0; i < 1; i+=1/distr) {
            for (int j = 0; j < 1; j+=1/distr) {
                if (random.nextDouble() > 0.5d) {
                    world.addParticle(particle, (double) pos.getX() + i, (double) pos.getY() + off,
                        (double) pos.getZ() + j, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    /**
     * create a particle that launches into the air like the lava pifzs and pops
     * 
     * @param world
     * @param pos
     * @param particle
     * @param off
     * @param itr
     */
    public static void eject(World world, BlockPos pos, IParticleData particle, double off, int itr) {
        Random rand = world.rand;

        for (int i = 0; i < itr; i++) {
            world.addParticle(particle, 
                (double) pos.getX()+0.5, 
                (double) pos.getY() + off,
                (double) pos.getZ()+0.5, 
                (rand.nextDouble()-0.5d)*0.15d,
                (rand.nextDouble()-0.5d)*0.15d + 0.027F,
                (rand.nextDouble()-0.5d)*0.15d
            );
        }
    }
}
