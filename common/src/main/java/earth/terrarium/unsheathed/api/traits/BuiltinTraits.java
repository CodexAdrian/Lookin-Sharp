package earth.terrarium.unsheathed.api.traits;

import com.google.common.collect.Multimap;
import earth.terrarium.unsheathed.api.rarities.ToolRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public enum BuiltinTraits implements ToolTrait {
    NIMBLE(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Trait modifier", 0.2 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
            }
        }
    },
    HEAVY(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Trait modifier", -0.1 * rarity.getMultiplier(), AttributeModifier.Operation.MULTIPLY_TOTAL));
                function.modifyAttribute(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Trait modifier", 0.2 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
            }
        }
    },
    SHARP(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Trait modifier", 0.5 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
                function.modifyAttribute(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Trait modifier", -0.2 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
            }
        }
    },
    LIGHT(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Trait modifier", 0.15 * rarity.getMultiplier(), AttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
    },
    STURDY(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.ARMOR, new AttributeModifier(ARMOR_MODIFIER, "Trait modifier", 1 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
            }
        }
    },
    TOUGH(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ARMOR_TOUGHNESS_MODIFIER, "Trait modifier", 0.5 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
            }
        }
    },
    INVIGORATING(1){
        @Override
        public void modifyAttributes(ItemStack stack, EquipmentSlot slot, AttributeModificationFunction function, ToolRarity rarity) {
            if(slot == EquipmentSlot.MAINHAND) {
                function.modifyAttribute(Attributes.MAX_HEALTH, new AttributeModifier(MAX_HEALTH_MODIFIER, "Trait modifier", 1 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
            }
        }
    };

    public static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("c64d76aa-25ab-11ee-be56-0242ac120002");
    public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("c1baa63a-25ab-11ee-be56-0242ac120002");
    public static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("cc3fede0-25ab-11ee-be56-0242ac120002");
    public static final UUID ARMOR_MODIFIER = UUID.fromString("d08eb552-25ab-11ee-be56-0242ac120002");
    public static final UUID ARMOR_TOUGHNESS_MODIFIER = UUID.fromString("d3f54990-25ab-11ee-be56-0242ac120002");
    public static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("d7608fa4-25ab-11ee-be56-0242ac120002");

    private final int weight;

    BuiltinTraits(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
