package green_villager.miners_life.item_group;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemGroupRegistration {
    public static void defineItemGroup() {
        Set<ItemConvertible> all_items = new HashSet<>();
        all_items.addAll(ItemRegistration.getAllMinersDreamItems());
        all_items.addAll(BlockRegistration.getAllMinersDreamBlocks());

        final ItemGroup MINERS_LIFE = RegisterItemGroup(Blocks.BRICKS, "Miners Dream", all_items, Identifier.of(MinersLife.MOD_ID, "item_group.miners_life"));
    }

    public static ItemGroup RegisterItemGroup(ItemConvertible icon_supplier, String display_name, Set<ItemConvertible> items, Identifier id) {
        ItemGroup itemGroup = FabricItemGroup.builder()
                .icon(() -> new ItemStack(icon_supplier))
                .displayName(Text.of(display_name))
                .entries((context, entries) -> entries.addAll(items.stream()
                        .map(ItemStack::new)
                        .collect(Collectors.toList())))
                .build();

        return Registry.register(Registries.ITEM_GROUP, id, itemGroup);
    }
}
