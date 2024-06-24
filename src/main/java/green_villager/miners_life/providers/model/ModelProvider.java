package green_villager.miners_life.providers.model;

import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

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
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistration.SULFUR, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.NITRE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.WET_MEET, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.DRIED_MEET, Models.GENERATED);
    }
}
