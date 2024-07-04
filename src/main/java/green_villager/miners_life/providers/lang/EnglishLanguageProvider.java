package green_villager.miners_life.providers.lang;

import green_villager.miners_life.Enums;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnglishLanguageProvider extends FabricLanguageProvider {

    private static final Enums.Languages LANGUAGE_CODE = Enums.Languages.En_Us;

    public EnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, LANGUAGE_CODE.name().toLowerCase(), registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        new TranslationProviderFactory(LANGUAGE_CODE).generate(dataOutput, translationBuilder);
    }
}
