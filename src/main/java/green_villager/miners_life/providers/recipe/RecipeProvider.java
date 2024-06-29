package green_villager.miners_life.providers.recipe;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockRegistration;
import green_villager.miners_life.item.ItemRegistration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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

    public static final Identifier SAND_RECIPE_ID = MinersLife.getMinersLifeId("sand_from_crafting");

    @Override
    public void generate(RecipeExporter exporter) {
        //crafting
        createReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.CHARCOAL, RecipeCategory.BUILDING_BLOCKS, BlockRegistration.CHARCOAL_BLOCK.asItem());

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.SAND, 2)
                .input(Items.SOUL_SAND)
                .input(Items.END_STONE)
                .criterion(FabricRecipeProvider.hasItem(Items.SOUL_SAND), FabricRecipeProvider.conditionsFromItem(Items.SOUL_SAND))
                .criterion(FabricRecipeProvider.hasItem(Items.END_STONE), FabricRecipeProvider.conditionsFromItem(Items.END_STONE))
                .offerTo(exporter, SAND_RECIPE_ID);

        //smoking
        createSmokingRecipes(exporter, RecipeCategory.FOOD, ItemRegistration.MEATITE, ItemRegistration.COOKED_MEATITE, 0.2f, 150);
    }

    private void createReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        String baseItemName = MinersLife.getItemName(baseItem.asItem());
        String compactItemName = MinersLife.getItemName(compactItem.asItem());

        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9)
                .input(compactItem)
                .criterion(FabricRecipeProvider.hasItem(compactItem), FabricRecipeProvider.conditionsFromItem(compactItem))
                .offerTo(exporter, MinersLife.getMinersLifeId(String.format("%s_from_reversing_%s", baseItemName, compactItemName)));

        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem, 1)
                .input('#', baseItem)
                .pattern("###").pattern("###").pattern("###")
                .criterion(FabricRecipeProvider.hasItem(baseItem), FabricRecipeProvider.conditionsFromItem(baseItem))
                .offerTo(exporter, MinersLife.getMinersLifeId(String.format("%s_from_compacting_%s", compactItemName, baseItemName)));
    }

    private void createSmokingRecipes(RecipeExporter exporter, RecipeCategory category, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), category, output, experience, cookingTime)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, MinersLife.getMinersLifeId(String.format("%s_from_smoking_%s", MinersLife.getItemName(output.asItem()), MinersLife.getItemName(input.asItem()))));
    }
}
