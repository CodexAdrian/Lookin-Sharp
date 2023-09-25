package earth.terrarium.lookinsharp.common.abilities;

import com.teamresourceful.resourcefullib.common.collections.WeightedCollection;
import earth.terrarium.lookinsharp.api.abilities.ToolAbility;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class RandomEffectAbility implements ToolAbility {
    public static final WeightedCollection<AttackFunction> EFFECTS = new WeightedCollection<>();
    static {
        EFFECTS.add(2, ((attacker, victim) -> victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1))));
        EFFECTS.add(2, ((attacker, victim) -> victim.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1))));
        EFFECTS.add(2, ((attacker, victim) -> victim.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1))));
        EFFECTS.add(2, ((attacker, victim) -> victim.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1))));
        EFFECTS.add(2, ((attacker, victim) -> victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 4))));
        EFFECTS.add(10, ((attacker, victim) -> {}));
        EFFECTS.add(1, ((attacker, victim) -> attacker.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2))));
        EFFECTS.add(1, ((attacker, victim) -> attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 1))));
        EFFECTS.add(1, ((attacker, victim) -> attacker.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60, 1))));
        EFFECTS.add(1, ((attacker, victim) -> attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1))));
        EFFECTS.add(1, ((attacker, victim) -> attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 1))));
    }

    @Override
    public boolean onHit(DamageSource source, LivingEntity victim, float amount) {
        if (source.getEntity() instanceof LivingEntity attacker) {
            EFFECTS.next().apply(attacker, victim);
        }
        return true;
    }

    public interface AttackFunction {
        void apply(LivingEntity attacker, LivingEntity victim);
    }
}
