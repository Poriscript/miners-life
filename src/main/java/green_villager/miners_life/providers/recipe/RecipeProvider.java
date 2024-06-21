package green_villager.miners_life.providers.recipe;

import green_villager.miners_life.MinersLife;
import green_villager.miners_life.block.BlockDefinition;
import green_villager.miners_life.item.ItemDefinition;
import green_villager.miners_life.providers.advancement.AdvancementProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.*;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        final Item charcoal_block = BlockDefinition.getMinersDreamBlock("charcoal_block");
        final Item sulfur = ItemDefinition.getMinersDreamItem("sulfur");
        final Item wet_meet = ItemDefinition.getMinersDreamItem("wet_meet");
        final Item dried_meet = ItemDefinition.getMinersDreamItem("dried_meet");
        final Item dried_meet_block = BlockDefinition.getMinersDreamBlock("dried_meet_block");

        offerCompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Items.CHARCOAL, charcoal_block);
        offerSmelting(exporter, List.of(Items.ROTTEN_FLESH), RecipeCategory.MISC, Items.CHARCOAL, 0.1f, 200, "coal");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, wet_meet)
                .input(sulfur)
                .input(Items.ROTTEN_FLESH)
                .input(Items.WATER_BUCKET)
                .input(Items.COPPER_INGOT)
                .criterion(FabricRecipeProvider.hasItem(sulfur), FabricRecipeProvider.conditionsFromItem(sulfur))
                .criterion(FabricRecipeProvider.hasItem(wet_meet), FabricRecipeProvider.conditionsFromItem(wet_meet))
                .offerTo(exporter);

        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, wet_meet, dried_meet, 0.1f);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.FOOD, dried_meet, RecipeCategory.BUILDING_BLOCKS, dried_meet_block);
    }
}
