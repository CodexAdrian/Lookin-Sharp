package earth.terrarium.lookinsharp.client.fabric;

import earth.terrarium.lookinsharp.client.LookinSharpClient;
import net.fabricmc.api.ClientModInitializer;

public class LookinSharpClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LookinSharpClient.init();
    }
}
