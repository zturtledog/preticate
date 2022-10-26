package com.confusedparrotfish.predicate.lib.partikle;

import java.util.Random;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class utils {
    //items

    public static final void ejectitem(World world, BlockPos pos, ItemStack item, double off) {
        Random rand = world.rand;

        ItemEntity entityItem = new ItemEntity(world,
                pos.getX()+0.5, pos.getY()+off, pos.getZ()+0.5,
                item.copy());

        if (item.hasTag()) {
            entityItem.getItem().setTag(item.getTag().copy());
        }

        float factor = 0.5F;
        entityItem.setMotion(
            (rand.nextDouble()-0.5d)*factor,
            // 0,
            (rand.nextDouble()-0.5d)*factor + 0.7F,
            (rand.nextDouble()-0.5d)*factor);
            // 0);
        world.addEntity(entityItem);
    }

    public static final void dropitem(World world, BlockPos pos, ItemStack item) {
        ItemEntity entityItem = new ItemEntity(world,
                pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                item.copy());

        if (item.hasTag()) {
            entityItem.getItem().setTag(item.getTag().copy());
        }

        world.addEntity(entityItem);
    }

    //

    public static boolean isinlist(Object[] bjx, Object jx) {
        for (Object object : bjx) {
            System.out.println(object.toString()+"::"+jx.toString());
            if (object == jx) {
                return true;
            }
        }
        return false;
    }
}
