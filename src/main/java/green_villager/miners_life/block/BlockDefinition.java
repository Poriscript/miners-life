package green_villager.miners_life.block;

import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BlockDefinition {

    private static final Set<ItemConvertible> miners_life_blocks = new HashSet<>();

    public static void defineBlocks() {
        final Block CHARCOAL_BLOCK = BlockRegistration.register(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), "charcoal_block");
        final Block DRIED_MEET_BLOCK = BlockRegistration.register(new Block(AbstractBlock.Settings.copy(Blocks.SPONGE)), "dried_meet_block");

        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);

        miners_life_blocks.add(CHARCOAL_BLOCK);
        miners_life_blocks.add(DRIED_MEET_BLOCK);
    }

    public static Set<ItemConvertible> getMinersDreamBlocks() {
        return miners_life_blocks;
    }

    public static Item getMinersDreamBlock(String block_name) {
        for (ItemConvertible ic : miners_life_blocks) {
            Item item = new ItemStack(ic).getItem();

            if (Objects.equals(item.getTranslationKey(), String.format("block.%s.%s", MinersLife.MOD_ID, block_name))) {
                return item;
            }
        }

        throw new RuntimeException("There are no blocks with the name given.");
    }
}
