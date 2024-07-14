package poriscript.miners_life.item_group;

import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;

public class ItemGroupRegistration {
    public static void defineItemGroup() {
        final List<ItemConvertible> modItems = new ArrayList<>();

        modItems.addAll(ItemRegistration.ALL_ITEMS);
        modItems.addAll(BlockRegistration.ALL_BLOCKS);

        final ItemGroup MINERS_LIFE_ITEM_GROUP = registerItemGroup(BlockRegistration.MEATITE_ORE, Text.translatable("itemgroup.miners_life.miners_life"), modItems, MinersLife.getMinersLifeId("miners_life"));
    }

    private static ItemGroup registerItemGroup(ItemConvertible icon_supplier, MutableText display_name_translation_key, List<ItemConvertible> items, Identifier id) {
        ItemGroup itemGroup = FabricItemGroup.builder()
                .icon(() -> new ItemStack(icon_supplier))
                .displayName(display_name_translation_key)
                .entries((context, entries) -> {
                    for (ItemConvertible item : items) {
                        entries.add(item);
                    }
                })
                .build();

        return Registry.register(Registries.ITEM_GROUP, id, itemGroup);
    }
}
