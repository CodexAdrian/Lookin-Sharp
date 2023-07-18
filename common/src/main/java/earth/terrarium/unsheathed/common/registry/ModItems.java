package earth.terrarium.unsheathed.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.unsheathed.common.items.*;
import earth.terrarium.unsheathed.common.util.PlatformUtils;
import earth.terrarium.unsheathed.Unsheathed;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.Locale;

public class ModItems {
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, Unsheathed.MOD_ID);
    public static final ResourcefulRegistry<CreativeModeTab> CREATIVE_TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, Unsheathed.MOD_ID);

    public static final RegistryEntry<CreativeModeTab> ITEM_GROUP = CREATIVE_TABS.register("main", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.unsheathed.main")).icon(() -> BuiltInRegistries.ITEM.get(new ResourceLocation(Unsheathed.MOD_ID, "diamond_sword")).getDefaultInstance()).build());

    public static final RegistryEntry<Item> FORGING_ANVIL = ITEMS.register("forging_station", () -> new BlockItem(ModBlocks.FORGING_ANVIL.get(), new Item.Properties()));

    static  {
        for (SwordTypes value : SwordTypes.VALUES) {
            var name = value.name().toLowerCase(Locale.ROOT);
            value.items.register(name + "_sword", () -> new BaseSword(value.tier, 3, -2.4f, new Item.Properties()));
            value.items.register(name + "_broadsword", () -> new GuardedSwordItem(value.tier, new Item.Properties()));
            value.items.register(name + "_shortsword", () -> new HighSpeedSwordItem(value.tier, 3, -1.8f, new Item.Properties()));
            value.items.register(name + "_cutlass", () -> new BaseSword(value.tier,3, -2.0f, new Item.Properties()));
            value.items.register(name + "_honed_sword", () -> new LightSwordItem(value.tier, 3, -2.2f, new Item.Properties()));
            value.items.register(name + "_gladius", () -> new GladiusSword(value.tier, new Item.Properties()));
            value.items.register(name + "_cleaver", () -> new CleaverItem(value.tier, new Item.Properties()));
            value.items.register(name + "_greatsword", () -> new GreatswordItem(value.tier, new Item.Properties()));
            value.items.register(name + "_scythe", () -> new ScytheItem(value.tier, new Item.Properties()));
            value.items.register(name + "_spear", () -> new SpearItem(value.tier, new Item.Properties()));
        }
    }
}
