package green_villager.miners_life.resource;

import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class ConfigResourceReloadListener implements SimpleSynchronousResourceReloadListener {
    public ConfigResourceReloadListener() {
    }

    @Override
    public Identifier getFabricId() {
        return MinersLife.getMinersLifeId("config");
    }

    @Override
    public void reload(ResourceManager manager) {
        final File configFilePath = new File(FabricLoader.getInstance().getConfigDir().toFile(), String.format("%s.json", MinersLife.MOD_ID));

        if (!(configFilePath.exists() && configFilePath.isFile())) {
            try {
                //Create default config.
                //noinspection ResultOfMethodCallIgnored
                configFilePath.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (final PrintWriter writer = new PrintWriter(Files.newBufferedWriter(configFilePath.toPath()))) {
                MinersLife.GSON.toJson(MinersLife.DEFAULT_CONFIG, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
