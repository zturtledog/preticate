package com.confusedparrotfish.predicate.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.confusedparrotfish.predicate.Predicate;

public class ModItems {
    public static final DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, Predicate.modid);

    //

    public static void register(IEventBus eventBus) {
        items.register(eventBus);
    }

    //properties

    private static Properties prop() {
        return new Item.Properties();//.group(ModItemGroup.predicatetab);
    }
}
