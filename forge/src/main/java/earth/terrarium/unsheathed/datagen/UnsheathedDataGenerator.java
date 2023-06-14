package earth.terrarium.unsheathed.datagen;

import earth.terrarium.unsheathed.Unsheathed;
import earth.terrarium.unsheathed.datagen.provider.client.ModItemModelProvider;
import earth.terrarium.unsheathed.datagen.provider.client.ModLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Unsheathed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class UnsheathedDataGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        // Client
        generator.addProvider(event.includeClient(), new ModLangProvider(generator));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(generator, existingFileHelper));

        // Server
    }
}
