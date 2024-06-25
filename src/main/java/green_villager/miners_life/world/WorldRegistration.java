package green_villager.miners_life.world;

import green_villager.miners_life.MinersLife;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class WorldRegistration {
    public static final RegistryKey<PlacedFeature> SULFUR_ORE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MinersLife.MOD_ID, "sulfur_ore_pf"));
    public static final RegistryKey<PlacedFeature> NITRE_ORE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MinersLife.MOD_ID, "nitre_ore_pf"));

    public static final RegistryKey<PlacedFeature> MILKITE_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MinersLife.MOD_ID, "milkite_pf"));

    public static void defineWorld() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, SULFUR_ORE_PLACED_FEATURE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, NITRE_ORE_PLACED_FEATURE_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MILKITE_FEATURE_KEY);
    }
}
