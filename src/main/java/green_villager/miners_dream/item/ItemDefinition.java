package green_villager.miners_dream.item;

import green_villager.miners_dream.MinersDream;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;

import java.util.Set;

public class ItemDefinition {
    private static final Set<ItemStack> miners_dream_items = ItemStackSet.create();

    // Instant health heals 4 (2 hearts) of health per tick.
    // Dried meat has a 20% chance of instantly healing 8 (2 hearts) of health.

    public static void defineItems() {
        final Item SULFUR = ItemRegistration.Register(new Item(new Item.Settings()), "sulfur");
        final Item WET_MEET = ItemRegistration.Register(new Item(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, MinersDream.tickFrom(10)), 1).statusEffect(new StatusEffectInstance(StatusEffects.POISON, MinersDream.tickFrom(10)), 1).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, MinersDream.tickFrom(10)), 1).build())), "wet_meet");
        final Item DRIED_MEET = ItemRegistration.Register(new Item(new Item.Settings().food(new FoodComponent.Builder().snack().nutrition(4).saturationModifier(1).statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2), 0.2f).build())), "dried_meet");

        FuelRegistry.INSTANCE.add(SULFUR, 20);

        miners_dream_items.add(new ItemStack(SULFUR));
        miners_dream_items.add(new ItemStack(WET_MEET));
        miners_dream_items.add(new ItemStack(DRIED_MEET));
    }

    public static Set<ItemStack> getMinersDreamItems() {
        return miners_dream_items;
    }
}

