package earth.terrarium.unsheathed.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.unsheathed.common.items.*;
import earth.terrarium.unsheathed.common.util.PlatformUtils;
import earth.terrarium.unsheathed.Unsheathed;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

import java.util.Locale;

public class ModItems {
    public static final CreativeModeTab ITEM_GROUP = PlatformUtils.createTab(new ResourceLocation(Unsheathed.MOD_ID, "main"), () -> new ItemStack(Registry.ITEM.get(new ResourceLocation(Unsheathed.MOD_ID, "diamond_sword"))));
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(Registry.ITEM, Unsheathed.MOD_ID);

    static  {
        for (Tiers value : Tiers.values()) {
            var name = value.name().toLowerCase(Locale.ROOT);
            name = name.equals("wood") ? "wooden" : name;
            ITEMS.register(name + "_sword", () -> new SwordItem(value, 3, -2.4f, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_broadsword", () -> new GuardedSword(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_cutlass", () -> new HighSpeedSwordItem(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_shortsword", () -> new RapierItem(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_gladius", () -> new GladiusSword(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_cleaver", () -> new CleaverItem(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_honed_sword", () -> new HighSpeedSwordItem(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_greatsword", () -> new GreatswordItem(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_scythe", () -> new ScytheItem(value, new Item.Properties().tab(ITEM_GROUP)));
            ITEMS.register(name + "_spear", () -> new SpearItem(value, new Item.Properties().tab(ITEM_GROUP)));
        }
    }
}
