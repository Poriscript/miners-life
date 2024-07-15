package poriscript.miners_life.providers.tag;

import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.tag.BlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.data.enums.Identifiers;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    private static final TagKey<Block> MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD = TagKey.of(RegistryKeys.BLOCK, Identifiers.REPLACEABLE_BLOCKS_OVERWORLD.getId());

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        final FabricTagProvider<Block>.FabricTagBuilder minersLifeReplaceableBlocksOverworldTagBuilder = getOrCreateTagBuilder(MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.ANDESITE);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.DIORITE);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.GRANITE);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.TUFF);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.GRAVEL);

        final FabricTagProvider<Block>.FabricTagBuilder fencesTagBuilder = getOrCreateTagBuilder(BlockTags.FENCES);
        final FabricTagProvider<Block>.FabricTagBuilder fenceGatesTagBuilder = getOrCreateTagBuilder(BlockTags.FENCE_GATES);
        final FabricTagProvider<Block>.FabricTagBuilder pickaxeMineableTagBuilder = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);

        fenceGatesTagBuilder.add(BlockRegistration.NETHER_BRICK_FENCE_GATE);

        for (BlockFamily family : BlockRegistration.getFamilies()) {
            fencesTagBuilder.add(family.getVariant(BlockFamily.Variant.FENCE));
            fenceGatesTagBuilder.add(family.getVariant(BlockFamily.Variant.FENCE_GATE));

            family.getVariants().forEach((variant, block) -> {
                pickaxeMineableTagBuilder.add(block);
            });
        }
    }
}
