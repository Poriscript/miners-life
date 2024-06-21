package green_villager.miners_life.item;

import green_villager.miners_life.MinersLife;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistration {
    public static Item Register(Item new_item, String item_id) {
        return Registry.register(Registries.ITEM, Identifier.of(MinersLife.MOD_ID, item_id), new_item);
    }
}
