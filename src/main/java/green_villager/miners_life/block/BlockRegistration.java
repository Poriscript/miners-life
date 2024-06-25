package green_villager.miners_life.block;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.definition.MilkiteBlock;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
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
    public static final Block DRIED_MEET_BLOCK = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.SPONGE)), "dried_meet_block");

    public static final Block SULFUR_ORE = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.LAPIS_ORE)), "sulfur_ore");
    public static final Block DEEPSLATE_SULFUR_ORE = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_LAPIS_ORE)), "deepslate_sulfur_ore");
    public static final Block NITRE_ORE = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.LAPIS_ORE)), "nitre_ore");
    public static final Block DEEPSLATE_NITRE_ORE = registerBlock(new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_LAPIS_ORE)), "deepslate_nitre_ore");

    public static final Block MILKITE = registerBlock(new MilkiteBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)), "milkite");

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);
    }

    public static Block registerBlock(Block new_block, String block_id) {
        final Identifier id = Identifier.of(MinersLife.MOD_ID, block_id);
        Registry.register(Registries.ITEM, id, new BlockItem(new_block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, id, new_block);
    }

    public static Set<ItemConvertible> getAllMinersDreamBlocks() {
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
