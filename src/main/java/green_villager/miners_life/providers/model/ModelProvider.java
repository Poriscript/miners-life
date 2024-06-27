package green_villager.miners_life.providers.model;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.block.definition.MilkiteBlock;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;

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

        Identifier milkite_id = blockStateModelGenerator.createSubModel(BlockRegistration.MILKITE, "", Models.CUBE_ALL, TextureMap::all);
        Identifier milkite_empty_id = blockStateModelGenerator.createSubModel(BlockRegistration.MILKITE, "_empty", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(BlockRegistration.MILKITE).coordinate(BlockStateModelGenerator.createBooleanModelMap(MilkiteBlock.IS_MILK_FULL, milkite_id, milkite_empty_id)));

        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.MEATITE_ORE);

        blockStateModelGenerator.registerWallPlant(BlockRegistration.EDIBLE_VINE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistration.SULFUR, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.NITRE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.WET_MEET, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.DRIED_MEET, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.MEATITE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.COOKED_MEATITE, Models.GENERATED);
    }
}
