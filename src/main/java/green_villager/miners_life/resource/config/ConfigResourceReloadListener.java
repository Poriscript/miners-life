package green_villager.miners_life.resource.config;

import com.google.gson.*;
import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

public class ConfigResourceReloadListener implements SimpleSynchronousResourceReloadListener {
    public ConfigResourceReloadListener() {

    }

    @Override
    public Identifier getFabricId() {
        return MinersLife.getMinersLifeId("config");
    }

    @Override
    public void reload(ResourceManager manager) {
        final String configFileName = "config.json";
        final Identifier configId = MinersLife.getMinersLifeId(configFileName);

        final Map<Identifier, Resource> resourceFiles = manager.findResources("", identifier -> {
            return Objects.equals(identifier, configId);
        });

        try (final BufferedReader reader = resourceFiles.get(configId).getReader()) {
            final ConfigSchema config = MinersLife.GSON.fromJson(reader, ConfigSchema.class);
            final File configFilePath = new File(FabricLoader.getInstance().getConfigDir().toFile(), String.format("%s.json", MinersLife.MOD_ID));

            if (!(configFilePath.exists() && configFilePath.isFile())) {
                //Create default config.
                //noinspection ResultOfMethodCallIgnored
                configFilePath.createNewFile();

                try (final PrintWriter writer = new PrintWriter(Files.newBufferedWriter(configFilePath.toPath()))) {
                    MinersLife.GSON.toJson(config, writer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
