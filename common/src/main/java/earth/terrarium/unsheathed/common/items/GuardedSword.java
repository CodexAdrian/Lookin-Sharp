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

public class GuardedSword extends EnhancedSwordItem {
    public GuardedSword(Tier tier, Properties properties) {
        super(new WrappedTier.Builder(tier).maxUses(0.7f).build(), 2, -2.4f, properties);
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> attributes = super.getDefaultAttributeModifiers(slot);
        if (slot == EquipmentSlot.MAINHAND) {
            attributes.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier("Weapon modifier", 2, AttributeModifier.Operation.ADDITION));
            attributes.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier("Weapon modifier", .2, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return attributes;
    }
}
