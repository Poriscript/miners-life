package green_villager.miners_life;

import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import green_villager.miners_life.item_group.ItemGroupRegistration;
import green_villager.miners_life.world.WorldRegistration;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersLife implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("miners_life");

    public static final String MOD_ID = "miners_life";

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        BlockRegistration.defineBlocks();
        ItemRegistration.defineItems();
        ItemGroupRegistration.defineItemGroup();
        WorldRegistration.defineWorld();
    }

    // Utilities
    public static int getTickFromSeconds(int seconds) {
        return seconds * 20;
    }

    public static String getItemName(Item item) {
        return item.getName().getString().toLowerCase()
                .replaceAll(".*\\.", "")
                .replaceAll(" ", "_");
    }
}