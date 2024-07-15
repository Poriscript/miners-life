package poriscript.miners_life.block;

import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Items;
import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.definition.DropItemByExplosionBlock;
import poriscript.miners_life.block.definition.WoodyOreBlocks;
import poriscript.miners_life.data.enums.Identifiers;
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

    public static final Block MEATITE_ORE = registerBlock(new DropItemByExplosionBlock(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f), ItemRegistration.MEATITE), Identifiers.MEATITE_ORE.getId());
    public static final Block VEGETABLITE_ORE = registerBlock(new DropItemByExplosionBlock(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f), ItemRegistration.VEGETABLITE), Identifiers.VEGETABLITE_ORE.getId());

    public static final Block NETHER_BRICK_FENCE_GATE = registerBlock(new FenceGateBlock(WoodyOreBlocks.METAL_FENCE_GATE_WOOD_TYPE, AbstractBlock.Settings.copy(Blocks.NETHER_BRICK_FENCE)), Identifiers.NETHER_BRICK_FENCE_GATE.getId());
    public static final Block CHARCOAL_BLOCK = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), Identifiers.CHARCOAL_BLOCK.getId());

    public static final BlockFamily CHARCOAL_FAMILY = registerBlocks(new WoodyOreBlocks(CHARCOAL_BLOCK, false).getBlockFamily(), Items.CHARCOAL);
    public static final BlockFamily COAL_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.COAL_BLOCK, false).getBlockFamily(), Items.COAL);
    public static final BlockFamily GOLD_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.GOLD_BLOCK, true).getBlockFamily(), Identifiers.GOLD.getName());
    public static final BlockFamily DIAMOND_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.DIAMOND_BLOCK, false).getBlockFamily(), Items.DIAMOND);
    public static final BlockFamily EMERALD_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.EMERALD_BLOCK, false).getBlockFamily(), Items.EMERALD);
    public static final BlockFamily LAPIS_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.LAPIS_BLOCK, false).getBlockFamily(), Items.LAPIS_LAZULI);
    public static final BlockFamily REDSTONE_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.REDSTONE_BLOCK, false).getBlockFamily(), Items.REDSTONE);
    public static final BlockFamily NETHERITE_FAMILY = registerBlocks(new WoodyOreBlocks(Blocks.NETHERITE_BLOCK, false).getBlockFamily(), Identifiers.NETHERITE.getName());

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);
    }

    private static Block registerBlock(Block new_block, Identifier id) {
        Registry.register(Registries.ITEM, id, new BlockItem(new_block, new Item.Settings()));

        final Block block = Registry.register(Registries.BLOCK, id, new_block);
        ALL_BLOCKS.add(block);

        return block;
    }

    private static Block registerBlock(BlockFamily blockFamily, BlockFamily.Variant variant, String name) {
        final Identifier id = Identifier.of(Identifiers.MOD_ID.getName(), String.format("%s_%s", name, variant.getName()));

        return registerBlock(blockFamily.getVariant(variant), id);
    }

    private static BlockFamily registerBlocks(BlockFamily blockFamily, Item material) {
        return registerBlocks(blockFamily, MinersLife.getName(material));
    }

    private static BlockFamily registerBlocks(BlockFamily blockFamily, String name) {
        final Block fenceGate = registerBlock(blockFamily, BlockFamily.Variant.FENCE_GATE, name);
        final Block fence = registerBlock(blockFamily, BlockFamily.Variant.FENCE, name);
        final Block button = registerBlock(blockFamily, BlockFamily.Variant.BUTTON, name);
        final Block trapdoor = registerBlock(blockFamily, BlockFamily.Variant.TRAPDOOR, name);
        final Block slab = registerBlock(blockFamily, BlockFamily.Variant.SLAB, name);
        final Block stairs = registerBlock(blockFamily, BlockFamily.Variant.STAIRS, name);

        return blockFamily;
    }

    public static List<BlockFamily> getFamilies() {
        return List.of(
                CHARCOAL_FAMILY,
                COAL_FAMILY,
                GOLD_FAMILY,
                DIAMOND_FAMILY,
                EMERALD_FAMILY,
                LAPIS_FAMILY,
                REDSTONE_FAMILY,
                NETHERITE_FAMILY
        );
    }
}
