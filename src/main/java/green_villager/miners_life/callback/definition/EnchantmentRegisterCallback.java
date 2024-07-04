package green_villager.miners_life.callback.definition;

import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.registry.Registries;

public class EnchantmentRegisterCallback {
    public static void register() {
        RegistryEntryAddedCallback.event(Registries.REGISTRIES).register((rawId, id, object) -> {
            System.out.println(rawId);
            System.out.println(id);
            System.out.println(object);

            int i = 1;
        });
    }
}
