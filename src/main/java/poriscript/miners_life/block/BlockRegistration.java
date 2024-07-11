package poriscript.miners_life.block;

import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.definition.DropItemByExplosionBlock;
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

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);
    }

    public static Block registerBlock(Block new_block, String block_id) {
        final Identifier id = MinersLife.getMinersLifeId(block_id);
        Registry.register(Registries.ITEM, id, new BlockItem(new_block, new Item.Settings()));
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
