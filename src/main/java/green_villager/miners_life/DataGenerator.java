package green_villager.miners_life;

import green_villager.miners_life.providers.advancement.AdvancementProvider;
import green_villager.miners_life.providers.loot_table.LootTableProvider;
import green_villager.miners_life.providers.model.ModelProvider;
import green_villager.miners_life.providers.recipe.RecipeProvider;
import green_villager.miners_life.providers.tag.BlockTagProvider;
import green_villager.miners_life.providers.tag.ItemTagProvider;
import green_villager.miners_life.providers.lang.EnglishLanguageProvider;
import green_villager.miners_life.providers.lang.JapaneseLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        //languages
        pack.addProvider(EnglishLanguageProvider::new);
        pack.addProvider(JapaneseLanguageProvider::new);
        //tags
        pack.addProvider(ItemTagProvider::new);
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
