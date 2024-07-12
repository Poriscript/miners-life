package poriscript.miners_life.providers.tag;

import net.minecraft.data.family.BlockFamily;
import net.minecraft.registry.tag.BlockTags;
import poriscript.miners_life.MinersLife;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import poriscript.miners_life.block.BlockRegistration;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    private static final TagKey<Block> MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD = TagKey.of(RegistryKeys.BLOCK, MinersLife.getMinersLifeId("miners_life_replaceable_blocks_overworld"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        final FabricTagProvider<Block>.FabricTagBuilder minersLifeReplaceableBlocksOverworldTagBuilder = getOrCreateTagBuilder(MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.ANDESITE);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.DIORITE);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.GRANITE);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.TUFF);
        minersLifeReplaceableBlocksOverworldTagBuilder.add(Blocks.GRAVEL);

        final FabricTagProvider<Block>.FabricTagBuilder fencesTagBuilder = getOrCreateTagBuilder(BlockTags.FENCES);
        fencesTagBuilder.add(BlockRegistration.CHARCOAL_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.COAL_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.GOLD_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.DIAMOND_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.EMERALD_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.LAPIS_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.REDSTONE_FAMILY.getVariant(BlockFamily.Variant.FENCE));
        fencesTagBuilder.add(BlockRegistration.NETHERITE_FAMILY.getVariant(BlockFamily.Variant.FENCE));

        final FabricTagProvider<Block>.FabricTagBuilder pickaxeMineableTagBuilder = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);
        BlockRegistration.CHARCOAL_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.COAL_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.GOLD_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.DIAMOND_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.EMERALD_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.LAPIS_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.REDSTONE_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));
        BlockRegistration.NETHERITE_FAMILY.getVariants().forEach((variant, block) -> pickaxeMineableTagBuilder.add(block));

    }
}
