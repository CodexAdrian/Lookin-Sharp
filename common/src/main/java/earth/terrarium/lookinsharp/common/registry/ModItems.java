package earth.terrarium.lookinsharp.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.types.BuiltInSwordTypes;
import earth.terrarium.lookinsharp.common.items.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.Locale;

public class ModItems {
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, LookinSharp.MOD_ID);
    public static final ResourcefulRegistry<Item> CRAFTING_COMPONENTS = ResourcefulRegistries.create(ITEMS);
    public static final ResourcefulRegistry<Item> SWORDS = ResourcefulRegistries.create(ITEMS);
    public static final ResourcefulRegistry<CreativeModeTab> CREATIVE_TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, LookinSharp.MOD_ID);


    public static final RegistryEntry<CreativeModeTab> ITEM_GROUP = CREATIVE_TABS.register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.lookinsharp.main")).icon(() -> BuiltInRegistries.ITEM.get(new ResourceLocation(LookinSharp.MOD_ID, "diamond_sword")).getDefaultInstance()).build());

    public static final RegistryEntry<Item> FORGING_ANVIL = ITEMS.register("forging_station", () -> new BlockItem(ModBlocks.FORGING_ANVIL.get(), new Item.Properties()));

    //artifacts - withering, deadly, sticky, glacial, tonic, weakening, vampiric, battering
    public static final RegistryEntry<Item> WITHERING_ARTIFACT = CRAFTING_COMPONENTS.register("withering_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> DEADLY_ARTIFACT = CRAFTING_COMPONENTS.register("deadly_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> STICKY_ARTIFACT = CRAFTING_COMPONENTS.register("sticky_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> GLACIAL_ARTIFACT = CRAFTING_COMPONENTS.register("glacial_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> TONIC_ARTIFACT = CRAFTING_COMPONENTS.register("tonic_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> WEAKENING_ARTIFACT = CRAFTING_COMPONENTS.register("weakening_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> VAMPIRIC_ARTIFACT = CRAFTING_COMPONENTS.register("vampiric_artifact", () -> new Item(new Item.Properties()));
    public static final RegistryEntry<Item> BATTERING_ARTIFACT = CRAFTING_COMPONENTS.register("battering_artifact", () -> new Item(new Item.Properties()));

    static  {
        for (MaterialTypes value : MaterialTypes.VALUES) {
            var name = value.name().toLowerCase(Locale.ROOT);
            for (BuiltInSwordTypes type : BuiltInSwordTypes.VALUES) {
                var typeName = type.name().toLowerCase(Locale.ROOT);
                value.items.register(name + "_" + typeName, () -> new BaseSword(value.tier, type, 3, -2.4f, new Item.Properties()));
            }
        }
    }
}
