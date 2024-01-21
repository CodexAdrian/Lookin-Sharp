package earth.terrarium.lookinsharp.datagen.provider.client;

import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.rarities.BuiltinRarities;
import earth.terrarium.lookinsharp.api.rarities.ToolRarityApi;
import earth.terrarium.lookinsharp.api.traits.BuiltinTraits;
import earth.terrarium.lookinsharp.api.traits.ToolTraitApi;
import earth.terrarium.lookinsharp.common.registry.ModBlocks;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput pGenerator) {
        super(pGenerator, LookinSharp.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.lookinsharp.main", "Lookin' Sharp!");
        add("ability.lookinsharp.golem", "§7Golem Battering");
        add("ability.lookinsharp.golem.desc", "Mobs hit by your sword go flying!");
        add("ability.lookinsharp.freeze", "§3Glacial Freeze");
        add("ability.lookinsharp.freeze.desc", "Mobs hit by your sword are frozen in place!");
        add("ability.lookinsharp.slowness", "§aSticky Slowness");
        add("ability.lookinsharp.slowness.desc", "Mobs hit by your sword are slowed!");
        add("ability.lookinsharp.weakness", "§6Weakening Strike");
        add("ability.lookinsharp.weakness.desc", "Mobs hit by your sword are weakened!");
        add("ability.lookinsharp.poison", "§2Poisonous Strike");
        add("ability.lookinsharp.poison.desc", "Mobs hit by your sword are poisoned!");
        add("ability.lookinsharp.random", "§5Witch's Tonic");
        add("ability.lookinsharp.random.desc", "Mobs hit by your sword are randomly afflicted with a potion effect, or you are afflicted with a helpful potion effect!");
        add("ability.lookinsharp.wither", "§fWithering Strike");
        add("ability.lookinsharp.wither.desc", "Mobs hit by your sword are withered!");
        add("ability.lookinsharp.life_steal", "§bLife Steal");
        add("ability.lookinsharp.life_steal.desc", "You heal when you hit mobs with your sword!");
        add("tooltip.lookinsharp.energy", "§d⚡§7 %s§d / §7%s");

        List.of(BuiltinTraits.values()).forEach(trait -> add(ToolTraitApi.getTraitId(trait).toLanguageKey("trait"), StringUtils.capitalise(trait.name().toLowerCase(Locale.ROOT))));
        List.of(BuiltinRarities.values()).forEach(rarity -> add(ToolRarityApi.getRarityId(rarity).toLanguageKey("rarity"), StringUtils.capitalise(rarity.name().toLowerCase(Locale.ROOT))));

        ModBlocks.BLOCKS.stream().forEach(entry -> addBlock(entry, StringUtils.capitaliseAllWords(entry.getId().getPath().replace('_', ' '))));
        ModItems.ITEMS.stream().filter(e -> !(e.get() instanceof BlockItem)).forEach(entry -> addItem(entry, StringUtils.capitaliseAllWords(Objects.requireNonNull(entry.getId()).getPath().replace('_', ' '))));
    }
}
