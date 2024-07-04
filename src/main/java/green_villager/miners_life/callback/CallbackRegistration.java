package green_villager.miners_life.callback;

import green_villager.miners_life.callback.definition.BlockBreakCallback;
import green_villager.miners_life.callback.definition.EnchantmentRegisterCallback;

public class CallbackRegistration {
    public static void defineCallback() {
        BlockBreakCallback.register();
        EnchantmentRegisterCallback.register();
    }
}
