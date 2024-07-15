package poriscript.miners_life.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import poriscript.miners_life.data.enums.Identifiers;

public class WorldRegistration {
    public static final RegistryKey<PlacedFeature> MEATITE_ORE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifiers.MEATITE_ORE.getId());
    public static final RegistryKey<PlacedFeature> VEGETABLITE_ORE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifiers.VEGETABLITE_ORE.getId());

    public static void defineWorld() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MEATITE_ORE_PLACED_FEATURE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, VEGETABLITE_ORE_PLACED_FEATURE_KEY);
    }
}
