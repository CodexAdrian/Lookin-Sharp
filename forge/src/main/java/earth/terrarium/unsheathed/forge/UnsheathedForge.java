package earth.terrarium.unsheathed.forge;

import earth.terrarium.unsheathed.Unsheathed;
import earth.terrarium.unsheathed.client.forge.UnsheathedClientForge;
import earth.terrarium.unsheathed.client.ModIdClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Unsheathed.MOD_ID)
public class UnsheathedForge {
    public UnsheathedForge() {
        Unsheathed.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(UnsheathedForge::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> UnsheathedClientForge::init);
        bus.addListener(UnsheathedForge::onClientSetup);
    }

    public static void commonSetup(FMLCommonSetupEvent event) {
        Unsheathed.postInit();
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        ModIdClient.init();
        UnsheathedClientForge.postInit();
    }
}
