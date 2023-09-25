package earth.terrarium.lookinsharp.api.types;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.common.registry.MaterialTypes;
import earth.terrarium.lookinsharp.common.util.PlatformUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public enum BuiltInSwordTypes implements SwordType {
    SWORD,
    BROADSWORD {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(LookinSharp.BASE_TOUGHNESS, "Weapon modifier", .2, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(LookinSharp.BASE_KNOCKBACK, "Weapon modifier", .2, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    },
    SHORTSWORD {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", -0.1f, AttributeModifier.Operation.MULTIPLY_TOTAL));
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(LookinSharp.BASE_MOVEMENT, "Weapon modifier", .1, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    },
    CUTLASS {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", .4f, AttributeModifier.Operation.ADDITION));
        }
    },
    HONED_SWORD {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", .1f, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    },
    GLADIUS {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", .2f, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(LookinSharp.BASE_TOUGHNESS, "Weapon modifier", 2, AttributeModifier.Operation.ADDITION));
        }
    },
    CLEAVER {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", 3, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -.8f, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(LookinSharp.BASE_KNOCKBACK, "Weapon modifier", .3, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(LookinSharp.BASE_MOVEMENT, "Weapon modifier", -.1, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    },
    GREATSWORD {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", .2f, AttributeModifier.Operation.ADDITION));
            builder.put(PlatformUtils.getAttackRangeAttribute(), new AttributeModifier(LookinSharp.BASE_RANGE, "Weapon modifier", 1, AttributeModifier.Operation.ADDITION));
        }
    },
    SCYTHE {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(LookinSharp.BASE_KNOCKBACK, "Weapon modifier", .2, AttributeModifier.Operation.MULTIPLY_BASE));
        }
    },
    SPEAR {
        @Override
        public void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", -.1, AttributeModifier.Operation.MULTIPLY_BASE));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -.2f, AttributeModifier.Operation.ADDITION));
            builder.put(PlatformUtils.getAttackRangeAttribute(), new AttributeModifier(LookinSharp.BASE_RANGE, "Weapon modifier", 2, AttributeModifier.Operation.ADDITION));
            builder.put(PlatformUtils.getReachAttribute(), new AttributeModifier(LookinSharp.BASE_REACH, "Weapon modifier", 0.5, AttributeModifier.Operation.ADDITION));
        }
    }
    ;

    public static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("b4f53ec0-54f3-11ee-8c99-0242ac120002");
    public static final UUID BASE_ATTACK_SPEED_UUID = UUID.fromString("b8f09178-54f3-11ee-8c99-0242ac120002");
    public static final BuiltInSwordTypes[] VALUES = values();
}
