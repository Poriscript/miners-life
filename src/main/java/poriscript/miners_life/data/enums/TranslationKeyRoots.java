package poriscript.miners_life.data.enums;

public enum TranslationKeyRoots {
    Block("block"),
    Item("item"),
    ItemGroup("itemGroup"),
    Advancements("advancements"),
    Enchantment("enchantment");

    private final String name;

    public String getName() {
        return name;
    }

    TranslationKeyRoots(String name) {
        this.name = name;
    }
}
