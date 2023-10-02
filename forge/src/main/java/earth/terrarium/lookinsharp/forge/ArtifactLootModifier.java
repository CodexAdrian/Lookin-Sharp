package earth.terrarium.lookinsharp.forge;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamresourceful.resourcefullib.common.codecs.recipes.ItemStackCodec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ArtifactLootModifier extends LootModifier {
    public static final Supplier<Codec<ArtifactLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> inst.group(
            LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter((lm) -> lm.conditions),
            ItemStackCodec.CODEC.fieldOf("result").forGetter((lm) -> lm.result)
            ).apply(inst, ArtifactLootModifier::new)));

    public final ItemStack result;

    protected ArtifactLootModifier(LootItemCondition[] conditionsIn, ItemStack result) {
        super(conditionsIn);
        this.result = result;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext arg) {
        objectArrayList.add(result.copy());
        return objectArrayList;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return LookinSharpForge.TEMPAD_LOOT_MODIFIER.get();
    }
}
