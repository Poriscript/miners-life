package poriscript.miners_life;

import poriscript.miners_life.providers.advancement.AdvancementProvider;
import poriscript.miners_life.providers.loot_table.LootTableProvider;
import poriscript.miners_life.providers.model.ModelProvider;
import poriscript.miners_life.providers.recipe.RecipeProvider;
import poriscript.miners_life.providers.tag.BlockTagProvider;
import poriscript.miners_life.providers.lang.EnglishLanguageProvider;
import poriscript.miners_life.providers.lang.JapaneseLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        //languages
        pack.addProvider(EnglishLanguageProvider::new);
        pack.addProvider(JapaneseLanguageProvider::new);
        //tags
        pack.addProvider(BlockTagProvider::new);
        //recipes
        pack.addProvider(RecipeProvider::new);
        //advancements
        pack.addProvider(AdvancementProvider::new);
        //models
        pack.addProvider(ModelProvider::new);
        //loot tables
        pack.addProvider(LootTableProvider::new);
    }
}
