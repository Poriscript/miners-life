package green_villager.miners_life.item;

import green_villager.miners_life.MinersLife;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ItemDefinition {
    private static final Set<ItemConvertible> miners_life_items = new HashSet<>();

    // Instant health heals 4 (2 hearts) of health per tick.
    // Dried meat has a 20% chance of instantly healing 8 (2 hearts) of health.

    public static void defineItems() {
        final Item SULFUR = ItemRegistration.Register(new Item(new Item.Settings()), "sulfur");
        final Item WET_MEET = ItemRegistration.Register(new Item(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, MinersLife.tickFrom(10)), 1).statusEffect(new StatusEffectInstance(StatusEffects.POISON, MinersLife.tickFrom(10)), 1).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, MinersLife.tickFrom(10)), 1).build())), "wet_meet");
        final Item DRIED_MEET = ItemRegistration.Register(new Item(new Item.Settings().food(new FoodComponent.Builder().snack().nutrition(4).saturationModifier(1).statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2), 0.2f).build())), "dried_meet");

        miners_life_items.add(SULFUR);
        miners_life_items.add(WET_MEET);
        miners_life_items.add(DRIED_MEET);
    }

    public static Set<ItemConvertible> getMinersDreamItems() {
        return miners_life_items;
    }

    public static Item getMinersDreamItem(String item_name) {
        for (ItemConvertible item : miners_life_items) {
            if (Objects.equals(item.asItem().getTranslationKey(), String.format("item.%s.%s", MinersLife.MOD_ID, item_name))) {
                return item.asItem();
            }
        }

        throw new RuntimeException("There are no items with the name given.");
    }
}

