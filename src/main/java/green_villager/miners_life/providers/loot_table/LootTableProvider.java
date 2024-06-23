package green_villager.miners_life.providers.loot_table;

import green_villager.miners_life.block.BlockDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockDefinition.CHARCOAL_BLOCK);
        addDrop(BlockDefinition.DRIED_MEET_BLOCK);

        addDrop(BlockDefinition.SULFUR_ORE);
        addDrop(BlockDefinition.DEEPSLATE_SULFUR_ORE);
        addDrop(BlockDefinition.NITRE_ORE);
        addDrop(BlockDefinition.DEEPSLATE_NITRE_ORE);
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
