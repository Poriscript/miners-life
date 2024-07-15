package poriscript.miners_life.providers.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import poriscript.miners_life.data.enums.Languages;

import java.util.concurrent.CompletableFuture;

public class JapaneseLanguageProvider extends FabricLanguageProvider {

    public static final Languages LANGUAGE_CODE = Languages.JA_JP;

    public JapaneseLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, LANGUAGE_CODE.name().toLowerCase(), registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        new TranslationProviderFactory(LANGUAGE_CODE).generate(translationBuilder);
    }
}
