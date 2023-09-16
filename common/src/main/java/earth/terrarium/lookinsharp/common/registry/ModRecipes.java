package earth.terrarium.lookinsharp.common.registry;

import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeSerializer;
import com.teamresourceful.resourcefullib.common.recipe.CodecRecipeType;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.common.recipe.ForgingRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final ResourcefulRegistry<RecipeType<?>> RECIPE_TYPES = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_TYPE, LookinSharp.MOD_ID);
    public static final ResourcefulRegistry<RecipeSerializer<?>> RECIPE_SERIALIZERS = ResourcefulRegistries.create(BuiltInRegistries.RECIPE_SERIALIZER, LookinSharp.MOD_ID);

    public static final RegistryEntry<RecipeType<ForgingRecipe>> FORGING = RECIPE_TYPES.register("forging", () -> CodecRecipeType.of("forging"));
    public static final RegistryEntry<RecipeSerializer<?>> FORGING_SERIALIZER = RECIPE_SERIALIZERS.register("forging", () -> new CodecRecipeSerializer<>(FORGING.get(), ForgingRecipe::codec));
}
