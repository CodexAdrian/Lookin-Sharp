package earth.terrarium.lookinsharp;

import com.mojang.logging.LogUtils;
import com.teamresourceful.resourcefullib.common.utils.modinfo.ModInfoUtils;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.common.registry.*;
import earth.terrarium.lookinsharp.compat.botarium.BotariumCompat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class LookinSharp {
    public static final String MOD_ID = "lookinsharp";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final UUID BASE_RANGE = UUID.fromString("83393ebc-15e3-11ee-be56-0242ac120002");
    public static final UUID BASE_REACH = UUID.fromString("7f70f3f0-15e4-11ee-be56-0242ac120002");
    public static final UUID BASE_KNOCKBACK = UUID.fromString("83393ebc-15e3-11ee-be56-0242ac120002");
    public static final UUID BASE_MOVEMENT = UUID.fromString("9edf9516-15e4-11ee-be56-0242ac120002");
    public static final UUID BASE_TOUGHNESS = UUID.fromString("30ffe60a-15e3-11ee-be56-0242ac120002");

    public static final Map<Supplier<Item>, DropChance> DROPS = new HashMap<>();

    public static void init() {
        ModBlocks.BLOCKS.init();
        ModItems.ITEMS.init();
        ModItems.CREATIVE_TABS.init();
        ModRecipes.RECIPE_SERIALIZERS.init();
        ModRecipes.RECIPE_TYPES.init();
        ModMenus.MENUS.init();
        ToolRarityApi.rollRarity();
        ToolTraitApi.rollTrait();
        ModAbilities.init();

        DROPS.put(ModItems.BATTERING_ARTIFACT, new DropChance(EntityType.IRON_GOLEM, 0.35F));
        DROPS.put(ModItems.DEADLY_ARTIFACT, new DropChance(EntityType.CAVE_SPIDER, 0.05F));
        DROPS.put(ModItems.GLACIAL_ARTIFACT, new DropChance(EntityType.SNOW_GOLEM, 0.35F));
        DROPS.put(ModItems.STICKY_ARTIFACT, new DropChance(EntityType.SLIME, 0.05F));
        DROPS.put(ModItems.TONIC_ARTIFACT, new DropChance(EntityType.WITCH, 0.25F));
        DROPS.put(ModItems.WEAKENING_ARTIFACT, new DropChance(EntityType.HUSK, 0.10F));
        DROPS.put(ModItems.WITHERING_ARTIFACT, new DropChance(EntityType.WITHER_SKELETON, 0.10F));
        DROPS.put(ModItems.VAMPIRIC_ARTIFACT, new DropChance(EntityType.GHAST, 0.25F));

        if (ModInfoUtils.isModLoaded("botarium")) {
            BotariumCompat.init();
        }
    }

    public static void postInit() {
    }

    public record DropChance(EntityType<?> entityType, float chance) {
    }
}
