package poriscript.miners_life.block;

import net.minecraft.data.family.BlockFamily;
import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.definition.DropItemByExplosionBlock;
import poriscript.miners_life.block.definition.WoodyOreBlocks;
import poriscript.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistration {
    public static final List<ItemConvertible> ALL_BLOCKS = new ArrayList<>();

    public static final Block MEATITE_ORE = registerBlock(new DropItemByExplosionBlock(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f), ItemRegistration.MEATITE), "meatite_ore");
    public static final Block VEGETABLITE_ORE = registerBlock(new DropItemByExplosionBlock(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f), ItemRegistration.VEGETABLITE), "vegetablite_ore");

    public static final Block NETHER_BRICK_FENCE_GATE = registerBlock(new FenceGateBlock(WoodyOreBlocks.METAL_FENCE_GATE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE)), "nether_brick_fence_gate");
    public static final Block CHARCOAL_BLOCK = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), "charcoal_block");

    public static final BlockFamily CHARCOAL_FAMILY = registerBlocks(new WoodyOreBlocks(CHARCOAL_BLOCK, false).getBlockFamily(), "charcoal");
    public static final BlockFamily COAL_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.COAL_BLOCK, false).getBlockFamily(), "coal");
    public static final BlockFamily GOLD_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.GOLD_BLOCK, true).getBlockFamily(), "gold");
    public static final BlockFamily DIAMOND_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.DIAMOND_BLOCK, false).getBlockFamily(), "diamond");
    public static final BlockFamily EMERALD_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.EMERALD_BLOCK, false).getBlockFamily(), "emerald");
    public static final BlockFamily LAPIS_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.LAPIS_BLOCK, false).getBlockFamily(), "lapis");
    public static final BlockFamily REDSTONE_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.REDSTONE_BLOCK, false).getBlockFamily(), "redstone");
    public static final BlockFamily NETHERITE_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.NETHERITE_BLOCK, false).getBlockFamily(), "netherite");

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);
    }

    private static Block registerBlock(Block new_block, String blockId) {
        final Identifier id = MinersLife.getMinersLifeId(blockId);

        Registry.register(Registries.ITEM, id, new BlockItem(new_block, new Item.Settings()));

        final Block block = Registry.register(Registries.BLOCK, id, new_block);
        ALL_BLOCKS.add(block);

        return block;
    }

    private static Block registerBlock(BlockFamily blockFamily, BlockFamily.Variant variant, String blockId) {
        return registerBlock(blockFamily.getVariant(variant), String.format("%s_%s", blockId, variant.getName()));
    }

    private static BlockFamily registerBlocks(BlockFamily blockFamily, String blockId) {
        final Block fenceGate = registerBlock(blockFamily, BlockFamily.Variant.FENCE_GATE, blockId);
        final Block fence = registerBlock(blockFamily, BlockFamily.Variant.FENCE, blockId);
        final Block button = registerBlock(blockFamily, BlockFamily.Variant.BUTTON, blockId);
        final Block trapdoor = registerBlock(blockFamily, BlockFamily.Variant.TRAPDOOR, blockId);
        final Block slab = registerBlock(blockFamily, BlockFamily.Variant.SLAB, blockId);
        final Block stairs = registerBlock(blockFamily, BlockFamily.Variant.STAIRS, blockId);

        return blockFamily;
    }
}
