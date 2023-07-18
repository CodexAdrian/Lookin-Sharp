package earth.terrarium.unsheathed.api.traits;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

@FunctionalInterface
public interface AttributeModificationFunction {
    void modifyAttribute(Attribute attribute, AttributeModifier modifier);
}
