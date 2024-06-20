package green_villager.miners_dream.item_group;

import green_villager.miners_dream.MinersDream;
import green_villager.miners_dream.block.BlockDefinition;
import green_villager.miners_dream.item.ItemDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import java.util.Set;

public class ItemGroupDefinition {
    public static void defineItemGroup() {
        final ItemGroup MINERS_DREAM = ItemGroupRegistration.Register(Blocks.BRICKS, "Miners Dream", getAllMinersDreamItems(), Identifier.of(MinersDream.MOD_ID, "item_group.miners_dream"));
    }

    private static Set<ItemStack> getAllMinersDreamItems(){
        Set<ItemStack> all_items = ItemStackSet.create();

        all_items.addAll(ItemDefinition.getMinersDreamItems());
        all_items.addAll(BlockDefinition.getMinersDreamBlockItems());

        return all_items;
    }
}
