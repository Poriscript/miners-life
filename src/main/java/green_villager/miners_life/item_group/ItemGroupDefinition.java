package green_villager.miners_life.item_group;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockDefinition;
import green_villager.miners_life.item.ItemDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class ItemGroupDefinition {
    public static void defineItemGroup() {
        Set<ItemConvertible> all_items = new HashSet<>();
        all_items.addAll(ItemDefinition.getAllMinersDreamItems());
        all_items.addAll(BlockDefinition.getAllMinersDreamBlocks());

        final ItemGroup MINERS_LIFE = ItemGroupRegistration.Register(Blocks.BRICKS, "Miners Dream", all_items, Identifier.of(MinersLife.MOD_ID, "item_group.miners_life"));
    }
}
