package green_villager.miners_life;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.callback.CallbackRegistration;
import green_villager.miners_life.item.ItemRegistration;
import green_villager.miners_life.item_group.ItemGroupRegistration;
import green_villager.miners_life.resource.config.ConfigResourceReloadListener;
import green_villager.miners_life.world.WorldRegistration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinersLife implements ModInitializer {
    public static final String MOD_ID = "miners_life";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

//        ConfigSchema config = ConfigLoader.Load();

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ConfigResourceReloadListener());

        BlockRegistration.defineBlocks();
        ItemRegistration.defineItems();
        ItemGroupRegistration.defineItemGroup();
        WorldRegistration.defineWorld();
        CallbackRegistration.defineCallback();
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