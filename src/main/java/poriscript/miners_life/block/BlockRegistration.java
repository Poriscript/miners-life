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

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class BlockRegistration {

    public static final Block CHARCOAL_BLOCK = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), "charcoal_block");

    public static final Block MEATITE_ORE = registerBlock(new DropItemByExplosionBlock(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f), ItemRegistration.MEATITE), "meatite_ore");
    public static final Block VEGETABLITE_ORE = registerBlock(new DropItemByExplosionBlock(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f), ItemRegistration.VEGETABLITE), "vegetablite_ore");

    public static BlockFamily COAL_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.COAL_BLOCK, false);
    public static BlockFamily CHARCOAL_FAMILY = WoodyOreBlocks.defineBlocks(BlockRegistration.CHARCOAL_BLOCK, false);
    public static BlockFamily GOLD_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.GOLD_BLOCK, true);
    public static BlockFamily DIAMOND_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.DIAMOND_BLOCK, false);
    public static BlockFamily EMERALD_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.EMERALD_BLOCK, false);
    public static BlockFamily LAPIS_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.LAPIS_BLOCK, false);
    public static BlockFamily REDSTONE_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.REDSTONE_BLOCK, false);
    public static BlockFamily NETHERITE_FAMILY = WoodyOreBlocks.defineBlocks(Blocks.NETHERITE_BLOCK, false);

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);

        registerBlocks(COAL_FAMILY, "coal");
        registerBlocks(CHARCOAL_FAMILY, "charcoal");
        registerBlocks(GOLD_FAMILY, "gold");
        registerBlocks(DIAMOND_FAMILY, "diamond");
        registerBlocks(EMERALD_FAMILY, "emerald");
        registerBlocks(LAPIS_FAMILY, "lapis");
        registerBlocks(REDSTONE_FAMILY, "redstone");
        registerBlocks(NETHERITE_FAMILY, "netherite");
    }

    public static Block registerBlock(Block new_block, String blockId) {
        final Identifier id = MinersLife.getMinersLifeId(blockId);
        Registry.register(Registries.ITEM, id, new BlockItem(new_block, new Item.Settings()));

        return Registry.register(Registries.BLOCK, id, new_block);
    }

    private static void registerBlocks(BlockFamily blockFamily, String blockId) {
        blockFamily.getVariants().forEach((variant, block) -> {
            registerBlock(block, String.format("%s_%s", blockId, variant.getName()));
        });
    }

    public static Set<ItemConvertible> getAllMinersLifeBlocks() {
        final Field[] fields = BlockRegistration.class.getDeclaredFields();
        final Set<ItemConvertible> items = new HashSet<>();

        for (Field field : fields) {
            try {
                final Object value = field.get(BlockRegistration.class);

                if (value instanceof ItemConvertible) {
                    items.add((ItemConvertible) value);
                } else if (value instanceof BlockFamily) {
                    items.addAll(((BlockFamily) value).getVariants().values());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return items;
    }
}
