package green_villager.miners_dream.block;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStackSet;

import java.util.HashSet;
import java.util.Set;

public class BlockDefinition {

    private static final Set<ItemConvertible> miners_dream_blocks = new HashSet<>();

    public static void defineBlocks() {
        final Block CHARCOAL_BLOCK = BlockRegistration.register(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), "charcoal_block");

        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);

        miners_dream_blocks.add(CHARCOAL_BLOCK);
    }

    public static Set<ItemConvertible> getMinersDreamBlocks() {
        return miners_dream_blocks;
    }
}
