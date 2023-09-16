package earth.terrarium.lookinsharp.client.screen;

import earth.terrarium.lookinsharp.common.menu.ForgingStationContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ForgingAnvilScreen extends AbstractContainerScreen<ForgingStationContainer> {
    private static final ResourceLocation BG_LOCATION = new ResourceLocation("textures/gui/container/stonecutter.png");
    private static final int SCROLLER_WIDTH = 12;
    private static final int SCROLLER_HEIGHT = 15;
    private static final int RECIPES_COLUMNS = 4;
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    private static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_X = 52;
    private static final int RECIPES_Y = 14;
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public ForgingAnvilScreen(ForgingStationContainer stonecutterMenu, Inventory inventory, Component component) {
        super(stonecutterMenu, inventory, component);
        stonecutterMenu.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        this.renderTooltip(guiGraphics, i, j);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        this.renderBackground(guiGraphics);
        int k = this.leftPos;
        int l = this.topPos;
        guiGraphics.blit(BG_LOCATION, k, l, 0, 0, this.imageWidth, this.imageHeight);
        int m = (int)(41.0f * this.scrollOffs);
        guiGraphics.blit(BG_LOCATION, k + 119, l + 15 + m, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
        int n = this.leftPos + 52;
        int o = this.topPos + 14;
        int p = this.startIndex + 12;
        this.renderButtons(guiGraphics, i, j, n, o, p);
        this.renderRecipes(guiGraphics, n, o, p);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int i, int j) {
        super.renderTooltip(guiGraphics, i, j);
        if (this.displayRecipes) {
            int k = this.leftPos + 52;
            int l = this.topPos + 14;
            int m = this.startIndex + 12;
            List<ItemStack> list = this.menu.getOutputs();
            for (int n = this.startIndex; n < m && n < menu.getNumOutputs(); ++n) {
                int o = n - this.startIndex;
                int p = k + o % 4 * 16;
                int q = l + o / 4 * 18 + 2;
                if (i < p || i >= p + 16 || j < q || j >= q + 18) continue;
                guiGraphics.renderTooltip(this.font, list.get(n), i, j);
            }
        }
    }

    private void renderButtons(GuiGraphics guiGraphics, int i, int j, int k, int l, int m) {
        for (int n = this.startIndex; n < m && n < menu.getNumOutputs(); ++n) {
            int o = n - this.startIndex;
            int p = k + o % 4 * 16;
            int q = o / 4;
            int r = l + q * 18 + 2;
            int s = this.imageHeight;
            if (n == menu.getSelectedRecipeIndex()) {
                s += 18;
            } else if (i >= p && j >= r && i < p + 16 && j < r + 18) {
                s += 36;
            }
            guiGraphics.blit(BG_LOCATION, p, r - 1, 0, s, 16, 18);
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int i, int j, int k) {
        List<ItemStack> list = menu.getOutputs();
        for (int l = this.startIndex; l < k && l < menu.getNumOutputs(); ++l) {
            int m = l - this.startIndex;
            int n = i + m % 4 * 16;
            int o = m / 4;
            int p = j + o * 18 + 2;
            guiGraphics.renderItem(list.get(l), n, p);
        }
    }

    @Override
    public boolean mouseClicked(double d, double e, int i) {
        this.scrolling = false;
        if (this.displayRecipes) {
            int j = this.leftPos + 52;
            int k = this.topPos + 14;
            int l = this.startIndex + 12;
            for (int m = this.startIndex; m < l; ++m) {
                int n = m - this.startIndex;
                double f = d - (double)(j + n % 4 * 16);
                double g = e - (double)(k + n / 4 * 18);
                if (!(f >= 0.0) || !(g >= 0.0) || !(f < 16.0) || !(g < 18.0) || !menu.clickMenuButton(this.minecraft.player, m)) continue;
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0f));
                this.minecraft.gameMode.handleInventoryButtonClick(menu.containerId, m);
                return true;
            }
            j = this.leftPos + 119;
            k = this.topPos + 9;
            if (d >= (double)j && d < (double)(j + 12) && e >= (double)k && e < (double)(k + 54)) {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(d, e, i);
    }

    @Override
    public boolean mouseDragged(double d, double e, int i, double f, double g) {
        if (this.scrolling && this.isScrollBarActive()) {
            int j = this.topPos + 14;
            int k = j + 54;
            this.scrollOffs = ((float)e - (float)j - 7.5f) / ((float)(k - j) - 15.0f);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0f, 1.0f);
            this.startIndex = (int)((double)(this.scrollOffs * (float)this.getOffscreenRows()) + 0.5) * 4;
            return true;
        }
        return super.mouseDragged(d, e, i, f, g);
    }

    @Override
    public boolean mouseScrolled(double d, double e, double f) {
        if (this.isScrollBarActive()) {
            int i = this.getOffscreenRows();
            float g = (float)f / (float)i;
            this.scrollOffs = Mth.clamp(this.scrollOffs - g, 0.0f, 1.0f);
            this.startIndex = (int)((double)(this.scrollOffs * (float)i) + 0.5) * 4;
        }
        return true;
    }

    private boolean isScrollBarActive() {
        return this.displayRecipes && menu.getNumOutputs() > 12;
    }

    protected int getOffscreenRows() {
        return (menu.getNumOutputs() + 4 - 1) / 4 - 3;
    }

    private void containerChanged() {
        this.displayRecipes = menu.hasInputItem();
        if (!this.displayRecipes) {
            this.scrollOffs = 0.0f;
            this.startIndex = 0;
        }
    }
}

