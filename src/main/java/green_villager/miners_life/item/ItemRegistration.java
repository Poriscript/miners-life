package green_villager.miners_life.item;

import green_villager.miners_life.MinersLife;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ItemRegistration {
    // Instant health heals 4 (2 hearts) of health per tick.
    // Dried meat has a 20% chance of instantly healing 8 (2 hearts) of health.
    public static final Item MEATITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(1).build())), "meatite");
    public static final Item COOKED_MEATITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(1).build())), "cooked_meatite");

    public static final Item VEGETABLITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(1).build())), "vegetablite");
    public static final Item COOKED_VEGETABLITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(6).build())), "cooked_vegetablite");

    public static void defineItems() {

    }

    public static Item RegisterItem(Item new_item, String item_id) {
        return Registry.register(Registries.ITEM, MinersLife.getMinersLifeId(item_id), new_item);
    }

    public static Set<ItemConvertible> getAllMinersLifeItems() {
        final Field[] fields = ItemRegistration.class.getDeclaredFields();
        final Set<ItemConvertible> items = new HashSet<>();

        for (Field field : fields) {
            try {
                final Object value = field.get(ItemRegistration.class);

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

