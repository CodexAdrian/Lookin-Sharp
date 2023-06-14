package earth.terrarium.unsheathed.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import earth.terrarium.unsheathed.api.WrappedTier;
import earth.terrarium.unsheathed.common.util.PlatformUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class SpearItem extends EnhancedSwordItem {
    public SpearItem(Tier tier, Properties properties) {
        super(new WrappedTier.Builder(tier).maxUses(0.85f).build(), 3, -2.4f, properties);
        this.attackDamage = (float)3 + tier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)f, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> attributes = super.getDefaultAttributeModifiers(slot);
        if (slot == EquipmentSlot.MAINHAND) {
            attributes.put(PlatformUtils.getAttackRangeAttribute(), new AttributeModifier("Weapon modifier", 2, AttributeModifier.Operation.ADDITION));
            attributes.put(PlatformUtils.getReachAttribute(), new AttributeModifier("Weapon modifier", 0.5, AttributeModifier.Operation.ADDITION));
        }
        return attributes;
    }
}
