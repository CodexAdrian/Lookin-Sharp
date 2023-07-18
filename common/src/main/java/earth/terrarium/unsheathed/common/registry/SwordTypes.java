package earth.terrarium.unsheathed.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public enum SwordTypes {
    WOODEN(Tiers.WOOD),
    STONE(Tiers.STONE),
    IRON(Tiers.IRON),
    GOLDEN(Tiers.GOLD),
    DIAMOND(Tiers.DIAMOND),
    NETHERITE(Tiers.NETHERITE);

    public static final SwordTypes[] VALUES = values();

    public final ResourcefulRegistry<Item> items;
    public final Tier tier;

    SwordTypes(Tier tier) {
        this.items = ResourcefulRegistries.create(ModItems.ITEMS);
        this.tier = tier;
    }
}
