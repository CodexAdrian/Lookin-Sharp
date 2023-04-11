package earth.terrarium.modid.common.registry;

import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeTab;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.modid.ModId;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public class ModItems {
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, ModId.MOD_ID);
    public static final Supplier<CreativeModeTab> TAB = new ResourcefulCreativeTab(new ResourceLocation(ModId.MOD_ID, "main"))
            .setItemIcon(() -> Blocks.DIRT)
            .addRegistry(ITEMS)
            .build();

//    public static final RegistryEntry<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties()));
}
