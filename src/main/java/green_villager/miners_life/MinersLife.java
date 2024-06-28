package green_villager.miners_life;

import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import green_villager.miners_life.item_group.ItemGroupRegistration;
import green_villager.miners_life.providers.loot_table.function.ExplosionAmplificationLootFunction;
import green_villager.miners_life.world.WorldRegistration;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersLife implements ModInitializer {
    public static final String MOD_ID = "miners_life";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final LootFunctionType<ExplosionAmplificationLootFunction> EXPLOSION_AMPLIFICATION_LOOT_FUNCTION_TYPE = Registry.register(Registries.LOOT_FUNCTION_TYPE, getMinersLifeId("explosion_amplification"), new LootFunctionType<>(ExplosionAmplificationLootFunction.CODEC));

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

    public static Identifier getMinersLifeId(String id) {
        return Identifier.of(MOD_ID, id);
    }
}