package earth.terrarium.lookinsharp.api.abilities;

import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public interface ToolAbility {
    default boolean onHit(DamageSource source, LivingEntity victim, float amount) { return true; }
}
