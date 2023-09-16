package earth.terrarium.lookinsharp.datagen.provider.client;

import earth.terrarium.lookinsharp.LookinSharp;
import earth.terrarium.lookinsharp.common.registry.SwordTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
        super(generator, LookinSharp.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        new ArrayList<>(List.of(SwordTypes.VALUES)).stream().flatMap(swordTypes -> swordTypes.items.stream()).forEach(entry -> {
            getBuilder(entry.getId().toString())
                    .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                    .texture("layer0", new ResourceLocation(entry.getId().getNamespace(), "item/" + entry.getId().getPath()));
        });
    }
}
