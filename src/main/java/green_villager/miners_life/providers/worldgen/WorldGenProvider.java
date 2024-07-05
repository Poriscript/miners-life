package green_villager.miners_life.providers.worldgen;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.providers.tag.BlockTagProvider;
import green_villager.miners_life.world.WorldRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.WorldPresets;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends FabricDynamicRegistryProvider {
    public WorldGenProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        configureOreFeature(entries, BlockRegistration.MEATITE_ORE,
                new OreFeatureConfig(List.of(
                        OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTagProvider.MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD), BlockRegistration.MEATITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DEEPSLATE), BlockRegistration.MEATITE_ORE.getDefaultState())
                ), 8, 0),
                List.of(
                        CountPlacementModifier.of(16),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                        BiomePlacementModifier.of()
                ),
                WorldRegistration.MEATITE_ORE_PLACED_FEATURE_KEY);

        configureOreFeature(entries, BlockRegistration.MILKITE,
                new OreFeatureConfig(
                        new TagMatchRuleTest(BlockTagProvider.MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD),
                        BlockRegistration.MILKITE.getDefaultState(),
                        4,
                        0
                ),
                List.of(
                        CountPlacementModifier.of(16),
                        SquarePlacementModifier.of(),
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(256)),
                        BiomePlacementModifier.of()
                ),
                WorldRegistration.MILKITE_PLACED_FEATURE_KEY);

        configureEdibleVineFeature(entries);
    }

    private static void configureOreFeature(Entries entries, Block oreBlock, OreFeatureConfig oreFeatureConfig, List<PlacementModifier> placementModifier, RegistryKey<PlacedFeature> orePfKey) {
        final String itemName = MinersLife.getItemName(oreBlock.asItem());

        final ConfiguredFeature<OreFeatureConfig, Feature<OreFeatureConfig>> oreCf = new ConfiguredFeature<>(Feature.ORE, oreFeatureConfig);

        final RegistryKey<ConfiguredFeature<?, ?>> oreCfKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, MinersLife.getMinersLifeId(itemName + "_cf"));

        final RegistryEntry<ConfiguredFeature<?, ?>> oreFeatureRef = entries.add(oreCfKey, oreCf);

        final PlacedFeature orePf = new PlacedFeature(oreFeatureRef, placementModifier);

        entries.add(orePfKey, orePf);
    }

    private static void configureEdibleVineFeature(Entries entries) {
        final List<RegistryEntry<Block>> edibleVinePlaceable = List.of(
                Registries.BLOCK.getEntry(Blocks.ANDESITE),
                Registries.BLOCK.getEntry(Blocks.DIORITE),
                Registries.BLOCK.getEntry(Blocks.GRANITE),
                Registries.BLOCK.getEntry(Blocks.TUFF),
                Registries.BLOCK.getEntry(Blocks.DEEPSLATE)
        );

        final ConfiguredFeature<MultifaceGrowthFeatureConfig, Feature<MultifaceGrowthFeatureConfig>> edibleVineCF =
                new ConfiguredFeature<>(Feature.MULTIFACE_GROWTH, new MultifaceGrowthFeatureConfig(
                        (MultifaceGrowthBlock) BlockRegistration.EDIBLE_VINE,
                        32,
                        true,
                        true,
                        true,
                        0.5f,
                        RegistryEntryList.of(edibleVinePlaceable)
                ));

        final RegistryKey<ConfiguredFeature<?, ?>> edibleVineCFKey = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, MinersLife.getMinersLifeId("edible_vine_cf"));

        final RegistryEntry<ConfiguredFeature<?, ?>> edibleVineFeatureRef = entries.add(edibleVineCFKey, edibleVineCF);

        final PlacedFeature edibleVinePF = new PlacedFeature(edibleVineFeatureRef, List.of(
                CountPlacementModifier.of(UniformIntProvider.create(104, 157)),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                SquarePlacementModifier.of(),
                SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, -64, -13),
                BiomePlacementModifier.of()
        ));

        entries.add(WorldRegistration.EDIBLE_VINE_PLACED_FEATURE_KEY, edibleVinePF);
    }

    @Override
    public String getName() {
        return "WorldGen";
    }
}
