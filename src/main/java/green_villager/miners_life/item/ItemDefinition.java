package green_villager.miners_life.item;

import green_villager.miners_life.MinersLife;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ItemDefinition {
    // Instant health heals 4 (2 hearts) of health per tick.
    // Dried meat has a 20% chance of instantly healing 8 (2 hearts) of health.
    public static final Item SULFUR = ItemRegistration.Register(new Item(new Item.Settings()), "sulfur");
    public static final Item WET_MEET = ItemRegistration.Register(new Item(new Item.Settings().food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, MinersLife.getTickFromSeconds(10)), 1).statusEffect(new StatusEffectInstance(StatusEffects.POISON, MinersLife.getTickFromSeconds(10)), 1).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, MinersLife.getTickFromSeconds(10)), 1).build())), "wet_meet");
    public static final Item DRIED_MEET = ItemRegistration.Register(new Item(new Item.Settings().food(new FoodComponent.Builder().snack().nutrition(4).saturationModifier(1).statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2), 0.2f).build())), "dried_meet");

    public static void defineItems() {

    }

    public static Set<ItemConvertible> getAllMinersDreamItems() {
        final Field[] fields = ItemDefinition.class.getDeclaredFields();
        final Set<ItemConvertible> items = new HashSet<>();

        for (Field field : fields) {
            try {
                final Object value = field.get(ItemDefinition.class);

                if (value instanceof ItemConvertible) {
                    items.add((ItemConvertible) value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return items;
    }
}

