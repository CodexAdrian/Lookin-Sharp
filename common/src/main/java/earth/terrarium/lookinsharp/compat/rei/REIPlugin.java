package earth.terrarium.lookinsharp.compat.rei;

import earth.terrarium.lookinsharp.common.items.BaseSword;
import earth.terrarium.lookinsharp.common.recipe.ArtifactAttachmentRecipe;
import earth.terrarium.lookinsharp.common.recipe.ForgingRecipe;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.common.registry.ModRecipes;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.client.categories.DefaultSmithingCategory;
import me.shedaniel.rei.plugin.common.displays.DefaultSmithingDisplay;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class REIPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ForgingRecipeCategory());
        registry.addWorkstations(ForgingRecipeCategory.ID, EntryIngredients.of(ModItems.FORGING_ANVIL.get().getDefaultInstance()));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        Minecraft client = Minecraft.getInstance();
        assert client.level != null;
        RecipeManager recipeManager = client.level.getRecipeManager();
        for (ForgingRecipe recipe : recipeManager.getAllRecipesFor(ModRecipes.FORGING.get())) {
            for (ItemStack stack : recipe.results()) {
                registry.add(new ForgingRecipeCategory.FlattenedRecipe(recipe.input(), stack, CategoryIdentifier.of(new ResourceLocation("lookinsharp", "forging"))));
            }
        }
        registry.registerRecipeFiller(
                ArtifactAttachmentRecipe.class,
                RecipeType.SMITHING,
                attachmentRecipe -> new DefaultSmithingDisplay(
                        attachmentRecipe,
                        List.of(
                                EntryIngredients.ofIngredient(attachmentRecipe.artifactIngredient()),
                                EntryIngredients.ofItems(BuiltInRegistries.ITEM.stream().filter(item -> item instanceof BaseSword).collect(Collectors.toCollection(ArrayList::new))),
                                EntryIngredients.ofIngredient(attachmentRecipe.addonIngredient())
                        )
                )
        );
    }
}
