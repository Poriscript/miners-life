package poriscript.miners_life.providers.model;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.Identifier;
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
        registerFenceGateBlockModel(blockStateModelGenerator, TextureMap.all(Blocks.NETHER_BRICKS), BlockRegistration.NETHER_BRICK_FENCE_GATE);

        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.MEATITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockRegistration.VEGETABLITE_ORE);

        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.COAL_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.CHARCOAL_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.GOLD_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.DIAMOND_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.EMERALD_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.LAPIS_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.REDSTONE_FAMILY);
        registerMapBlockModels(blockStateModelGenerator, BlockRegistration.NETHERITE_FAMILY);
    }

    private void registerMapBlockModels(BlockStateModelGenerator blockStateModelGenerator, BlockFamily blockFamily) {
        blockFamily.getVariants().forEach((variant, block) -> {
            final Block baseBlock = blockFamily.getBaseBlock();
            final TextureMap textureMap = TextureMap.all(baseBlock);

            switch (variant) {
                case BUTTON -> registerButtonBlockModel(blockStateModelGenerator, textureMap, block);
                case SLAB -> registerSlabBlockModel(blockStateModelGenerator, textureMap, block, baseBlock);
                case STAIRS -> registerStairsBlockModel(blockStateModelGenerator, textureMap, block);
                case TRAPDOOR -> registerTrapdoor(blockStateModelGenerator, textureMap, block);
                case FENCE -> registerFenceBlockModel(blockStateModelGenerator, textureMap, block);
                case FENCE_GATE -> registerFenceGateBlockModel(blockStateModelGenerator, textureMap, block);
                default -> throw new RuntimeException(String.format("%s is undefined block family.", variant));
            }
        });
    }

    private void registerButtonBlockModel(BlockStateModelGenerator blockStateModelGenerator, TextureMap textureMap, Block buttonBlock) {
        final Identifier identifier1 = Models.BUTTON.upload(buttonBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier2 = Models.BUTTON_PRESSED.upload(buttonBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, identifier1, identifier2));
        final Identifier identifier3 = Models.BUTTON_INVENTORY.upload(buttonBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(buttonBlock, identifier3);
    }

    private void registerSlabBlockModel(BlockStateModelGenerator blockStateModelGenerator, TextureMap textureMap, Block slabBlock, Block baseBlock) {
        final Identifier identifier1 = Models.SLAB.upload(slabBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier2 = Models.SLAB_TOP.upload(slabBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slabBlock, identifier1, identifier2, ModelIds.getBlockModelId(baseBlock)));
        blockStateModelGenerator.registerParentedItemModel(slabBlock, identifier1);
    }

    private void registerStairsBlockModel(BlockStateModelGenerator blockStateModelGenerator, TextureMap textureMap, Block stairsBlock) {
        final Identifier identifier = Models.INNER_STAIRS.upload(stairsBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier2 = Models.STAIRS.upload(stairsBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier3 = Models.OUTER_STAIRS.upload(stairsBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairsBlock, identifier, identifier2, identifier3));
        blockStateModelGenerator.registerParentedItemModel(stairsBlock, identifier2);
    }

    public void registerTrapdoor(BlockStateModelGenerator blockStateModelGenerator, TextureMap textureMap, Block trapdoorBlock) {
        final Identifier identifier1 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_TOP.upload(trapdoorBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier2 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_BOTTOM.upload(trapdoorBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier3 = Models.TEMPLATE_ORIENTABLE_TRAPDOOR_OPEN.upload(trapdoorBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createOrientableTrapdoorBlockState(trapdoorBlock, identifier1, identifier2, identifier3));
        blockStateModelGenerator.registerParentedItemModel(trapdoorBlock, identifier2);
    }

    public void registerFenceBlockModel(BlockStateModelGenerator blockStateModelGenerator, TextureMap textureMap, Block fenceBlock) {
        final Identifier identifier1 = Models.FENCE_POST.upload(fenceBlock, textureMap, blockStateModelGenerator.modelCollector);
        final Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier1, identifier2));
        final Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(fenceBlock, identifier3);
    }

    public void registerFenceGateBlockModel(BlockStateModelGenerator blockStateModelGenerator, TextureMap textureMap, Block fenceGateBlock) {
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4, true));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemRegistration.MEATITE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.COOKED_MEATITE, Models.GENERATED);

        itemModelGenerator.register(ItemRegistration.VEGETABLITE, Models.GENERATED);
        itemModelGenerator.register(ItemRegistration.COOKED_VEGETABLITE, Models.GENERATED);
    }
}
