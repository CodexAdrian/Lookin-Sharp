package earth.terrarium.lookinsharp.forge;

import com.mojang.serialization.Codec;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.rarities.ToolRarity;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTrait;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.client.forge.LookinSharpClientForge;
import earth.terrarium.lookinsharp.client.LookinSharpClient;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.common.util.PlatformUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(LookinSharp.MOD_ID)
public class LookinSharpForge {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_TABLES = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, LookinSharp.MOD_ID);

    public static final RegistryObject<Codec<ArtifactLootModifier>> TEMPAD_LOOT_MODIFIER = LOOT_TABLES.register("artifact_drop", ArtifactLootModifier.CODEC);

    public LookinSharpForge() {
        LookinSharp.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        LOOT_TABLES.register(bus);
        bus.addListener(LookinSharpForge::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> LookinSharpClientForge::init);
        bus.addListener(LookinSharpForge::onClientSetup);
        bus.addListener(LookinSharpForge::onRegisterCreativeTabs);
        MinecraftForge.EVENT_BUS.addListener(LookinSharpForge::modifyAttributesEvent);
        MinecraftForge.EVENT_BUS.addListener((LivingDamageEvent event) -> PlatformUtils.onEntityHit(event.getEntity(), event.getSource(), event.getAmount()));
    }

    public static void commonSetup(FMLCommonSetupEvent event) {
        LookinSharp.postInit();
    }

    public static void onClientSetup(FMLClientSetupEvent event) {
        LookinSharpClient.init();
        LookinSharpClientForge.postInit();
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
