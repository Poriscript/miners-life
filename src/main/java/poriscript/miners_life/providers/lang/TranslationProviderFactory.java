package poriscript.miners_life.providers.lang;

import poriscript.miners_life.Enums;
import poriscript.miners_life.MinersLife;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.util.*;

public class TranslationProviderFactory {
    private static final Set<Map<String, String>> translations = Builder.builder()
            .add(Enums.TranslationKeyRoots.Block, "charcoal_block", "木炭ブロック", "Block of Charcoal")
            .add(Enums.TranslationKeyRoots.Block, "nether_brick_fence_gate", "ネザーレンガのフェンスゲート", "Nether Brick Fence Gate")
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
            .add(Enums.TranslationKeyRoots.Block, "charcoal_button", "木炭のボタン", "Charcoal Button")
            .add(Enums.TranslationKeyRoots.Block, "charcoal_fence", "木炭のフェンス", "Charcoal Fence")
            .add(Enums.TranslationKeyRoots.Block, "charcoal_fence_gate", "木炭のフェンスゲート", "Charcoal Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "charcoal_slab", "木炭のハーフブロック", "Charcoal Slab")
            .add(Enums.TranslationKeyRoots.Block, "charcoal_stairs", "木炭の階段", "Charcoal Stairs")
            .add(Enums.TranslationKeyRoots.Block, "charcoal_trapdoor", "木炭のトラップドア", "Charcoal Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "coal_button", "石炭のボタン", "Coal Button")
            .add(Enums.TranslationKeyRoots.Block, "coal_fence", "石炭のフェンス", "Coal Fence")
            .add(Enums.TranslationKeyRoots.Block, "coal_fence_gate", "石炭のフェンスゲート", "Coal Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "coal_slab", "石炭のハーフブロック", "Coal Slab")
            .add(Enums.TranslationKeyRoots.Block, "coal_stairs", "石炭の階段", "Coal Stairs")
            .add(Enums.TranslationKeyRoots.Block, "coal_trapdoor", "石炭のトラップドア", "Coal Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "gold_button", "金のボタン", "Gold Button")
            .add(Enums.TranslationKeyRoots.Block, "gold_fence", "金のフェンス", "Gold Fence")
            .add(Enums.TranslationKeyRoots.Block, "gold_fence_gate", "金のフェンスゲート", "Gold Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "gold_slab", "金のハーフブロック", "Gold Slab")
            .add(Enums.TranslationKeyRoots.Block, "gold_stairs", "金の階段", "Gold Stairs")
            .add(Enums.TranslationKeyRoots.Block, "gold_trapdoor", "金のトラップドア", "Gold Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "diamond_button", "ダイヤモンドのボタン", "Diamond Button")
            .add(Enums.TranslationKeyRoots.Block, "diamond_fence", "ダイヤモンドのフェンス", "Diamond Fence")
            .add(Enums.TranslationKeyRoots.Block, "diamond_fence_gate", "ダイヤモンドのフェンスゲート", "Diamond Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "diamond_slab", "ダイヤモンドのハーフブロック", "Diamond Slab")
            .add(Enums.TranslationKeyRoots.Block, "diamond_stairs", "ダイヤモンドの階段", "Diamond Stairs")
            .add(Enums.TranslationKeyRoots.Block, "diamond_trapdoor", "ダイヤモンドのトラップドア", "Diamond Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "emerald_button", "エメラルドのボタン", "Emerald Button")
            .add(Enums.TranslationKeyRoots.Block, "emerald_fence", "エメラルドのフェンス", "Emerald Fence")
            .add(Enums.TranslationKeyRoots.Block, "emerald_fence_gate", "エメラルドのフェンスゲート", "Emerald Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "emerald_slab", "エメラルドのハーフブロック", "Emerald Slab")
            .add(Enums.TranslationKeyRoots.Block, "emerald_stairs", "エメラルドの階段", "Emerald Stairs")
            .add(Enums.TranslationKeyRoots.Block, "emerald_trapdoor", "エメラルドのトラップドア", "Emerald Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "lapis_button", "ラピスラズリのボタン", "Lapis Button")
            .add(Enums.TranslationKeyRoots.Block, "lapis_fence", "ラピスラズリのフェンス", "Lapis Fence")
            .add(Enums.TranslationKeyRoots.Block, "lapis_fence_gate", "ラピスラズリのフェンスゲート", "Lapis Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "lapis_slab", "ラピスラズリのハーフブロック", "Lapis Slab")
            .add(Enums.TranslationKeyRoots.Block, "lapis_stairs", "ラピスラズリの階段", "Lapis Stairs")
            .add(Enums.TranslationKeyRoots.Block, "lapis_trapdoor", "ラピスラズリのトラップドア", "Lapis Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "netherite_button", "ネザライトのボタン", "Netherite Button")
            .add(Enums.TranslationKeyRoots.Block, "netherite_fence", "ネザライトのフェンス", "Netherite Fence")
            .add(Enums.TranslationKeyRoots.Block, "netherite_fence_gate", "ネザライトのフェンスゲート", "Netherite Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "netherite_slab", "ネザライトのハーフブロック", "Netherite Slab")
            .add(Enums.TranslationKeyRoots.Block, "netherite_stairs", "ネザライトの階段", "Netherite Stairs")
            .add(Enums.TranslationKeyRoots.Block, "netherite_trapdoor", "ネザライトのトラップドア", "Netherite Trapdoor")
            .add(Enums.TranslationKeyRoots.Block, "redstone_button", "レッドストーンのボタン", "Redstone Button")
            .add(Enums.TranslationKeyRoots.Block, "redstone_fence", "レッドストーンのフェンス", "Redstone Fence")
            .add(Enums.TranslationKeyRoots.Block, "redstone_fence_gate", "レッドストーンのフェンスゲート", "Redstone Fence Gate")
            .add(Enums.TranslationKeyRoots.Block, "redstone_slab", "レッドストーンのハーフブロック", "Redstone Slab")
            .add(Enums.TranslationKeyRoots.Block, "redstone_stairs", "レッドストーンの階段", "Redstone Stairs")
            .add(Enums.TranslationKeyRoots.Block, "redstone_trapdoor", "レッドストーンのトラップドア", "Redstone Trapdoor")
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
//                    default -> throw new RuntimeException("Unknown language code.");
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
