package earth.terrarium.unsheathed.api.rarities;

public enum BuiltinRarities implements ToolRarity {
    COMMON(40, 0.75, 0xFFbfbfbf),
    UNCOMMON(30, 0.95, 0xFF34eb3d),
    RARE(15, 1.0, 0xFF3489eb),
    EPIC(10, 1.1, 0xFF8034eb),
    LEGENDARY(5, 1.25, 0xFFeb3434),
    ARTIFACT(0, 1.5, 0xFFEBB434);

    private final int color;
    private final int weight;
    private final double multiplier;

    BuiltinRarities(int weight, double multiplier, int color) {
        this.weight = weight;
        this.multiplier = multiplier;
        this.color = color;
    }


    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public int getColor() {
        return color;
    }
}
