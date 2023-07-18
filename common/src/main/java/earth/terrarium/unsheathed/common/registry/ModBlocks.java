package earth.terrarium.unsheathed.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import earth.terrarium.unsheathed.Unsheathed;
import earth.terrarium.unsheathed.common.block.ForgingStationBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public class ModBlocks {
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, Unsheathed.MOD_ID);

    public static final RegistryEntry<ForgingStationBlock> FORGING_ANVIL = BLOCKS.register("forging_station", ForgingStationBlock::new);
}
