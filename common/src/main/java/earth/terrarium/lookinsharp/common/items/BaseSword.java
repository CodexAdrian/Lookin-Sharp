package earth.terrarium.lookinsharp.common.items;

import earth.terrarium.lookinsharp.api.rarities.ToolRarity;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTrait;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseSword extends SwordItem {
    public BaseSword(Tier tier, int i, float f, Properties properties) {
        super(tier, i, f, properties);
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, Level level, Player player) {
        super.onCraftedBy(itemStack, level, player);
        if (level.isClientSide()) {
            return;
        }

        ToolTrait trait = ToolTraitApi.rollTrait();
        ToolTraitApi.setTrait(itemStack, trait);

        ToolRarity rarity = ToolRarityApi.rollRarity();
        ToolRarityApi.setRarity(itemStack, rarity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        if (level == null) return;

        ToolRarity rarity = ToolRarityApi.fromItem(itemStack);

        if (rarity != null) {
            list.add(Component.translatable(ToolRarityApi.getRarityId(rarity).toLanguageKey("rarity")).withStyle(ChatFormatting.BOLD).withStyle(Style.EMPTY.withColor(rarity.getColor())));
        }
    }

    @Override
    public @NotNull Component getName(ItemStack itemStack) {
        ToolTrait toolTrait = ToolTraitApi.fromItem(itemStack);
        if (toolTrait != null) {
            return Component.translatable(ToolTraitApi.getTraitId(toolTrait).toLanguageKey("trait")).append(Component.literal(" ")).append(super.getName(itemStack));
        }
        return super.getName(itemStack);
    }
}
