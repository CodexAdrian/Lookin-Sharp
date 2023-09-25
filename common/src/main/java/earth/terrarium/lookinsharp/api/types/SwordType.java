package earth.terrarium.lookinsharp.api.types;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;

public interface SwordType {
    default void modifyAttributeModifiers(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder) {}
}
