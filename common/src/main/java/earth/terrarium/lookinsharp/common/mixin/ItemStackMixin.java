package earth.terrarium.lookinsharp.common.mixin;

import com.teamresourceful.resourcefullib.common.utils.modinfo.ModInfoUtils;
import earth.terrarium.lookinsharp.compat.botarium.BotariumCompat;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();

    @Inject(method= "hurtAndBreak", at = @At("TAIL"))
    public <T extends LivingEntity> void lookinsharp$hurtAndBreak(int i, T livingEntity, Consumer<T> consumer, CallbackInfo ci) {
        if (!livingEntity.level().isClientSide && (!(livingEntity instanceof Player) || !((Player)livingEntity).getAbilities().instabuild)) {
            if (ModInfoUtils.isModLoaded("botarium")) {
                BotariumCompat.extractEnergy((ItemStack)(Object)this, i);
            }
        }
    }
}
