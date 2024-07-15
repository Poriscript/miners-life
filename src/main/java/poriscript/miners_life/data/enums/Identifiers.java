package poriscript.miners_life.data.enums;

import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import poriscript.miners_life.MinersLife;

public enum Identifiers {
    MOD_ID("miners_life"),
    REPLACEABLE_BLOCKS_OVERWORLD("miners_life_replaceable_blocks_overworld"),
    ROOT("root"),
    METAL("metal"),
    GEM("gem"),
    GOLD("gold"),
    NETHERITE("netherite"),

    MEATITE("meatite"),
    COOKED_MEATITE("cooked_meatite"),
    VEGETABLITE("vegetablite"),
    COOKED_VEGETABLITE("cooked_vegetablite"),

    MEATITE_ORE("meatite_ore"),
    VEGETABLITE_ORE("vegetablite_ore"),
    NETHER_BRICK_FENCE_GATE("nether_brick_fence_gate"),
    CHARCOAL_BLOCK("charcoal_block"),

    BLAST_MINING("blast_mining"),

    SAND_FROM_CRAFTING("sand_from_crafting");

    private final String name;
    private final Identifier id;

    Identifiers(String name) {
        this.name = name;
        this.id = Identifier.of("miners_life", name);
    }

    public String getName() {
        return name;
    }

    public Identifier getId() {
        return id;
    }

    public String getTranslationKey(TranslationKeyRoots root) {
        return String.format("%s.%s.%s", root.getName(), Identifiers.MOD_ID.getName(), name);
    }

    public static String getTranslationKey(Item item, TranslationKeyRoots root, BlockFamily.Variant variant) {
        return getTranslationKey(MinersLife.getName(item), root, variant);
    }

    public static String getTranslationKey(String baseName, TranslationKeyRoots root, BlockFamily.Variant variant) {
        return String.format("%s.%s.%s_%s", root.getName(), Identifiers.MOD_ID.getName(), baseName, variant.getName());
    }

    public String getAdvancementTranslationKey(ActionTypes actionType, boolean isTitle) {
        return String.format("%s.%s.%s_%s.%s", TranslationKeyRoots.Advancements.getName(), Identifiers.MOD_ID.getName(), actionType.getName(), name, isTitle ? "title" : "description");
    }

    public static String getAdvancementTranslationKey(Block block, ActionTypes actionType, boolean isTitle) {
        return String.format("%s.%s.%s_%s.%s", TranslationKeyRoots.Advancements.getName(), Identifiers.MOD_ID.getName(), actionType.getName(), MinersLife.getName(block), isTitle ? "title" : "description");
    }
}