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
                function.modifyAttribute(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Trait modifier", 0.1 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
                function.modifyAttribute(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Trait modifier", 0.1 * rarity.getMultiplier(), AttributeModifier.Operation.ADDITION));
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

    public static final UUID ATTACK_SPEED_MODIFIER = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    public static final UUID MOVEMENT_SPEED_MODIFIER = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    public static final UUID ARMOR_MODIFIER = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");
    public static final UUID ARMOR_TOUGHNESS_MODIFIER = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D");
    public static final UUID MAX_HEALTH_MODIFIER = UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC");

    private final int weight;

    BuiltinTraits(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
