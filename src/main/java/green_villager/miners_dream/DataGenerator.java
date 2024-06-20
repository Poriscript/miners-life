package green_villager.miners_dream;

import green_villager.miners_dream.providers.tag.BlockTagProvider;
import green_villager.miners_dream.providers.tag.ItemTagProvider;
import green_villager.miners_dream.providers.lang.EnglishLanguageProvider;
import green_villager.miners_dream.providers.lang.JapaneseLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(EnglishLanguageProvider::new);
        pack.addProvider(JapaneseLanguageProvider::new);

//        pack.addProvider(RecipeProvider::new);
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(BlockTagProvider::new);
    }
}
