package earth.terrarium.lookinsharp.common.abilities;

import earth.terrarium.lookinsharp.api.abilities.ToolAbility;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class GolemAbility implements ToolAbility {
    @Override
    public boolean onHit(DamageSource source, LivingEntity victim, float amount) {
        victim.setDeltaMovement(victim.getDeltaMovement().x, Math.max(victim.getDeltaMovement().y, 0) + .4, victim.getDeltaMovement().z);
        return true;
    }
}
