package green_villager.miners_life.providers.model;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.block.definition.MilkiteBlock;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.CHARCOAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.DRIED_MEET_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.SULFUR_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.DEEPSLATE_SULFUR_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.NITRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.DEEPSLATE_NITRE_ORE);

        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier
                .create(BlockRegistration.MILKITE)
                .with(When.create().set(MilkiteBlock.IS_MILK_FULL, false), BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of(MinersLife.MOD_ID, "block/milkite_empty")))
                .with(When.create().set(MilkiteBlock.IS_MILK_FULL, true), BlockStateVariant.create().put(VariantSettings.MODEL, Identifier.of(MinersLife.MOD_ID, "block/milkite"))));

        TexturedModel.CUBE_ALL.upload(BlockRegistration.MILKITE, blockStateModelGenerator.modelCollector);
        TexturedModel.makeFactory(block -> {
                    return TextureMap.all(Identifier.of(MinersLife.MOD_ID, "block/milkite_empty"));
                },
                Models.CUBE_ALL).upload(BlockRegistration.MILKITE, "_empty", blockStateModelGenerator.modelCollector);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistration.SULFUR, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.NITRE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.WET_MEET, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.DRIED_MEET, Models.GENERATED);
    }
}
