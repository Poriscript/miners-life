package green_villager.miners_life.providers.tag;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.item.ItemRegistration;
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

    private static final TagKey<Item> MINERS_LIFE_ITEMS = TagKey.of(RegistryKeys.ITEM, MinersLife.getMinersLifeId("miners_life_items"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        FabricTagProvider<Item>.FabricTagBuilder builder = getOrCreateTagBuilder(MINERS_LIFE_ITEMS);

        for (ItemConvertible item : ItemRegistration.getAllMinersLifeItems()) {
            builder.add(item.asItem()).setReplace(false);
        }
    }
}
