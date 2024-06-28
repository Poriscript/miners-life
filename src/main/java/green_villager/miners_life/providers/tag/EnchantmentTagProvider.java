package green_villager.miners_life.providers.tag;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.enchantment.definition.ExplosiveMiningEnchantment;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class EnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
    public EnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public static final TagKey<Enchantment> EXPLOSION_EXCLUSIVE_SET = TagKey.of(RegistryKeys.ENCHANTMENT, MinersLife.getMinersLifeId("exclusive_set/explosion"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        final FabricTagProvider<Enchantment>.FabricTagBuilder builder = getOrCreateTagBuilder(EXPLOSION_EXCLUSIVE_SET);

        builder.add(Enchantments.SILK_TOUCH);
//        builder.add(ExplosiveMiningEnchantment.EXPLOSIVE_MINING);
    }
}
