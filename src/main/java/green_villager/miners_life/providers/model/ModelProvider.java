package green_villager.miners_life.providers.model;

import green_villager.miners_life.block.BlockDefinition;
import green_villager.miners_life.item.ItemDefinition;
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
        blockStateModelGenerator.registerSimpleCubeAll(BlockDefinition.CHARCOAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockDefinition.DRIED_MEET_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemDefinition.SULFUR, Models.GENERATED);
        itemModelGenerator.register(ItemDefinition.WET_MEET, Models.GENERATED);
        itemModelGenerator.register(ItemDefinition.DRIED_MEET, Models.GENERATED);
    }
}
