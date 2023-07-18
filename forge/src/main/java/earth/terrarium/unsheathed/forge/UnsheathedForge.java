package earth.terrarium.unsheathed.forge;

import earth.terrarium.unsheathed.Unsheathed;
import earth.terrarium.unsheathed.api.rarities.ToolRarity;
import earth.terrarium.unsheathed.api.rarities.ToolRarityApi;
import earth.terrarium.unsheathed.api.traits.ToolTrait;
import earth.terrarium.unsheathed.api.traits.ToolTraitApi;
import earth.terrarium.unsheathed.client.forge.UnsheathedClientForge;
import earth.terrarium.unsheathed.client.UnsheathedClient;
import earth.terrarium.unsheathed.common.registry.ModItems;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
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
        bus.addListener(UnsheathedForge::onRegisterCreativeTabs);
        MinecraftForge.EVENT_BUS.addListener(UnsheathedForge::modifyAttributesEvent);
    }

    public static void commonSetup(FMLCommonSetupEvent event) {
        Unsheathed.postInit();
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        UnsheathedClient.init();
        UnsheathedClientForge.postInit();
    }

    public static void onRegisterCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if(event.getTab().equals(ModItems.ITEM_GROUP.get())) {
            ModItems.ITEMS.stream().forEach(event::accept);
        }
    }

    public static void modifyAttributesEvent(ItemAttributeModifierEvent event) {
        ToolRarity toolRarity = ToolRarityApi.fromItem(event.getItemStack());
        ToolTrait toolTrait = ToolTraitApi.fromItem(event.getItemStack());

        if (toolRarity != null && toolTrait != null) {
            toolTrait.modifyAttributes(event.getItemStack(), event.getSlotType(), event::addModifier, toolRarity);
        }
    }
}
