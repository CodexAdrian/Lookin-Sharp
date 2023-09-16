package earth.terrarium.lookinsharp.datagen;

import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.datagen.provider.client.ModItemModelProvider;
import earth.terrarium.lookinsharp.datagen.provider.client.ModLangProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LookinSharp.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class UnsheathedDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator().getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Client
        event.getGenerator().addProvider(event.includeClient(), new ModLangProvider(generator));
        event.getGenerator().addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));

        // Server
    }
}
