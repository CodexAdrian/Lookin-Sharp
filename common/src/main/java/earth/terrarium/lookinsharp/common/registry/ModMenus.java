package earth.terrarium.lookinsharp.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.common.menu.ForgingStationContainer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
    public static final ResourcefulRegistry<MenuType<?>> MENUS = ResourcefulRegistries.create(BuiltInRegistries.MENU, LookinSharp.MOD_ID);

    public static final RegistryEntry<MenuType<ForgingStationContainer>> FORGING_ANVIL = MENUS.register("forging_anvil", () -> new MenuType<>(ForgingStationContainer::new, FeatureFlags.VANILLA_SET));
}
