package green_villager.miners_life.item_group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemGroupRegistration {
    public static ItemGroup Register(ItemConvertible icon_supplier, String display_name, Set<ItemConvertible> items, Identifier id) {
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
