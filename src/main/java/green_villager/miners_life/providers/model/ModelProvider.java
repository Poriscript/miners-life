package green_villager.miners_life.providers.model;

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

        Identifier milkite_id = blockStateModelGenerator.createSubModel(BlockRegistration.MILKITE, "", Models.CUBE_ALL, TextureMap::all);
        Identifier milkite_empty_id = blockStateModelGenerator.createSubModel(BlockRegistration.MILKITE, "_empty", Models.CUBE_ALL, TextureMap::all);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(BlockRegistration.MILKITE).coordinate(BlockStateModelGenerator.createBooleanModelMap(MilkiteBlock.IS_MILK_FULL, milkite_id, milkite_empty_id)));

        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.MEATITE_ORE);

        blockStateModelGenerator.registerWallPlant(BlockRegistration.EDIBLE_VINE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistration.MEATITE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.COOKED_MEATITE, Models.GENERATED);
    }
}
