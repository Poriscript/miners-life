package poriscript.miners_life.data.enums;

public enum ActionTypes {
    Root(Identifiers.ROOT.getName()),
    Get("get"),
    Craft("craft");

    private final String name;

    public String getName() {
        return name;
    }

    ActionTypes(String name) {
        this.name = name;
    }
}
