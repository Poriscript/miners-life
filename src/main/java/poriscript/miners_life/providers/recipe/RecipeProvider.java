package poriscript.miners_life.providers.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.item.ItemRegistration;
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

        createWoodyOreRecipes(exporter, BlockRegistration.CHARCOAL_FAMILY, BlockRegistration.CHARCOAL_BLOCK, Items.CHARCOAL);
        createWoodyOreRecipes(exporter, BlockRegistration.COAL_FAMILY, Blocks.COAL_BLOCK, Items.COAL);
        createWoodyOreRecipes(exporter, BlockRegistration.GOLD_FAMILY, Blocks.GOLD_BLOCK, Items.GOLD_ORE);
        createWoodyOreRecipes(exporter, BlockRegistration.DIAMOND_FAMILY, Blocks.DIAMOND_BLOCK, Items.DIAMOND);
        createWoodyOreRecipes(exporter, BlockRegistration.EMERALD_FAMILY, Blocks.EMERALD_BLOCK, Items.EMERALD);
        createWoodyOreRecipes(exporter, BlockRegistration.LAPIS_FAMILY, Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI);
        createWoodyOreRecipes(exporter, BlockRegistration.REDSTONE_FAMILY, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        createWoodyOreRecipes(exporter, BlockRegistration.NETHERITE_FAMILY, Blocks.NETHERITE_BLOCK, Items.ANCIENT_DEBRIS);

        //smoking
        createSmokingRecipes(exporter, RecipeCategory.FOOD, ItemRegistration.MEATITE, ItemRegistration.COOKED_MEATITE, 0.2f, 150);
        createSmokingRecipes(exporter, RecipeCategory.FOOD, ItemRegistration.VEGETABLITE, ItemRegistration.COOKED_VEGETABLITE, 0.2f, 150);
    }


    private void createReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        final String baseItemName = MinersLife.getItemName(baseItem.asItem());
        final String compactItemName = MinersLife.getItemName(compactItem.asItem());

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

    private static void createWoodyOreRecipes(RecipeExporter exporter, BlockFamily outputFamily, Block input, Item buttonMaterial) {
        outputFamily.getVariants().forEach((variant, outputBlock) -> {
            switch (variant) {
                case BUTTON -> offerCrossButtonRecipe(exporter, outputBlock, buttonMaterial);
                case SLAB -> offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, outputBlock, input);
                case STAIRS -> offerStairsRecipe(exporter, outputBlock, input);
                case TRAPDOOR -> offerTrapdoorRecipe(exporter, outputBlock, input);
                case FENCE -> offerFenceRecipe(exporter, outputBlock, input);
                case FENCE_GATE -> offerFenceGateRecipe(exporter, outputBlock, input);
                default ->
                        throw new RuntimeException(String.format("Recipe generation function of %s is undefined.", variant));
            }
        });
    }

    private static void offerCrossButtonRecipe(RecipeExporter exporter, Block output, Item material) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 5).input('#', material).pattern(" # ").pattern("###").pattern(" # ").criterion(hasItem(material), conditionsFromItem(material)).offerTo(exporter);
    }

    private static void offerStairsRecipe(RecipeExporter exporter, Block output, Block input) {
        createStairsRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerTrapdoorRecipe(RecipeExporter exporter, Block output, Block input) {
        createTrapdoorRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerFenceRecipe(RecipeExporter exporter, Block output, Block input) {
        createFenceRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }

    private static void offerFenceGateRecipe(RecipeExporter exporter, Block output, Block input) {
        createFenceGateRecipe(output, Ingredient.ofItems(input)).criterion(hasItem(input), conditionsFromItem(input)).offerTo(exporter);
    }
}
