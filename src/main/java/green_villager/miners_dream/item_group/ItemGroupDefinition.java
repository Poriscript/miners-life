package green_villager.miners_dream.item_group;

import green_villager.miners_dream.MinersDream;
import green_villager.miners_dream.block.BlockDefinition;
import green_villager.miners_dream.item.ItemDefinition;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

import java.util.Set;

public class ItemGroupDefinition {
    public static void DefineItemGroup() {
        final ItemGroup MINERS_DREAM = ItemGroupRegistration.Register(Blocks.BRICKS, "Miners Dream", GetAllMinersDreamItems(), Identifier.of(MinersDream.MOD_ID, "item_group.miners_dream"));
    }

    private static Set<ItemStack> GetAllMinersDreamItems(){
        Set<ItemStack> all_items = ItemStackSet.create();

        all_items.addAll(ItemDefinition.GetMinersDreamItems());
        all_items.addAll(BlockDefinition.GetMinersDreamBlockItems());

        return all_items;
    }
}
