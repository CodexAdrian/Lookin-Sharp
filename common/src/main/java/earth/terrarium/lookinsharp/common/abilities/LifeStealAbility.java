package earth.terrarium.lookinsharp.common.abilities;

import earth.terrarium.lookinsharp.api.abilities.ToolAbility;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class LifeStealAbility implements ToolAbility {
    @Override
    public boolean onHit(DamageSource source, LivingEntity victim, float amount) {
        if (source.getEntity() instanceof LivingEntity attacker) {
            attacker.heal(amount / 4);
        }
        return true;
    }
}
