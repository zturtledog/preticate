package com.confusedparrotfish.predicate.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup predicatetab = new ItemGroup("Predicate") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.ICON_ITEM.get());
        }
    };
}
