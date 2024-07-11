package poriscript.miners_life.world;

import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.BlockRegistration;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class WorldRegistration {
    public static final RegistryKey<PlacedFeature> MEATITE_ORE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, MinersLife.getMinersLifeId(MinersLife.getBlockName(BlockRegistration.MEATITE_ORE)));
    public static final RegistryKey<PlacedFeature> VEGETABLITE_ORE_PLACED_FEATURE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, MinersLife.getMinersLifeId(MinersLife.getBlockName(BlockRegistration.VEGETABLITE_ORE)));

    public static void defineWorld() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MEATITE_ORE_PLACED_FEATURE_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, VEGETABLITE_ORE_PLACED_FEATURE_KEY);
    }
}
