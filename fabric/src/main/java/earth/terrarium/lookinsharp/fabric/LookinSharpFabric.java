package earth.terrarium.lookinsharp.fabric;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.rarities.ToolRarity;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTrait;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.common.util.PlatformUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
        LookinSharp.DROPS.forEach((itemSupplier, dropChance) -> {
            LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
                if (source.isBuiltin() && dropChance.entityType().getDefaultLootTable().equals(id)) {
                    LootPool.Builder poolBuilder = new LootPool.Builder()
                            .setRolls(ConstantValue.exactly(1))
                            .when(LootItemRandomChanceCondition.randomChance(dropChance.chance()))
                            .when(LootItemKilledByPlayerCondition.killedByPlayer())
                            .add(LootItem.lootTableItem(itemSupplier.get()))
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)).build());
                    tableBuilder.pool(poolBuilder.build());
                }
            });
        });
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(PlatformUtils::onEntityHit);
    }
}
