package green_villager.miners_life.providers.recipe;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockDefinition;
import green_villager.miners_life.item.ItemDefinition;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        final Item charcoal_block = BlockDefinition.CHARCOAL_BLOCK.asItem();
        final Item sulfur = ItemDefinition.SULFUR;
        final Item wet_meet = ItemDefinition.WET_MEET;
        final Item dried_meet = ItemDefinition.DRIED_MEET;
        final Item dried_meet_block = BlockDefinition.DRIED_MEET_BLOCK.asItem();

        createReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.CHARCOAL, RecipeCategory.BUILDING_BLOCKS, charcoal_block);
        createReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, dried_meet, RecipeCategory.BUILDING_BLOCKS, dried_meet_block);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, wet_meet)
                .input(sulfur)
                .input(Items.ROTTEN_FLESH)
                .input(Items.WATER_BUCKET)
                .input(Items.COPPER_INGOT)
                .criterion(FabricRecipeProvider.hasItem(sulfur), FabricRecipeProvider.conditionsFromItem(sulfur))
                .offerTo(exporter);

        createSmeltingRecipes(exporter, RecipeCategory.MISC, Items.ROTTEN_FLESH, Items.CHARCOAL, 0.1f, 200);

        createSmokingRecipes(exporter, RecipeCategory.FOOD, wet_meet, dried_meet, 0.1f, 100);
    }

    private void createReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9)
                .input(compactItem)
                .criterion(FabricRecipeProvider.hasItem(compactItem), FabricRecipeProvider.conditionsFromItem(compactItem))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem, 1)
                .input('#', baseItem)
                .pattern("###").pattern("###").pattern("###")
                .criterion(FabricRecipeProvider.hasItem(baseItem), FabricRecipeProvider.conditionsFromItem(baseItem))
                .offerTo(exporter);
    }

    private void createSmeltingRecipes(RecipeExporter exporter, RecipeCategory category, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), category, output, experience, cookingTime)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, Identifier.of(MinersLife.MOD_ID, String.format("%s_from_smelting_%s", MinersLife.getItemName(output.asItem()), MinersLife.getItemName(input.asItem()))));
    }

    private void createSmokingRecipes(RecipeExporter exporter, RecipeCategory category, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), category, output, experience, cookingTime)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, Identifier.of(MinersLife.MOD_ID, String.format("%s_from_smoking_%s", MinersLife.getItemName(output.asItem()), MinersLife.getItemName(input.asItem()))));
    }
}
