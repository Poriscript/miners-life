package poriscript.miners_life.callback;

import poriscript.miners_life.callback.definition.BlockBreakCallback;

public class CallbackRegistration {
    public static void defineCallback() {
        BlockBreakCallback.register();
    }
}
