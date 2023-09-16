package earth.terrarium.lookinsharp.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.WrappedTier;
import earth.terrarium.lookinsharp.common.util.PlatformUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

public class SpearItem extends BaseSword {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public SpearItem(Tier tier, Properties properties) {
        super(new WrappedTier.Builder(tier).maxUses(1.5f).build(), 4, -2f, properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.getDamage() * .9, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.6f, AttributeModifier.Operation.ADDITION));
        builder.put(PlatformUtils.getAttackRangeAttribute(), new AttributeModifier(LookinSharp.BASE_RANGE, "Weapon modifier", 2, AttributeModifier.Operation.ADDITION));
        builder.put(PlatformUtils.getReachAttribute(), new AttributeModifier(LookinSharp.BASE_REACH, "Weapon modifier", 0.5, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }
}

