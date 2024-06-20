package green_villager.miners_dream.providers.tag;

import green_villager.miners_dream.block.BlockDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
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

    private static final TagKey<Block> MINERS_DREAM = TagKey.of(RegistryKeys.BLOCK, Identifier.of("miners_dream:block"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        FabricTagProvider<Block>.FabricTagBuilder builder = getOrCreateTagBuilder(MINERS_DREAM);

        for (ItemConvertible block : BlockDefinition.getMinersDreamBlocks()){
            builder.add((Block) block).setReplace(false);
        }
    }
}
