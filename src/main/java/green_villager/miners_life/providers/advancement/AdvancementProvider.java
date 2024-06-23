package green_villager.miners_life.providers.advancement;

import green_villager.miners_life.Enums;
import green_villager.miners_life.MinersLife;
import green_villager.miners_life.item.ItemDefinition;
import green_villager.miners_life.providers.recipe.RecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {
    public AdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    private final Identifier BACKGROUND_ID = Identifier.of(Identifier.DEFAULT_NAMESPACE, "textures/gui/advancements/backgrounds/stone.png");

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {

        final AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        Items.STONE,
                        Text.translatable(getTranslationTitleKey("root")),
                        Text.translatable(getTranslationDescriptionKey("root")),
                        BACKGROUND_ID,
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("begin_miners_life", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
                .build(consumer, String.format("%s:root", MinersLife.MOD_ID));

        AdvancementEntry getSulfurAdvancement = createAdvancement(consumer, rootAdvancement, ItemDefinition.SULFUR);
        AdvancementEntry getWetMeetAdvancement = createAdvancement(consumer, getSulfurAdvancement, ItemDefinition.WET_MEET);
        AdvancementEntry getDriedMeetAdvancement = createAdvancement(consumer, getWetMeetAdvancement, ItemDefinition.DRIED_MEET);

        AdvancementEntry getNitreAdvancement = createAdvancement(consumer, rootAdvancement, ItemDefinition.NITRE);

        AdvancementEntry craftGunPowderAdvancement = createAdvancement(consumer, getNitreAdvancement, Items.GUNPOWDER,
                Enums.ActionTypes.Craft,
                RecipeUnlockedCriterion.create(RecipeProvider.GUNPOWDER_RECIPE_ID),
                AdvancementRewards.Builder.experience(128).build());

        AdvancementEntry craftSandAdvancement = createAdvancement(consumer, craftGunPowderAdvancement, Items.SAND,
                Enums.ActionTypes.Craft,
                RecipeUnlockedCriterion.create(RecipeProvider.SAND_RECIPE_ID),
                AdvancementRewards.Builder.experience(128).build());

        AdvancementEntry getTNTAdvancement = createAdvancement(consumer, craftSandAdvancement, Items.TNT);
    }

    private AdvancementEntry createAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry parent, Item icon) {
        return createAdvancement(consumer, parent, icon,
                Enums.ActionTypes.Get,
                InventoryChangedCriterion.Conditions.items(icon),
                AdvancementRewards.Builder.experience(128).build());
    }

    private AdvancementEntry createAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry parent, Item icon, Enums.ActionTypes actionType, AdvancementCriterion<?> criterion, AdvancementRewards rewards) {
        final String get_item_str = String.format("%s_%s", actionType.name().toLowerCase(), MinersLife.getItemName(icon));

        return Advancement.Builder.create().parent(parent)
                .display(
                        icon,
                        Text.translatable(getTranslationTitleKey(get_item_str)),
                        Text.translatable(getTranslationDescriptionKey(get_item_str)),
                        BACKGROUND_ID,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion(get_item_str, criterion)
                .rewards(rewards)
                .build(consumer, String.format("%s:%s", MinersLife.MOD_ID, get_item_str));
    }

    private String getTranslationTitleKey(String id) {
        return String.format("advancements.miners_life.%s.title", id);
    }

    private String getTranslationDescriptionKey(String id) {
        return String.format("advancements.miners_life.%s.description", id);
    }
}
