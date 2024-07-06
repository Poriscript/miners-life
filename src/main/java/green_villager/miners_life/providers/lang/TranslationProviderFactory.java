package green_villager.miners_life.providers.lang;

import green_villager.miners_life.Enums;
import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.*;

public class TranslationProviderFactory {
    private static final Set<Map<String, String>> translations = Builder.builder()
            .add(Enums.TranslationKeyRoots.Block, "charcoal_block", "木炭ブロック", "Block of Charcoal")
            .add(Enums.TranslationKeyRoots.Block, "meatite_ore", "肉鉱石", "Meatite Ore")
            .add(Enums.TranslationKeyRoots.Block, "vegetablite_ore", "野菜鉱石", "Vegetablite Ore")
            .add(Enums.TranslationKeyRoots.ItemGroup, MinersLife.MOD_ID, "採掘家の生活", "Miners Life")
            .add(Enums.TranslationKeyRoots.Item, "meatite", "肉石", "Meatite")
            .add(Enums.TranslationKeyRoots.Item, "cooked_meatite", "焼肉石", "Cooked Meatite")
            .add(Enums.TranslationKeyRoots.Item, "vegetablite", "野菜石", "Vegetablite")
            .add(Enums.TranslationKeyRoots.Item, "cooked_vegetablite", "焼野菜石", "Cooked Vegetablite")
            .add(Enums.TranslationKeyRoots.Advancements, "root.title", "採掘家の生活", "Miners Life")
            .add(Enums.TranslationKeyRoots.Advancements, "root.description", "掘る・焼く・集める", "Digging, Smelting, Collecting")
            .add(Enums.TranslationKeyRoots.Advancements, "craft_sand.title", "昇天させましょう", "Let them ascend.")
            .add(Enums.TranslationKeyRoots.Advancements, "craft_sand.description", "火薬を安全に取り扱うために必要です", "Required for safe handling of gunpowder.")
            .add(Enums.TranslationKeyRoots.Advancements, "get_tnt.title", "起爆・爆破・誘爆", "Exploding, Explosion, Induced Explosion")
            .add(Enums.TranslationKeyRoots.Advancements, "get_tnt.description", "コストを抑えて爆破採掘しましょう", "Keep costs low and blast mining.")
            .add(Enums.TranslationKeyRoots.Advancements, "get_vegetablite.title", "野菜の石", "Vegetable stone.")
            .add(Enums.TranslationKeyRoots.Advancements, "get_vegetablite.description", "ある鉱石を爆破してみましょう", "Let's blow up some ore.")
            .add(Enums.TranslationKeyRoots.Advancements, "get_meatite.title", "肉の石", "Meet stone.")
            .add(Enums.TranslationKeyRoots.Advancements, "get_meatite.description", "ある鉱石を爆破してみましょう", "Let's blow up some ore.")
            .add(Enums.TranslationKeyRoots.Enchantment, "blast_mining", "爆破採掘", "Blast Mining")
            .build();

    private static final String KEY = "key";
    private final Enums.Languages languageCode;

    public TranslationProviderFactory(Enums.Languages languageCode) {
        this.languageCode = languageCode;
    }

    public void generate(FabricDataOutput dataOutput, FabricLanguageProvider.TranslationBuilder translationBuilder) {
        try {
            for (Map<String, String> translation : translations) {
                final String value = switch (languageCode) {
                    case Ja_Jp -> translation.get(Enums.Languages.Ja_Jp.name().toLowerCase());
                    case En_Us -> translation.get(Enums.Languages.En_Us.name().toLowerCase());
                    default -> throw new RuntimeException("Unknown language code.");
                };

                translationBuilder.add(translation.get(KEY), value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read translation file!", e);
        }
    }

    private static class Builder {
        private final Set<Map<String, String>> translations = new HashSet<>();

        public static Builder builder() {
            return new Builder();
        }

        public Set<Map<String, String>> build() {
            return translations;
        }

        public Builder add(Enums.TranslationKeyRoots root, String key, String ja, String en) {
            final HashMap<String, String> translation = HashMap.newHashMap(3);
            translation.put(KEY, String.format("%s.%s.%s", root.name().toLowerCase(), MinersLife.MOD_ID, key));
            translation.put(Enums.Languages.Ja_Jp.name().toLowerCase(), ja);
            translation.put(Enums.Languages.En_Us.name().toLowerCase(), en);
            translations.add(translation);
            return this;
        }
    }
}
