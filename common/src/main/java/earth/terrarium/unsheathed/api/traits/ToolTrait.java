package earth.terrarium.unsheathed.api.traits;

import com.google.common.collect.Multimap;
import earth.terrarium.unsheathed.api.rarities.ToolRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

public interface ToolTrait {
    int getWeight();
    void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity);
}
