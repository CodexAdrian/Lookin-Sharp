package earth.terrarium.lookinsharp.fabric;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.rarities.ToolRarity;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTrait;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class LookinSharpFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LookinSharp.init();
        ItemGroupEvents.modifyEntriesEvent(ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(LookinSharp.MOD_ID, "main"))).register(entries -> ModItems.ITEMS.stream().map(RegistryEntry::get).forEach(entries::accept));
        ModifyItemAttributeModifiersCallback.EVENT.register((stack, slot, attributeModifiers) -> {
            ToolRarity toolRarity = ToolRarityApi.fromItem(stack);
            ToolTrait toolTrait = ToolTraitApi.fromItem(stack);

            if (toolRarity != null && toolTrait != null) {
                toolTrait.modifyAttributes(stack, slot, attributeModifiers::put, toolRarity);
            }
        });
    }
}
