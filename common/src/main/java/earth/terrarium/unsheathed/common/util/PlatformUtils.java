package earth.terrarium.unsheathed.common.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
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
}
