package green_villager.miners_dream.providers.lang;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TranslationProviderFactory {
    public static void generate(FabricDataOutput dataOutput, FabricLanguageProvider.TranslationBuilder translationBuilder, String language_code) {
        try {
            // build/datagenがルートディレクトリ。再生成の際はsrc/main/generated/../langとbuild/resources/main/../langのjsonファイルを削除して、2回runDatagenを行う
            String json_str = Files.readString(Paths.get("../resources/main/assets/miners_dream/translations.json"));

            Gson gson = new Gson();
            TranslationSchema json_obj = gson.fromJson(json_str, TranslationSchema.class);

            for (TranslationSchema.Translation translation : json_obj.translations) {
                translationBuilder.add(translation.key, getTranslatedValue(translation, language_code));
            }

            try {
                translationBuilder.add(dataOutput.getModContainer().findPath(String.format("assets/miners_dream/lang/%s.json", language_code)).orElseThrow());
            } catch (IOException e) {
                throw new RuntimeException("Failed to add the language file!", e);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read translation file!", e);
        }
    }

    private static String getTranslatedValue(TranslationSchema.Translation translation, String language_code) {
        return switch (language_code) {
            case "ja_jp" -> translation.jaJp;
            case "en_us" -> translation.enUs;
            default -> throw new RuntimeException("Unknown language code.");
        };
    }
}
