package poriscript.miners_life.item;

import poriscript.miners_life.MinersLife;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistration {
    public static final List<ItemConvertible> ALL_ITEMS = new ArrayList<>();

    public static final Item MEATITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(1).build())), "meatite");
    public static final Item COOKED_MEATITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(1).build())), "cooked_meatite");

    public static final Item VEGETABLITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(1).build())), "vegetablite");
    public static final Item COOKED_VEGETABLITE = RegisterItem(new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(6).build())), "cooked_vegetablite");

    public static void defineItems() {

    }

    private static Item RegisterItem(Item new_item, String item_id) {
        final Item item = Registry.register(Registries.ITEM, MinersLife.getMinersLifeId(item_id), new_item);
        ALL_ITEMS.add(item);

        return item;
    }
}

