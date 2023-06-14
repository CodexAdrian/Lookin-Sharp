package earth.terrarium.unsheathed.fabric;

import earth.terrarium.unsheathed.Unsheathed;
import net.fabricmc.api.ModInitializer;

public class ModIdFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Unsheathed.init();
    }
}
