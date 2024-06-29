package green_villager.miners_life.block;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.definition.MeatiteOre;
import green_villager.miners_life.block.definition.MilkiteBlock;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.component.type.FoodComponent;
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

    public static final Block MILKITE = registerBlock(new MilkiteBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)), "milkite");
    public static final Block MEATITE_ORE = registerBlock(new MeatiteOre(AbstractBlock.Settings.copy(Blocks.BASALT).strength(0.02f)), "meatite_ore");

    public static final Block EDIBLE_VINE = registerBlock(new GlowLichenBlock(AbstractBlock.Settings.copy(Blocks.GLOW_LICHEN).luminance(GlowLichenBlock.getLuminanceSupplier(0))), "edible_vine", new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().nutrition(1).saturationModifier(7).build()));

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);
    }

    public static Block registerBlock(Block new_block, String block_id) {
        return registerBlock(new_block, block_id, new Item.Settings());
    }

    public static Block registerBlock(Block new_block, String block_id, Item.Settings settings) {
        final Identifier id = MinersLife.getMinersLifeId(block_id);
        Registry.register(Registries.ITEM, id, new BlockItem(new_block, settings));
        return Registry.register(Registries.BLOCK, id, new_block);
    }

    public static Set<ItemConvertible> getAllMinersLifeBlocks() {
        final Field[] fields = BlockRegistration.class.getDeclaredFields();
        final Set<ItemConvertible> items = new HashSet<>();

        for (Field field : fields) {
            try {
                final Object value = field.get(BlockRegistration.class);

                if (value instanceof ItemConvertible) {
                    items.add((ItemConvertible) value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return items;
    }
}
