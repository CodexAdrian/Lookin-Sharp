package earth.terrarium.unsheathed;

import com.mojang.logging.LogUtils;
import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import earth.terrarium.unsheathed.common.config.UnsheathedConfig;
import earth.terrarium.unsheathed.common.registry.ModBlocks;
import earth.terrarium.unsheathed.common.registry.ModItems;
import org.slf4j.Logger;

public class Unsheathed {
    public static final String MOD_ID = "unsheathed";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Configurator CONFIGURATOR = new Configurator();

    public static void init() {
        CONFIGURATOR.registerConfig(UnsheathedConfig.class);
        ModBlocks.BLOCKS.init();
        ModItems.ITEMS.init();
    }

    public static void postInit() {
    }
}
