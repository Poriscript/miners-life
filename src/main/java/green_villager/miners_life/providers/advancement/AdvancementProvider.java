package green_villager.miners_life.providers.advancement;

import green_villager.miners_life.providers.recipe.RecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {
    public AdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        MinersLifeAdvancement minersLifeAdvancement = new MinersLifeAdvancement(this, registryLookup, consumer);
    }

    String getTranslationTitleKey(String id) {
        return String.format("advancements.miners_life.recipes.%s.title", id);
    }

    String getTranslationDescriptionKey(String id) {
        return String.format("advancements.miners_life.recipes.%s.description", id);
    }
}
