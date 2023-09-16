package earth.terrarium.lookinsharp.api.traits;

import com.teamresourceful.resourcefullib.common.collections.WeightedCollection;
import earth.terrarium.lookinsharp.LookinSharp;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ToolTraitApi {
    private static final Map<ResourceLocation, ToolTrait> TRAIT_REGISTRY = new HashMap<>();
    private static final WeightedCollection<ToolTrait> TRAIT_POOL = new WeightedCollection<>();

    static {
        Arrays.stream(BuiltinTraits.values()).forEach(trait -> registerTrait(new ResourceLocation(LookinSharp.MOD_ID, trait.name().toLowerCase(Locale.ROOT)), trait));
    }

    @SuppressWarnings("UnusedReturnValue")
    public static ToolTrait registerTrait(ResourceLocation id, ToolTrait trait) {
        TRAIT_REGISTRY.put(id, trait);
        TRAIT_POOL.add(trait.getWeight(), trait);
        return trait;
    }

    public static ToolTrait getTrait(ResourceLocation id) {
        return TRAIT_REGISTRY.get(id);
    }

    public static ToolTrait rollTrait() {
        return TRAIT_POOL.next();
    }

    public static ResourceLocation getTraitId(ToolTrait trait) {
        return TRAIT_REGISTRY.entrySet().stream().filter(entry -> entry.getValue() == trait).findFirst().orElseThrow(() -> new IllegalStateException("Trait not registered")).getKey();
    }

    @Nullable
    public static ToolTrait fromItem(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("Trait") ? getTrait(new ResourceLocation(stack.getTag().getString("Trait"))) : null;
    }

    public static void setTrait(ItemStack stack, ToolTrait trait) {
        stack.getOrCreateTag().putString("Trait", getTraitId(trait).toString());
    }
}
