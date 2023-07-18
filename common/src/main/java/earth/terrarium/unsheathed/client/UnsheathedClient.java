package earth.terrarium.unsheathed.client;

import earth.terrarium.unsheathed.client.screen.ForgingAnvilScreen;
import earth.terrarium.unsheathed.common.registry.ModMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class UnsheathedClient {
    public static void init() {
        MenuScreens.register(ModMenus.FORGING_ANVIL.get(), ForgingAnvilScreen::new);
    }
}
