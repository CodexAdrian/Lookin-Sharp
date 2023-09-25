package earth.terrarium.lookinsharp.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import earth.terrarium.lookinsharp.api.abilities.ToolAbility;
import earth.terrarium.lookinsharp.api.abilities.ToolAbilityManager;
import earth.terrarium.lookinsharp.api.rarities.ToolRarity;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.ToolTrait;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.api.types.SwordType;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
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
    public static final String ABILITY_KEY = "Ability";
    public final SwordType type;

    public BaseSword(Tier tier, SwordType type, int i, float f, Properties properties) {
        super(tier, i, f, properties);
        this.type = type;
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, Level level, Player player) {
        super.onCraftedBy(itemStack, level, player);
        if (level.isClientSide()) {
            return;
        }

        if (ToolTraitApi.fromItem(itemStack) == null) {
            ToolTrait trait = ToolTraitApi.rollTrait();
            ToolTraitApi.setTrait(itemStack, trait);
        }

        if (ToolRarityApi.fromItem(itemStack) == null) {
            ToolRarity rarity = ToolRarityApi.rollRarity();
            ToolRarityApi.setRarity(itemStack, rarity);
        }

    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        if (level == null) return;

        ToolRarity rarity = ToolRarityApi.fromItem(itemStack);
        if (rarity != null) {
            list.add(Component.translatable(ToolRarityApi.getRarityId(rarity).toLanguageKey("rarity")).withStyle(ChatFormatting.BOLD).withStyle(Style.EMPTY.withColor(rarity.getColor())));
        }

        ToolAbility ability = getAbility(itemStack);
        if (ability != null) {
            ResourceLocation resourceLocation = ResourceLocation.tryParse(ToolAbilityManager.getName(ability));
            if (resourceLocation == null) return;
            list.add(Component.translatable(resourceLocation.toLanguageKey("ability")));
            list.add(Component.translatable(resourceLocation.toLanguageKey("ability").concat(".desc")).withStyle(ChatFormatting.GRAY));
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

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if (EquipmentSlot.MAINHAND != equipmentSlot) return super.getDefaultAttributeModifiers(equipmentSlot);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(super.getDefaultAttributeModifiers(equipmentSlot));
        this.type.modifyAttributeModifiers(builder);
        return builder.build();
    }

    public SwordType getType() {
        return this.type;
    }

    @Nullable
    public ToolAbility getAbility(ItemStack stack) {
        return ToolAbilityManager.getAbility(stack.getOrCreateTag().getString(ABILITY_KEY));
    }

    public void setAbility(ItemStack stack, ToolAbility ability) {
        stack.getOrCreateTag().putString(ABILITY_KEY, ToolAbilityManager.getName(ability));
    }
}
