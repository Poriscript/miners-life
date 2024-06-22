package green_villager.miners_life.providers.advancement;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.item.ItemDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.datafixer.fix.ItemNameFix;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
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
    }

    private AdvancementEntry createAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry parent, Item icon) {
        final String get_item_str = String.format("get_%s", MinersLife.getItemName(icon));

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
                .criterion(get_item_str, InventoryChangedCriterion.Conditions.items(icon))
                .rewards(AdvancementRewards.Builder.experience(128))
                .build(consumer, String.format("%s:%s", MinersLife.MOD_ID, get_item_str));
    }

    private String getTranslationTitleKey(String id) {
        return String.format("advancements.miners_life.%s.title", id);
    }

    private String getTranslationDescriptionKey(String id) {
        return String.format("advancements.miners_life.%s.description", id);
    }
}
