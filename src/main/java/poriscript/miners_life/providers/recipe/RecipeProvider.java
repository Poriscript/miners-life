package poriscript.miners_life.providers.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import poriscript.miners_life.MinersLife;
import poriscript.miners_life.block.BlockRegistration;
import poriscript.miners_life.data.enums.Identifiers;
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

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //crafting
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockRegistration.NETHER_BRICK_FENCE_GATE, 3)
                .input('b', Blocks.NETHER_BRICKS)
                .input('i', Items.NETHER_BRICK)
                .pattern("ibi")
                .pattern("ibi")
                .criterion(hasItem(Items.NETHER_BRICK), conditionsFromItem(Items.NETHER_BRICK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.SAND, 2)
                .input(Items.SOUL_SAND)
                .input(Items.END_STONE)
                .criterion(FabricRecipeProvider.hasItem(Items.SOUL_SAND), FabricRecipeProvider.conditionsFromItem(Items.SOUL_SAND))
                .criterion(FabricRecipeProvider.hasItem(Items.END_STONE), FabricRecipeProvider.conditionsFromItem(Items.END_STONE))
                .offerTo(exporter, Identifiers.SAND_FROM_CRAFTING.getId());

        createReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.CHARCOAL, RecipeCategory.BUILDING_BLOCKS, BlockRegistration.CHARCOAL_BLOCK.asItem());

        createWoodyOreRecipes(exporter, BlockRegistration.CHARCOAL_FAMILY, BlockRegistration.CHARCOAL_BLOCK, Items.CHARCOAL);
        createWoodyOreRecipes(exporter, BlockRegistration.COAL_FAMILY, Blocks.COAL_BLOCK, Items.COAL);
        createWoodyOreRecipes(exporter, BlockRegistration.GOLD_FAMILY, Blocks.GOLD_BLOCK, Items.GOLD_INGOT);
        createWoodyOreRecipes(exporter, BlockRegistration.DIAMOND_FAMILY, Blocks.DIAMOND_BLOCK, Items.DIAMOND);
        createWoodyOreRecipes(exporter, BlockRegistration.EMERALD_FAMILY, Blocks.EMERALD_BLOCK, Items.EMERALD);
        createWoodyOreRecipes(exporter, BlockRegistration.LAPIS_FAMILY, Blocks.LAPIS_BLOCK, Items.LAPIS_LAZULI);
        createWoodyOreRecipes(exporter, BlockRegistration.REDSTONE_FAMILY, Blocks.REDSTONE_BLOCK, Items.REDSTONE);
        createWoodyOreRecipes(exporter, BlockRegistration.NETHERITE_FAMILY, Blocks.NETHERITE_BLOCK, Items.NETHERITE_INGOT);

        //smoking
        createSmokingRecipes(exporter, RecipeCategory.FOOD, ItemRegistration.MEATITE, ItemRegistration.COOKED_MEATITE, 0.2f, 150);
        createSmokingRecipes(exporter, RecipeCategory.FOOD, ItemRegistration.VEGETABLITE, ItemRegistration.COOKED_VEGETABLITE, 0.2f, 150);
    }


    private void createReversibleCompactingRecipes(RecipeExporter exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        final String baseItemName = MinersLife.getName(baseItem.asItem());
        final String compactItemName = MinersLife.getName(compactItem.asItem());

        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9)
                .input(compactItem)
                .criterion(FabricRecipeProvider.hasItem(compactItem), FabricRecipeProvider.conditionsFromItem(compactItem))
                .offerTo(exporter, Identifier.of(Identifiers.MOD_ID.getName(), String.format("%s_from_reversing_%s", baseItemName, compactItemName)));

        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem, 1)
                .input('#', baseItem)
                .pattern("###").pattern("###").pattern("###")
                .criterion(FabricRecipeProvider.hasItem(baseItem), FabricRecipeProvider.conditionsFromItem(baseItem))
                .offerTo(exporter, Identifier.of(Identifiers.MOD_ID.getName(), String.format("%s_from_compacting_%s", compactItemName, baseItemName)));
    }

    private void createSmokingRecipes(RecipeExporter exporter, RecipeCategory category, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(input), category, output, experience, cookingTime)
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, Identifier.of(Identifiers.MOD_ID.getName(), String.format("%s_from_smoking_%s", MinersLife.getName(output.asItem()), MinersLife.getName(input.asItem()))));
    }

    private static void createWoodyOreRecipes(RecipeExporter exporter, BlockFamily outputFamily, Block input, Item material) {
        outputFamily.getVariants().forEach((variant, outputBlock) -> {
            final TagKey<Item> baseItemTag = switch (variant) {
                case BUTTON -> ItemTags.BUTTONS;
                case SLAB -> ItemTags.SLABS;
                case STAIRS -> ItemTags.STAIRS;
                case TRAPDOOR -> ItemTags.TRAPDOORS;
                case FENCE -> ItemTags.FENCES;
                case FENCE_GATE -> ItemTags.FENCE_GATES;
                default -> throw new RuntimeException(String.format("%s to item tags is undefined.", variant));
            };

            ShapedRecipeJsonBuilder
                    .create(RecipeCategory.BUILDING_BLOCKS, outputBlock)
                    .input('m', material)
                    .input('b', baseItemTag)
                    .pattern("mbm")
                    .criterion(hasItem(material), conditionsFromItem(material))
                    .offerTo(exporter);

            offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, outputBlock, input, 9);
        });
    }
}
