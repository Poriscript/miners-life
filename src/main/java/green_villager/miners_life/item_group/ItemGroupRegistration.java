package green_villager.miners_life.item_group;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemGroupRegistration {
    public static void defineItemGroup() {
        Set<ItemConvertible> all_items = new HashSet<>();
        all_items.addAll(ItemRegistration.getAllMinersLifeItems());
        all_items.addAll(BlockRegistration.getAllMinersLifeBlocks());

        final ItemGroup MINERS_LIFE_ITEM_GROUP = registerItemGroup(Blocks.BRICKS, Text.translatable("itemgroup.miners_life.miners_life"), all_items, MinersLife.getMinersLifeId("miners_life"));
    }

    public static ItemGroup registerItemGroup(ItemConvertible icon_supplier, MutableText display_name_translation_key, Set<ItemConvertible> items, Identifier id) {
        ItemGroup itemGroup = FabricItemGroup.builder()
                .icon(() -> new ItemStack(icon_supplier))
                .displayName(display_name_translation_key)
                .entries((context, entries) -> entries.addAll(items.stream()
                        .map(ItemStack::new)
                        .collect(Collectors.toList())))
                .build();

        return Registry.register(Registries.ITEM_GROUP, id, itemGroup);
    }
}
