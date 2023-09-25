package earth.terrarium.lookinsharp.datagen.provider.client;

import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.api.abilities.ToolAbilityManager;
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

        List.of(BuiltinTraits.values()).forEach(trait -> add(ToolTraitApi.getTraitId(trait).toLanguageKey("trait"), StringUtils.capitalise(trait.name().toLowerCase(Locale.ROOT))));
        List.of(BuiltinRarities.values()).forEach(rarity -> add(ToolRarityApi.getRarityId(rarity).toLanguageKey("rarity"), StringUtils.capitalise(rarity.name().toLowerCase(Locale.ROOT))));

        ModBlocks.BLOCKS.stream().forEach(entry -> addBlock(entry, StringUtils.capitaliseAllWords(entry.getId().getPath().replace('_', ' '))));
        ModItems.ITEMS.stream().filter(e -> !(e.get() instanceof BlockItem)).forEach(entry -> addItem(entry, StringUtils.capitaliseAllWords(Objects.requireNonNull(entry.getId()).getPath().replace('_', ' '))));
    }
}
