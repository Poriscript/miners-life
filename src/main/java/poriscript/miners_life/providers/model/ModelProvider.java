package poriscript.miners_life.providers.model;

import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.CHARCOAL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.MEATITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.VEGETABLITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistration.MEATITE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.COOKED_MEATITE, Models.GENERATED);

        itemModelGenerator.register(ItemRegistration.VEGETABLITE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.COOKED_VEGETABLITE, Models.GENERATED);
    }
}
