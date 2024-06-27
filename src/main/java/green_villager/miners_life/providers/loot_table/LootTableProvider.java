package green_villager.miners_life.providers.loot_table;

import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockRegistration.CHARCOAL_BLOCK);
        addDrop(BlockRegistration.DRIED_MEET_BLOCK);

        oreDrops(BlockRegistration.SULFUR_ORE, ItemRegistration.SULFUR);
        oreDrops(BlockRegistration.DEEPSLATE_SULFUR_ORE, ItemRegistration.SULFUR);
        oreDrops(BlockRegistration.NITRE_ORE, ItemRegistration.NITRE);
        oreDrops(BlockRegistration.DEEPSLATE_NITRE_ORE, ItemRegistration.NITRE);

        addDrop(BlockRegistration.MILKITE);

        addDropWithSilkTouch(BlockRegistration.MEATITE_ORE);

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
