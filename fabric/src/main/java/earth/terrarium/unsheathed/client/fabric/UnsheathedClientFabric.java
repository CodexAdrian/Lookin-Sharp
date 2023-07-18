package earth.terrarium.unsheathed.client.fabric;

import earth.terrarium.unsheathed.client.UnsheathedClient;
import net.fabricmc.api.ClientModInitializer;

public class UnsheathedClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UnsheathedClient.init();
    }
}
