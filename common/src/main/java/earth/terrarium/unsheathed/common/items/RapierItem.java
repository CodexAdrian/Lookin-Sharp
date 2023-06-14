package earth.terrarium.unsheathed.common.items;

import com.google.common.collect.Multimap;
import earth.terrarium.unsheathed.api.WrappedTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class RapierItem extends SwordItem {
    public RapierItem(Tier tier, Properties properties) {
        super(new WrappedTier.Builder(tier).maxUses(0.65f).build(), 2, -4f, properties);
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> attributes = super.getDefaultAttributeModifiers(slot);
        if (slot == EquipmentSlot.MAINHAND) {
            attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier("Weapon modifier", .1, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return attributes;
    }
}
