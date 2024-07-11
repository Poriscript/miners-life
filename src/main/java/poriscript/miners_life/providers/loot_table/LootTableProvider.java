package poriscript.miners_life.providers.loot_table;

import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockRegistration.CHARCOAL_BLOCK);
        addDrop(BlockRegistration.MEATITE_ORE, getModDefaultOreLootTableBuilder(BlockRegistration.MEATITE_ORE, ItemRegistration.MEATITE));
        addDrop(BlockRegistration.VEGETABLITE_ORE, getModDefaultOreLootTableBuilder(BlockRegistration.VEGETABLITE_ORE, ItemRegistration.VEGETABLITE));
    }

    private LootTable.Builder getModDefaultOreLootTableBuilder(Block silkTouchDropBlock, Item explosionDropItem) {
        return LootTable.builder().pool(LootPool.builder().with(AlternativeEntry.builder(
                ItemEntry.builder(silkTouchDropBlock).conditionally(createSilkTouchCondition()),
                ItemEntry.builder(explosionDropItem)
                        .apply(ApplyBonusLootFunction.oreDrops(registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE))))));
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
