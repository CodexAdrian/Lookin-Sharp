package earth.terrarium.lookinsharp.common.util;

import com.teamresourceful.resourcefullib.common.utils.modinfo.ModInfoUtils;
import dev.architectury.injectables.annotations.ExpectPlatform;
import earth.terrarium.lookinsharp.api.abilities.ToolAbility;
import earth.terrarium.lookinsharp.common.items.BaseSword;
import earth.terrarium.lookinsharp.compat.botarium.BotariumCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Supplier;

public class PlatformUtils {
    @ExpectPlatform
    public static CreativeModeTab createTab(ResourceLocation loc, Supplier<ItemStack> icon) {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static Attribute getReachAttribute() {
        throw new NotImplementedException();
    }

    @ExpectPlatform
    public static Attribute getAttackRangeAttribute() {
        throw new NotImplementedException();
    }

    public static boolean onEntityHit(LivingEntity victim, DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            ItemStack stack = player.getMainHandItem();
            if (stack.getItem() instanceof BaseSword tool) {
                ToolAbility ability = tool.getAbility(stack);
                boolean canAttack = true;
                if (ModInfoUtils.isModLoaded("botarium")) {
                    canAttack = BotariumCompat.canAttack(stack);
                }
                if (ability != null) {
                    return canAttack && ability.onHit(source, victim, amount);
                } else {
                    return canAttack;
                }
            }
        }
        return true;
    }
}
