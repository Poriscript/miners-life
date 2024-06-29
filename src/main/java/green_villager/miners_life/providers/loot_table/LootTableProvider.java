package green_villager.miners_life.providers.loot_table;

import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.LootConditionTypes;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.SequenceEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockRegistration.CHARCOAL_BLOCK);

        addDrop(BlockRegistration.MILKITE);

        addDropWithSilkTouch(BlockRegistration.MEATITE_ORE);

        addDrop(BlockRegistration.MEATITE_ORE, LootTable.builder().pool(LootPool.builder().with(AlternativeEntry.builder(
                ItemEntry.builder(BlockRegistration.MEATITE_ORE).conditionally(createSilkTouchCondition()),
                ItemEntry.builder(ItemRegistration.MEATITE)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1), true).conditionally(InvertedLootCondition.builder(SurvivesExplosionLootCondition.builder())))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1), true).conditionally(InvertedLootCondition.builder(SurvivesExplosionLootCondition.builder())))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1), true).conditionally(InvertedLootCondition.builder(SurvivesExplosionLootCondition.builder())))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1), true).conditionally(InvertedLootCondition.builder(SurvivesExplosionLootCondition.builder())))
                        .apply(ApplyBonusLootFunction.oreDrops(registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))))
        )));

        addDrop(BlockRegistration.EDIBLE_VINE,
                multifaceGrowthDrops(BlockRegistration.EDIBLE_VINE,
                        createWithShearsOrSilkTouchCondition()).randomSequenceId(Identifier.ofVanilla("blocks/glow_lichen")));
    }

    @Override
    public BlockLootTableGenerator withConditions(ResourceCondition... conditions) {
        return super.withConditions(conditions);
    }

    @Override
    public BiConsumer<RegistryKey<LootTable>, LootTable.Builder> withConditions(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter, ResourceCondition... conditions) {
        return super.withConditions(exporter, conditions);
    }
}
