package earth.terrarium.lookinsharp.compat.jei.category;

import earth.terrarium.lookinsharp.common.items.BaseSword;
import earth.terrarium.lookinsharp.common.recipe.ArtifactAttachmentRecipe;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArtifactAttachmentRecipeCategory implements IRecipeCategory<SmithingRecipe> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("jei", "textures/jei/gui/gui_vanilla.png");
    private final IDrawable background;
    private final IDrawable icon;

    public ArtifactAttachmentRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(TEXTURE, 0, 168, 108, 18);
        icon = guiHelper.createDrawableItemStack(new ItemStack(Blocks.SMITHING_TABLE));
    }

    @Override
    public @NotNull RecipeType<SmithingRecipe> getRecipeType() {
        return RecipeTypes.SMITHING;
    }

    @Override
    public @NotNull Component getTitle() {
        return Blocks.SMITHING_TABLE.getName();
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
    public void setRecipe(IRecipeLayoutBuilder builder, SmithingRecipe recipe, IFocusGroup focuses) {
        if (!(recipe instanceof ArtifactAttachmentRecipe attachmentRecipe)) return;
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1)
                .addIngredients(attachmentRecipe.artifactIngredient());

        List<ItemStack> list = BuiltInRegistries.ITEM.stream().filter(item -> item instanceof BaseSword).map(ItemStack::new).toList();
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 1)
                .addItemStacks(list);

        builder.addSlot(RecipeIngredientRole.INPUT, 37, 1)
                .addIngredients(attachmentRecipe.addonIngredient());

        List<ItemStack> stacksWithAbility = new ArrayList<>(list).stream().map(stack -> {
            if (stack.getItem() instanceof BaseSword sword) {
                sword.setAbility(stack, attachmentRecipe.result());
            }
            return stack;
        }).toList();
        builder.addSlot(RecipeIngredientRole.OUTPUT, 91, 1)
                .addItemStacks(stacksWithAbility);
    }

    @Override
    public boolean isHandled(SmithingRecipe recipe) {
        return recipe instanceof ArtifactAttachmentRecipe;
    }
}
