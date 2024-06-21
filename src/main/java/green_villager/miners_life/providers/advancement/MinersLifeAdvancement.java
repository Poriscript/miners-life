package green_villager.miners_life.providers.advancement;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockDefinition;
import green_villager.miners_life.item.ItemDefinition;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.function.Consumer;

public class MinersLifeAdvancement {

    private final Identifier BACKGROUND_ID = Identifier.of(Identifier.DEFAULT_NAMESPACE, "textures/gui/advancements/backgrounds/stone.png");

    public MinersLifeAdvancement(AdvancementProvider provider, RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {

        final String sulfur_name = "sulfur";
        final Item sulfur_item = ItemDefinition.getMinersDreamItem(sulfur_name);

        final String wet_meet_name = "wet_meet";
        final Item wet_meet_item = ItemDefinition.getMinersDreamItem(wet_meet_name);

        final String dried_meet_name = "dried_meet";
        final Item dried_meet_item = ItemDefinition.getMinersDreamItem(dried_meet_name);

        final String dried_meet_block_name = "dried_meet_block";
        final Item dried_meet_block_item = BlockDefinition.getMinersDreamBlock(dried_meet_block_name);

        final AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        Items.STONE,
                        Text.translatable(provider.getTranslationTitleKey("root")),
                        Text.translatable(provider.getTranslationDescriptionKey("root")),
                        BACKGROUND_ID,
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("begin_miners_life",  InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
                .build(consumer, String.format("%s:root", MinersLife.MOD_ID));

        AdvancementEntry getSulfurAdvancement = createAdvancement(provider, consumer, rootAdvancement, sulfur_item, wet_meet_name);
        AdvancementEntry getWetMeetAdvancement = createAdvancement(provider, consumer, getSulfurAdvancement, wet_meet_item, dried_meet_name);
        AdvancementEntry getDriedMeetAdvancement = createAdvancement(provider, consumer, getWetMeetAdvancement, dried_meet_item, dried_meet_block_name);
    }

    AdvancementEntry createAdvancement(AdvancementProvider provider, Consumer<AdvancementEntry> consumer, AdvancementEntry parent, Item icon, String release_item_name) {
        final String item_name = icon.getName().getString().replaceFirst(".*\\.", "");
        final String get_item_str = String.format("get_%s", item_name);

        return Advancement.Builder.create().parent(parent)
                .display(
                        icon,
                        Text.translatable(provider.getTranslationTitleKey(item_name)),
                        Text.translatable(provider.getTranslationDescriptionKey(item_name)),
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
}
