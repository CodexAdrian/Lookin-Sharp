package earth.terrarium.lookinsharp.compat.botarium.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.types.BuiltInSwordTypes;
import earth.terrarium.lookinsharp.common.items.BaseSword;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.compat.botarium.items.BotariumBaseSword;
import earth.terrarium.lookinsharp.compat.botarium.items.VitaliumMaterial;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class BotariumItems {
    public static final RegistryEntry<Item> VITALIUM_INGOT = ModItems.CRAFTING_COMPONENTS.register("vitalium_ingot", () -> new Item(new Item.Properties()));

    static {
        for (BuiltInSwordTypes type : BuiltInSwordTypes.VALUES) {
            var typeName = type.name().toLowerCase();
            ModItems.SWORDS.register("vitalium_" + typeName, () -> new BotariumBaseSword(VitaliumMaterial.INSTANCE, type, 3, -2.4f, new Item.Properties()));
        }
    }
}
