package earth.terrarium.lookinsharp.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.IngredientCodec;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipe;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.abilities.ToolAbility;
import earth.terrarium.lookinsharp.api.abilities.ToolAbilityManager;
import earth.terrarium.lookinsharp.common.items.BaseSword;
import earth.terrarium.lookinsharp.common.registry.MaterialTypes;
import earth.terrarium.lookinsharp.common.registry.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record ArtifactAttachmentRecipe(ResourceLocation id, Ingredient artifactIngredient, Ingredient addonIngredient, ToolAbility result) implements CodecRecipe<Container>, SmithingRecipe {
    public static Codec<? extends SmithingRecipe> codec(ResourceLocation id) {
        return RecordCodecBuilder.<ArtifactAttachmentRecipe>create(instance -> instance.group(
                RecordCodecBuilder.point(id),
                IngredientCodec.CODEC.fieldOf("artifact").forGetter(ArtifactAttachmentRecipe::artifactIngredient),
                IngredientCodec.CODEC.fieldOf("addon").forGetter(ArtifactAttachmentRecipe::addonIngredient),
                ToolAbilityManager.CODEC.fieldOf("result").forGetter(ArtifactAttachmentRecipe::result)
        ).apply(instance, ArtifactAttachmentRecipe::new));
    }

    @Override
    public boolean isTemplateIngredient(ItemStack itemStack) {
        return artifactIngredient.test(itemStack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack itemStack) {
        return itemStack.getItem() instanceof BaseSword;
    }

    @Override
    public boolean isAdditionIngredient(ItemStack itemStack) {
        return addonIngredient.test(itemStack);
    }

    @Override
    public boolean matches(Container container, Level level) {
        return artifactIngredient.test(container.getItem(0)) && container.getItem(1).getItem() instanceof BaseSword && addonIngredient.test(container.getItem(2));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull Container container, @NotNull RegistryAccess access) {
        ItemStack sword = container.getItem(1);
        ItemStack result = sword.copy();
        if (result.getItem() instanceof BaseSword swordItem) {
            swordItem.setAbility(result, this.result);
        }
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.ARTIFACT_ATTACHMENT_SERIALIZER.get();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return CodecRecipe.super.canCraftInDimensions(pWidth, pHeight);
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess access) {
        ItemStack stack = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(LookinSharp.MOD_ID, "iron_sword")));
        if (stack.getItem() instanceof BaseSword sword) {
            sword.setAbility(stack, result);
        }
        return stack;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
