package poriscript.miners_life.item_group;

import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.data.enums.Identifiers;
import poriscript.miners_life.data.enums.TranslationKeyRoots;
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

        final String translationKey = Identifiers.MOD_ID.getTranslationKey(TranslationKeyRoots.ItemGroup);

        final ItemGroup MINERS_LIFE_ITEM_GROUP = registerItemGroup(Text.translatable(translationKey), modItems, Identifiers.MOD_ID.getId());
    }

    private static ItemGroup registerItemGroup(MutableText display_name_translation_key, List<ItemConvertible> items, Identifier id) {
        ItemGroup itemGroup = FabricItemGroup.builder()
                .icon(() -> new ItemStack(BlockRegistration.MEATITE_ORE))
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
