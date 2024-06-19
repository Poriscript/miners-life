package green_villager.miners_dream.block;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;
import net.minecraft.item.Items;

import java.util.Set;

public class BlockDefinition {

    private static final Set<ItemStack> miners_dream_block_items = ItemStackSet.create();

    public static void DefineBlocks() {
        final Block CHARCOAL_BLOCK = BlockRegistration.Register(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), "charcoal_block");

        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);

        miners_dream_block_items.add(new ItemStack(CHARCOAL_BLOCK));
    }

    public static Set<ItemStack> GetMinersDreamBlockItems() {
        return miners_dream_block_items;
    }
}
