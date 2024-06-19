package green_villager.miners_dream;

import green_villager.miners_dream.block.BlockDefinition;
import green_villager.miners_dream.item.ItemDefinition;
import green_villager.miners_dream.item_group.ItemGroupDefinition;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersDream implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("miners_dream");

    public static final String MOD_ID = "miners_dream";

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        BlockDefinition.DefineBlocks();
        ItemDefinition.DefineItems();
        ItemGroupDefinition.DefineItemGroup();

        UseItemCallback.EVENT.register((player, world, hand) -> {
            player.getHungerManager().addExhaustion(18);
            return TypedActionResult.pass(ItemStack.EMPTY);
        });

        Block block = Registries.BLOCK.get(Identifier.of(MOD_ID, "charcoal_block"));
        LOGGER.warn(block.getName().toString());
    }

    // Utilities
    public static int TickFrom(int seconds) {
        return seconds * 20;
    }
}

