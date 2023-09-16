package earth.terrarium.lookinsharp.common.util.fabric;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class PlatformUtilsImpl {
    public static CreativeModeTab createTab(ResourceLocation loc, Supplier<ItemStack> icon) {
        return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable(loc.toLanguageKey("itemGroup"))).icon(icon).build();
    }

    public static Attribute getReachAttribute() {
        return ReachEntityAttributes.REACH;
    }

    public static Attribute getAttackRangeAttribute() {
        return ReachEntityAttributes.ATTACK_RANGE;
    }
}
