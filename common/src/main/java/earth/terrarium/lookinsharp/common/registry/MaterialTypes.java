package earth.terrarium.lookinsharp.common.registry;

import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public enum MaterialTypes {
    WOODEN(Tiers.WOOD),
    STONE(Tiers.STONE),
    IRON(Tiers.IRON),
    GOLDEN(Tiers.GOLD),
    DIAMOND(Tiers.DIAMOND),
    NETHERITE(Tiers.NETHERITE);

    public static final MaterialTypes[] VALUES = values();

    public final ResourcefulRegistry<Item> items;
    public final Tier tier;

    MaterialTypes(Tier tier) {
        this.items = ResourcefulRegistries.create(ModItems.SWORDS);
        this.tier = tier;
    }
}
