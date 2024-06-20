package green_villager.miners_dream.providers.tag;

import green_villager.miners_dream.item.ItemDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    private static final TagKey<Item> MINERS_DREAM = TagKey.of(RegistryKeys.ITEM, Identifier.of("miners_dream:item"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        FabricTagProvider<Item>.FabricTagBuilder builder = getOrCreateTagBuilder(MINERS_DREAM);

        for (ItemConvertible item : ItemDefinition.getMinersDreamItems()){
            builder.add(item.asItem());
        }
    }
}
