package green_villager.miners_dream.item_group;

import green_villager.miners_dream.MinersDream;
import green_villager.miners_dream.block.BlockDefinition;
import green_villager.miners_dream.item.ItemDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class ItemGroupDefinition {
    public static void defineItemGroup() {
        final ItemGroup MINERS_DREAM = ItemGroupRegistration.Register(Blocks.BRICKS, "Miners Dream", getAllMinersDreamItems(), Identifier.of(MinersDream.MOD_ID, "item_group.miners_dream"));
    }

    private static Set<ItemConvertible> getAllMinersDreamItems(){
        Set<ItemConvertible> all_items = new HashSet<>();

        all_items.addAll(ItemDefinition.getMinersDreamItems());
        all_items.addAll(BlockDefinition.getMinersDreamBlocks());

        return all_items;
    }
}
