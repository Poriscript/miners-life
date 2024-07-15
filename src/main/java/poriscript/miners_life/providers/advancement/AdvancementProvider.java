package poriscript.miners_life.providers.advancement;

import poriscript.miners_life.data.enums.ActionTypes;
import poriscript.miners_life.MinersLife;
import poriscript.miners_life.data.enums.Identifiers;
import poriscript.miners_life.data.enums.TranslationKeyRoots;
import poriscript.miners_life.item.ItemRegistration;
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
                        Text.translatable(getTranslationTitleKey(Identifiers.ROOT.getName())),
                        Text.translatable(getTranslationDescriptionKey(Identifiers.ROOT.getName())),
                        BACKGROUND_ID,
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("begin_miners_life", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
                .build(Identifiers.ROOT.getId());

        final AdvancementEntry craftSandAdvancement = createAdvancement(rootAdvancement, Items.SAND,
                ActionTypes.Craft,
                RecipeUnlockedCriterion.create(Identifiers.SAND_FROM_CRAFTING.getId()),
                AdvancementRewards.Builder.experience(128).build());

        final AdvancementEntry getTNTAdvancement = createAdvancement(craftSandAdvancement, Items.TNT);

        final AdvancementEntry getMeatiteAdvancement = createAdvancement(rootAdvancement, ItemRegistration.MEATITE);
        final AdvancementEntry getVegetabliteAdvancement = createAdvancement(getMeatiteAdvancement, ItemRegistration.VEGETABLITE);
    }

    private AdvancementEntry createAdvancement(AdvancementEntry parent, Item icon) {
        return createAdvancement(parent, icon,
                ActionTypes.Get,
                InventoryChangedCriterion.Conditions.items(icon),
                AdvancementRewards.Builder.experience(128).build());
    }

    private AdvancementEntry createAdvancement(AdvancementEntry parent, Item icon, ActionTypes actionType, AdvancementCriterion<?> criterion, AdvancementRewards rewards) {
        final String actionStr = String.format("%s_%s", actionType.getName(), MinersLife.getName(icon));
        final Identifier id = Identifier.of(Identifiers.MOD_ID.getName(), actionStr);

        return Advancement.Builder.create().parent(parent)
                .display(
                        icon,
                        Text.translatable(getTranslationTitleKey(actionStr)),
                        Text.translatable(getTranslationDescriptionKey(actionStr)),
                        BACKGROUND_ID,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion(actionStr, criterion)
                .rewards(rewards)
                .build(id);
    }

    private String getTranslationTitleKey(String id) {
        return String.format("%s.%s.%s.title", TranslationKeyRoots.Advancements.getName(), Identifiers.MOD_ID.getName(), id);
    }

    private String getTranslationDescriptionKey(String id) {
        return String.format("%s.%s.%s.description", TranslationKeyRoots.Advancements.getName(), Identifiers.MOD_ID.getName(), id);
    }
}
