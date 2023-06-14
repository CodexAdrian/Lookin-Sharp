package earth.terrarium.unsheathed.common.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class HighSpeedSwordItem extends EnhancedSwordItem {
    public HighSpeedSwordItem(Tier tier, Properties properties) {
        super(tier, 4, -3.2f, properties);
    }
}
