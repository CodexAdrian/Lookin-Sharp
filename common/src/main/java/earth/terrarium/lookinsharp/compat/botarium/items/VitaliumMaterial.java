package earth.terrarium.lookinsharp.compat.botarium.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class VitaliumMaterial implements Tier {
    public static final VitaliumMaterial INSTANCE = new VitaliumMaterial();
    @Override
    public int getUses() {
        return 1000;
    }

    @Override
    public float getSpeed() {
        return 8.0F;
    }

    @Override
    public float getAttackDamageBonus() {
        return 3.5F;
    }

    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public int getEnchantmentValue() {
        return 15;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }
}
