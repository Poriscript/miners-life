package green_villager.miners_life.block;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class BlockDefinition {

    public static final Block CHARCOAL_BLOCK = BlockRegistration.register(new Block(AbstractBlock.Settings.copy(Blocks.COAL_BLOCK)), "charcoal_block");
    public static final Block DRIED_MEET_BLOCK = BlockRegistration.register(new Block(AbstractBlock.Settings.copy(Blocks.SPONGE)), "dried_meet_block");

    public static void defineBlocks() {
        FuelRegistry.INSTANCE.add(CHARCOAL_BLOCK, 15200);
    }

    public static Set<ItemConvertible> getAllMinersDreamBlocks() {
        final Field[] fields = BlockDefinition.class.getDeclaredFields();
        final Set<ItemConvertible> items = new HashSet<>();

        for (Field field : fields) {
            try {
                final Object value = field.get(BlockDefinition.class);

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
