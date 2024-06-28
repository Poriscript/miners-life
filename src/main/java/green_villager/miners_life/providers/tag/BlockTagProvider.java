package green_villager.miners_life.providers.tag;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    private static final TagKey<Block> MINERS_LIFE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, MinersLife.getMinersLifeId("miners_life_blocks"));
    private static final TagKey<Block> MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD = TagKey.of(RegistryKeys.BLOCK, MinersLife.getMinersLifeId("miners_life_replaceable_blocks_overworld"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        final FabricTagProvider<Block>.FabricTagBuilder miners_life_blocks_tag_builder = getOrCreateTagBuilder(MINERS_LIFE_BLOCKS);

        for (ItemConvertible block : BlockRegistration.getAllMinersLifeBlocks()) {
            miners_life_blocks_tag_builder.add((Block) block).setReplace(false);
        }

        final FabricTagProvider<Block>.FabricTagBuilder miners_life_replaceable_blocks_overworld_tag_builder = getOrCreateTagBuilder(MINERS_LIFE_REPLACEABLE_BLOCKS_OVERWORLD);
        miners_life_replaceable_blocks_overworld_tag_builder.add(Blocks.ANDESITE);
        miners_life_replaceable_blocks_overworld_tag_builder.add(Blocks.DIORITE);
        miners_life_replaceable_blocks_overworld_tag_builder.add(Blocks.GRANITE);
        miners_life_replaceable_blocks_overworld_tag_builder.add(Blocks.GRAVEL);
        miners_life_replaceable_blocks_overworld_tag_builder.add(Blocks.TUFF);
    }
}
