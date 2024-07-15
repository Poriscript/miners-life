package poriscript.miners_life.providers.lang;

import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Items;
import poriscript.miners_life.data.enums.ActionTypes;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import poriscript.miners_life.data.enums.Identifiers;
import poriscript.miners_life.data.enums.Languages;
import poriscript.miners_life.data.enums.TranslationKeyRoots;

import java.util.*;

public class TranslationProviderFactory {
    private static final Set<Map<String, String>> translations = TranslationBuilder.builder()
            .add("肉石", "Meatite", Identifiers.MEATITE.getTranslationKey(TranslationKeyRoots.Item))
            .add("焼肉石", "Cooked Meatite", Identifiers.COOKED_MEATITE.getTranslationKey(TranslationKeyRoots.Item))
            .add("野菜石", "Vegetablite", Identifiers.VEGETABLITE.getTranslationKey(TranslationKeyRoots.Item))
            .add("焼野菜石", "Cooked Vegetablite", Identifiers.COOKED_VEGETABLITE.getTranslationKey(TranslationKeyRoots.Item))
            .add("肉鉱石", "Meatite Ore", Identifiers.MEATITE_ORE.getTranslationKey(TranslationKeyRoots.Block))
            .add("野菜鉱石", "Vegetablite Ore", Identifiers.VEGETABLITE_ORE.getTranslationKey(TranslationKeyRoots.Block))
            .add("ネザーレンガのフェンスゲート", "Nether Brick Fence Gate", Identifiers.NETHER_BRICK_FENCE_GATE.getTranslationKey(TranslationKeyRoots.Block))
            .add("木炭のフェンスゲート", "Charcoal Fence Gate", Identifiers.getTranslationKey(Items.CHARCOAL, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("木炭のフェンス", "Charcoal Fence", Identifiers.getTranslationKey(Items.CHARCOAL, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("木炭ブロック", "Block of Charcoal", Identifiers.CHARCOAL_BLOCK.getTranslationKey(TranslationKeyRoots.Block))
            .add("木炭のボタン", "Charcoal Button", Identifiers.getTranslationKey(Items.CHARCOAL, TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("木炭のハーフブロック", "Charcoal Slab", Identifiers.getTranslationKey(Items.CHARCOAL, TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("木炭の階段", "Charcoal Stairs", Identifiers.getTranslationKey(Items.CHARCOAL, TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("木炭のトラップドア", "Charcoal Trapdoor", Identifiers.getTranslationKey(Items.CHARCOAL, TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("石炭のフェンスゲート", "Coal Fence Gate", Identifiers.getTranslationKey(Items.COAL, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("石炭のフェンス", "Coal Fence", Identifiers.getTranslationKey(Items.COAL, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("石炭のボタン", "Coal Button", Identifiers.getTranslationKey(Items.COAL, TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("石炭のハーフブロック", "Coal Slab", Identifiers.getTranslationKey(Items.COAL, TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("石炭の階段", "Coal Stairs", Identifiers.getTranslationKey(Items.COAL, TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("石炭のトラップドア", "Coal Trapdoor", Identifiers.getTranslationKey(Items.COAL, TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("金のフェンスゲート", "Gold Fence Gate", Identifiers.getTranslationKey(Identifiers.GOLD.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("金のフェンス", "Gold Fence", Identifiers.getTranslationKey(Identifiers.GOLD.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("金のボタン", "Gold Button", Identifiers.getTranslationKey(Identifiers.GOLD.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("金のハーフブロック", "Gold Slab", Identifiers.getTranslationKey(Identifiers.GOLD.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("金の階段", "Gold Stairs", Identifiers.getTranslationKey(Identifiers.GOLD.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("金のトラップドア", "Gold Trapdoor", Identifiers.getTranslationKey(Identifiers.GOLD.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("ダイヤモンドのフェンスゲート", "Diamond Fence Gate", Identifiers.getTranslationKey(Items.DIAMOND, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("ダイヤモンドのフェンス", "Diamond Fence", Identifiers.getTranslationKey(Items.DIAMOND, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("ダイヤモンドのボタン", "Diamond Button", Identifiers.getTranslationKey(Items.DIAMOND, TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("ダイヤモンドのハーフブロック", "Diamond Slab", Identifiers.getTranslationKey(Items.DIAMOND, TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("ダイヤモンドの階段", "Diamond Stairs", Identifiers.getTranslationKey(Items.DIAMOND, TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("ダイヤモンドのトラップドア", "Diamond Trapdoor", Identifiers.getTranslationKey(Items.DIAMOND, TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("エメラルドのフェンスゲート", "Emerald Fence Gate", Identifiers.getTranslationKey(Items.EMERALD, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("エメラルドのフェンス", "Emerald Fence", Identifiers.getTranslationKey(Items.EMERALD, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("エメラルドのボタン", "Emerald Button", Identifiers.getTranslationKey(Items.EMERALD, TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("エメラルドのハーフブロック", "Emerald Slab", Identifiers.getTranslationKey(Items.EMERALD, TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("エメラルドの階段", "Emerald Stairs", Identifiers.getTranslationKey(Items.EMERALD, TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("エメラルドのトラップドア", "Emerald Trapdoor", Identifiers.getTranslationKey(Items.EMERALD, TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("ラピスラズリのフェンスゲート", "Lapis Lazuli Fence Gate", Identifiers.getTranslationKey(Items.LAPIS_LAZULI, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("ラピスラズリのフェンス", "Lapis Lazuli Fence", Identifiers.getTranslationKey(Items.LAPIS_LAZULI, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("ラピスラズリのボタン", "Lapis Lazuli Button", Identifiers.getTranslationKey(Items.LAPIS_LAZULI, TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("ラピスラズリのハーフブロック", "Lapis Lazuli Slab", Identifiers.getTranslationKey(Items.LAPIS_LAZULI, TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("ラピスラズリの階段", "Lapis Lazuli Stairs", Identifiers.getTranslationKey(Items.LAPIS_LAZULI, TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("ラピスラズリのトラップドア", "Lapis Lazuli Trapdoor", Identifiers.getTranslationKey(Items.LAPIS_LAZULI, TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("ネザライトのフェンスゲート", "Netherite Fence Gate", Identifiers.getTranslationKey(Identifiers.NETHERITE.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("ネザライトのフェンス", "Netherite Fence", Identifiers.getTranslationKey(Identifiers.NETHERITE.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("ネザライトのボタン", "Netherite Button", Identifiers.getTranslationKey(Identifiers.NETHERITE.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("ネザライトのハーフブロック", "Netherite Slab", Identifiers.getTranslationKey(Identifiers.NETHERITE.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("ネザライトの階段", "Netherite Stairs", Identifiers.getTranslationKey(Identifiers.NETHERITE.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("ネザライトのトラップドア", "Netherite Trapdoor", Identifiers.getTranslationKey(Identifiers.NETHERITE.getName(), TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("レッドストーンのフェンスゲート", "Redstone Fence Gate", Identifiers.getTranslationKey(Items.REDSTONE, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE_GATE))
            .add("レッドストーンのフェンス", "Redstone Fence", Identifiers.getTranslationKey(Items.REDSTONE, TranslationKeyRoots.Block, BlockFamily.Variant.FENCE))
            .add("レッドストーンのボタン", "Redstone Button", Identifiers.getTranslationKey(Items.REDSTONE, TranslationKeyRoots.Block, BlockFamily.Variant.BUTTON))
            .add("レッドストーンのトラップドア", "Redstone Trapdoor", Identifiers.getTranslationKey(Items.REDSTONE, TranslationKeyRoots.Block, BlockFamily.Variant.TRAPDOOR))
            .add("レッドストーンのハーフブロック", "Redstone Slab", Identifiers.getTranslationKey(Items.REDSTONE, TranslationKeyRoots.Block, BlockFamily.Variant.SLAB))
            .add("レッドストーンの階段", "Redstone Stairs", Identifiers.getTranslationKey(Items.REDSTONE, TranslationKeyRoots.Block, BlockFamily.Variant.STAIRS))
            .add("採掘家の生活", "Miners Life", Identifiers.ROOT.getAdvancementTranslationKey(ActionTypes.Root, true))
            .add("掘る・焼く・集める", "Digging, Smelting, Collecting", Identifiers.ROOT.getAdvancementTranslationKey(ActionTypes.Root, false))
            .add("昇天させましょう", "Let them ascend.", Identifiers.getAdvancementTranslationKey(Blocks.SAND, ActionTypes.Get, true))
            .add("火薬を安全に取り扱うために必要です", "Required for safe handling of gunpowder.", Identifiers.getAdvancementTranslationKey(Blocks.SAND, ActionTypes.Get, false))
            .add("起爆・爆破・誘爆", "Exploding, Explosion, Induced Explosion", Identifiers.getAdvancementTranslationKey(Blocks.TNT, ActionTypes.Get, true))
            .add("コストを抑えて爆破採掘しましょう", "Keep costs low and blast mining.", Identifiers.getAdvancementTranslationKey(Blocks.TNT, ActionTypes.Get, false))
            .add("野菜の石", "Vegetable stone.", Identifiers.VEGETABLITE.getAdvancementTranslationKey(ActionTypes.Get, true))
            .add("ある鉱石を爆破してみましょう", "Let's blow up some ore.", Identifiers.VEGETABLITE.getAdvancementTranslationKey(ActionTypes.Get, false))
            .add("肉の石", "Meet stone.", Identifiers.MEATITE.getAdvancementTranslationKey(ActionTypes.Get, true))
            .add("ある鉱石を爆破してみましょう", "Let's blow up some ore.", Identifiers.MEATITE.getAdvancementTranslationKey(ActionTypes.Get, false))
            .add("爆破採掘", "Blast Mining", Identifiers.BLAST_MINING.getTranslationKey(TranslationKeyRoots.Enchantment))
            .add("採掘家の生活", "Miners Life", Identifiers.MOD_ID.getTranslationKey(TranslationKeyRoots.ItemGroup))
            .build();

    private static final String KEY = "key";
    private final Languages languageCode;

    public TranslationProviderFactory(Languages languageCode) {
        this.languageCode = languageCode;
    }

    public void generate(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        for (Map<String, String> translation : translations) {
            final String value = switch (languageCode) {
                case JA_JP -> translation.get(Languages.JA_JP.getName());
                case EN_US -> translation.get(Languages.EN_US.getName());
//                    default -> throw new RuntimeException("Unknown language code.");
            };

            translationBuilder.add(translation.get(KEY), value);
        }
    }

    private static class TranslationBuilder {
        private final Set<Map<String, String>> translations = new HashSet<>();

        public static TranslationBuilder builder() {
            return new TranslationBuilder();
        }

        public Set<Map<String, String>> build() {
            return translations;
        }

        public TranslationBuilder add(String ja, String en, String key) {
            final HashMap<String, String> translation = HashMap.newHashMap(3);
            translation.put(KEY, key);
            translation.put(Languages.JA_JP.getName(), ja);
            translation.put(Languages.EN_US.getName(), en);
            translations.add(translation);
            return this;
        }
    }
}
