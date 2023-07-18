package earth.terrarium.unsheathed.common.util.forge;

import earth.terrarium.unsheathed.Unsheathed;
import earth.terrarium.unsheathed.common.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

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
