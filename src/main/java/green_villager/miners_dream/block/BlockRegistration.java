package green_villager.miners_dream.block;

import green_villager.miners_dream.MinersDream;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockRegistration {
    public static Block register(Block new_block, String block_id) {
        final Identifier id = Identifier.of(MinersDream.MOD_ID, block_id);
        Registry.register(Registries.ITEM, id, new BlockItem(new_block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, id, new_block);
    }
}
