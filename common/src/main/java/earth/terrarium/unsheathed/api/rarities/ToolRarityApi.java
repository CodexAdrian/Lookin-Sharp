package earth.terrarium.unsheathed.api.rarities;

import com.teamresourceful.resourcefullib.common.collections.WeightedCollection;
import earth.terrarium.unsheathed.Unsheathed;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ToolRarityApi {
    private static final Map<ResourceLocation, ToolRarity> RARITY_REGISTRY = new HashMap<>();
    private static final WeightedCollection<ToolRarity> RARITY_POOL = new WeightedCollection<>();

    static {
        Arrays.stream(BuiltinRarities.values()).forEach(rarity -> registerRarity(new ResourceLocation(Unsheathed.MOD_ID, rarity.name().toLowerCase(Locale.ROOT)), rarity));
    }

    @SuppressWarnings("UnusedReturnValue")
    public static ToolRarity registerRarity(ResourceLocation id, ToolRarity rarity) {
        RARITY_REGISTRY.put(id, rarity);
        RARITY_POOL.add(rarity.getWeight(), rarity);
        return rarity;
    }

    public static ToolRarity getRarity(ResourceLocation id) {
        return RARITY_REGISTRY.get(id);
    }

    public static ToolRarity rollRarity() {
        return RARITY_POOL.next();
    }

    public static ResourceLocation getRarityId(ToolRarity rarity) {
        return RARITY_REGISTRY.entrySet().stream().filter(entry -> entry.getValue() == rarity).findFirst().orElseThrow(() -> new IllegalStateException("Rarity not registered")).getKey();
    }

    @Nullable
    public static ToolRarity fromItem(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("Rarity") ? getRarity(new ResourceLocation(stack.getTag().getString("Rarity"))) : null;
    }

    public static void setRarity(ItemStack stack, ToolRarity rarity) {
        stack.getOrCreateTag().putString("Rarity", getRarityId(rarity).toString());
    }
}
