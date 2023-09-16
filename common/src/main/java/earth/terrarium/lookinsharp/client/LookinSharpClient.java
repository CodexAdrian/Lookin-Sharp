package earth.terrarium.lookinsharp.client;

import earth.terrarium.lookinsharp.client.screen.ForgingAnvilScreen;
import earth.terrarium.lookinsharp.common.registry.ModMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class LookinSharpClient {
    public static void init() {
        MenuScreens.register(ModMenus.FORGING_ANVIL.get(), ForgingAnvilScreen::new);
    }
}
