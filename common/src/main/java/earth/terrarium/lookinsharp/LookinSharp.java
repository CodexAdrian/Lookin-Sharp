package earth.terrarium.lookinsharp;

import com.mojang.logging.LogUtils;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.common.registry.ModBlocks;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.common.registry.ModMenus;
import earth.terrarium.lookinsharp.common.registry.ModRecipes;
import org.slf4j.Logger;

import java.util.UUID;

public class LookinSharp {
    public static final String MOD_ID = "lookinsharp";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final UUID BASE_RANGE = UUID.fromString("83393ebc-15e3-11ee-be56-0242ac120002");
    public static final UUID BASE_REACH = UUID.fromString("7f70f3f0-15e4-11ee-be56-0242ac120002");
    public static final UUID BASE_KNOCKBACK = UUID.fromString("83393ebc-15e3-11ee-be56-0242ac120002");
    public static final UUID BASE_MOVEMENT = UUID.fromString("9edf9516-15e4-11ee-be56-0242ac120002");
    public static final UUID BASE_TOUGHNESS = UUID.fromString("30ffe60a-15e3-11ee-be56-0242ac120002");

    public static void init() {
        ModBlocks.BLOCKS.init();
        ModItems.ITEMS.init();
        ModItems.CREATIVE_TABS.init();
        ModRecipes.RECIPE_SERIALIZERS.init();
        ModRecipes.RECIPE_TYPES.init();
        ModMenus.MENUS.init();
        ToolRarityApi.rollRarity();
        ToolTraitApi.rollTrait();
    }

    public static void postInit() {
    }
}
