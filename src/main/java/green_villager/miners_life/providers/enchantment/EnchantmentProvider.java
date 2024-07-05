package green_villager.miners_life.providers.enchantment;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.resource.ConfigSchema;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EnchantmentProvider extends FabricDynamicRegistryProvider {
    private final FabricDataOutput dataOutput;
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;
    private final String enchantmentName;

    public EnchantmentProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        this(dataOutput, registryLookup, "explosive_mining");
    }

    protected EnchantmentProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup, String enchantmentName) {
        super(dataOutput, registryLookup);

        this.dataOutput = dataOutput;
        this.registryLookup = registryLookup;
        this.enchantmentName = enchantmentName;
    }

    @Override
    public String getName() {
        return "Enchantment";
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        try {
            final List<RegistryEntry<Item>> registryEntryList = new ArrayList<>();

            for (String item : MinersLife.DEFAULT_CONFIG.supportedItems) {
                registryEntryList.add(Registries.ITEM.getEntry(Identifier.of(item)).orElseThrow());
            }

            final RegistryWrapper.Impl<Enchantment> impl = registryLookup
                    .get()
                    .getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

            final Enchantment explosionMiningEnchantment = Enchantment
                    .builder(new Enchantment.Definition(RegistryEntryList.of(registryEntryList),
                            Optional.empty(),
                            2,
                            5,
                            Enchantment.leveledCost(15, 7),
                            Enchantment.leveledCost(65, 6),
                            4,
                            List.of(AttributeModifierSlot.MAINHAND)
                    ))
                    .exclusiveSet(impl.getOrThrow(EnchantmentTags.MINING_EXCLUSIVE_SET))
                    .addEffect(EnchantmentEffectComponentTypes.ITEM_DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(8)))
                    .build(MinersLife.getMinersLifeId(enchantmentName));

            final RegistryKey<Enchantment> key = RegistryKey.of(RegistryKeys.ENCHANTMENT, MinersLife.getMinersLifeId(enchantmentName));
            entries.add(key, explosionMiningEnchantment);

        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
