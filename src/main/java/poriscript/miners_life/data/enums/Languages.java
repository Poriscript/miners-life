package poriscript.miners_life.data.enums;

public enum Languages {
    JA_JP("ja_jp"),
    EN_US("en_us");

    private final String name;

    public String getName() {
        return name;
    }

    Languages(String name) {
        this.name = name;
    }
}
