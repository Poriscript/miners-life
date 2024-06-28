package green_villager.miners_life.providers.lang;

import com.google.gson.Gson;
import green_villager.miners_life.Enums;
import green_villager.miners_life.MinersLife;
import green_villager.miners_life.item_group.ItemGroupRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.nio.file.Files;
import java.util.List;

public class TranslationProviderFactory {
    public static void generate(FabricDataOutput dataOutput, FabricLanguageProvider.TranslationBuilder translationBuilder, Enums.Languages language_code) {
        try {
            final String json_str = Files.readString(dataOutput.getModContainer().findPath("assets/miners_life/translations.json").orElseThrow());
            final List<TranslationSchema.Translation> translations = new Gson().fromJson(json_str, TranslationSchema.class).translations;

            for (TranslationSchema.Translation translation : translations) {
                final String value = switch (language_code) {
                    case JA_JP -> translation.jaJp;
                    case EN_US -> translation.enUs;
                    default -> throw new RuntimeException("Unknown language code.");
                };

                translationBuilder.add(translation.key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read translation file!", e);
        }
    }
}
