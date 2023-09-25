package earth.terrarium.lookinsharp.datagen.provider.client;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.common.registry.MaterialTypes;
import earth.terrarium.lookinsharp.common.registry.ModItems;
import earth.terrarium.lookinsharp.compat.botarium.registry.BotariumItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, LookinSharp.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.SWORDS.stream().forEach(entry -> {
            getBuilder(entry.getId().toString())
                    .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                    .texture("layer0", new ResourceLocation(entry.getId().getNamespace(), "item/" + entry.getId().getPath()));
        });
        ModItems.CRAFTING_COMPONENTS.stream().forEach(entry -> {
            getBuilder(entry.getId().toString())
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", new ResourceLocation(entry.getId().getNamespace(), "item/" + entry.getId().getPath()));
        });
    }
}
