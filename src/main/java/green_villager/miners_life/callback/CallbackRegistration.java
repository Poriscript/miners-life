package green_villager.miners_life.callback;

import green_villager.miners_life.callback.definition.BlockBreakCallback;

public class CallbackRegistration {
    public static void defineCallback() {
        BlockBreakCallback.register();
    }
}
