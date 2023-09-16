package earth.terrarium.lookinsharp.common.util.forge;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

import java.util.function.Supplier;

public class PlatformUtilsImpl {
    public static CreativeModeTab createTab(ResourceLocation loc, Supplier<ItemStack> icon) {
        return CreativeModeTab.builder().title(Component.translatable(loc.toLanguageKey("itemGroup"))).icon(icon).build();
    }

    public static Attribute getReachAttribute() {
        return ForgeMod.BLOCK_REACH.get();
    }

    public static Attribute getAttackRangeAttribute() {
        return ForgeMod.ENTITY_REACH.get();
    }
}
