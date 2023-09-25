package earth.terrarium.lookinsharp.compat.jei;

import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.common.recipe.ArtifactAttachmentRecipe;
import earth.terrarium.lookinsharp.common.recipe.ForgingRecipe;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.common.registry.ModRecipes;
import earth.terrarium.lookinsharp.compat.jei.category.ArtifactAttachmentRecipeCategory;
import earth.terrarium.lookinsharp.compat.jei.category.ForgingRecipeCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    private static final ResourceLocation UID = new ResourceLocation(LookinSharp.MOD_ID, LookinSharp.MOD_ID);

    private static List<ForgingRecipeCategory.FlattenedRecipe> flatten(Collection<ForgingRecipe> recipes) {
        List<ForgingRecipeCategory.FlattenedRecipe> flattenedRecipes = new ArrayList<>();
        for (ForgingRecipe recipe : recipes) {
            for (ItemStack result : recipe.results()) {
                flattenedRecipes.add(new ForgingRecipeCategory.FlattenedRecipe(recipe.input(), result.copy()));
            }
        }
        return flattenedRecipes;
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ForgingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        registration.addRecipes(ForgingRecipeCategory.FORGING, flatten(recipeManager.getAllRecipesFor(ModRecipes.FORGING.get())));
        registration.addRecipes(RecipeTypes.SMITHING, recipeManager.getAllRecipesFor(RecipeType.SMITHING));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.FORGING_ANVIL.get()), ForgingRecipeCategory.FORGING);
    }
}
