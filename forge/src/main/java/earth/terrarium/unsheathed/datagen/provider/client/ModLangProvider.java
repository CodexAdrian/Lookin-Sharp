package earth.terrarium.unsheathed.datagen.provider.client;

import earth.terrarium.unsheathed.Unsheathed;
import earth.terrarium.unsheathed.api.rarities.BuiltinRarities;
import earth.terrarium.unsheathed.api.rarities.ToolRarityApi;
import earth.terrarium.unsheathed.api.traits.BuiltinTraits;
import earth.terrarium.unsheathed.api.traits.ToolTraitApi;
import earth.terrarium.unsheathed.common.registry.ModBlocks;
import earth.terrarium.unsheathed.common.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.common.data.LanguageProvider;
import org.codehaus.plexus.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput pGenerator) {
        super(pGenerator, Unsheathed.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.unsheathed.main", "Unnamed Sword Mod, name needs more beans");

        List.of(BuiltinTraits.values()).forEach(trait -> add(ToolTraitApi.getTraitId(trait).toLanguageKey("trait"), StringUtils.capitalise(trait.name().toLowerCase(Locale.ROOT))));
        List.of(BuiltinRarities.values()).forEach(rarity -> add(ToolRarityApi.getRarityId(rarity).toLanguageKey("rarity"), StringUtils.capitalise(rarity.name().toLowerCase(Locale.ROOT))));

        ModBlocks.BLOCKS.stream().forEach(entry -> addBlock(entry, StringUtils.capitaliseAllWords(entry.getId().getPath().replace("_", " "))));
        ModItems.ITEMS.stream().filter(e -> !(e.get() instanceof BlockItem)).forEach(entry -> addItem(entry, StringUtils.capitaliseAllWords(Objects.requireNonNull(entry.getId()).getPath().replace("_", " "))));
    }
}
