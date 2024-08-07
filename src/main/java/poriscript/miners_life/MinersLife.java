package poriscript.miners_life;

import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.callback.CallbackRegistration;
import poriscript.miners_life.data.enums.Identifiers;
import poriscript.miners_life.item.ItemRegistration;
import poriscript.miners_life.item_group.ItemGroupRegistration;
import poriscript.miners_life.world.WorldRegistration;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersLife implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(Identifiers.MOD_ID.getName());

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        BlockRegistration.defineBlocks();
        ItemRegistration.defineItems();
        ItemGroupRegistration.defineItemGroup();
        WorldRegistration.defineWorld();
        CallbackRegistration.defineCallback();
    }

    // Utilities
    public static String getName(Item item) {
        return item.getName().getString().toLowerCase()
                .replaceAll(".*\\.", "")
                .replaceAll(" ", "_");
    }

    public static String getName(Block block) {
        return block.getName().getString().toLowerCase()
                .replaceAll(".*\\.", "")
                .replaceAll(" ", "_");
    }
}