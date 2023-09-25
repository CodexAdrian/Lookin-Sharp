package earth.terrarium.lookinsharp.compat.jei.category;

import earth.terrarium.lookinsharp.common.registry.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class ForgingRecipeCategory implements IRecipeCategory<ForgingRecipeCategory.FlattenedRecipe> {

    public static final RecipeType<FlattenedRecipe> FORGING = new RecipeType<>(new ResourceLocation("forging"), FlattenedRecipe.class);
    private static final ResourceLocation TEXTURE = new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ForgingRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(TEXTURE, 0, 220, 82, 34);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ModItems.FORGING_ANVIL.get().getDefaultInstance());
    }

    @Override
    public RecipeType<FlattenedRecipe> getRecipeType() {
        return FORGING;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.lookinsharp.forging_station");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FlattenedRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 9).addIngredients(recipe.tag);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9).addItemStack(recipe.result);
    }

    public record FlattenedRecipe(Ingredient tag, ItemStack result) {
    }
}