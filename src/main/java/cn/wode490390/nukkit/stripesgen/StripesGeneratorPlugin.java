package cn.wode490390.nukkit.stripesgen;

import cn.nukkit.block.Block;
import cn.nukkit.level.GlobalBlockPalette;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.DyeColor;
import cn.wode490390.nukkit.stripesgen.util.MetricsLite;

import java.util.NoSuchElementException;

public class StripesGeneratorPlugin extends PluginBase {

    private static StripesGeneratorPlugin INSTANCE;

    private StripesGeneratorSettings settings;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        try {
            new MetricsLite(this, 6995);
        } catch (Throwable ignore) {

        }

        this.saveDefaultConfig();
        Config config = this.getConfig();

        String node = "stripe1.material";
        int stripe1Material = Block.CONCRETE;
        try {
            stripe1Material = config.getInt(node, stripe1Material);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        int stripe1Meta = DyeColor.BLACK.getWoolData();
        node = "stripe1.meta";
        try {
            stripe1Meta = config.getInt(node, stripe1Meta);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        node = "stripe2.material";
        int stripe2Material = Block.CONCRETE;
        try {
            stripe2Material = config.getInt(node, stripe2Material);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }
        int stripe2Meta = DyeColor.YELLOW.getWoolData();
        node = "stripe2.meta";
        try {
            stripe2Meta = config.getInt(node, stripe2Meta);
        } catch (Exception e) {
            this.logConfigException(node, e);
        }

        try {
            GlobalBlockPalette.getOrCreateRuntimeId(stripe1Material, 0);
            try {
                GlobalBlockPalette.getOrCreateRuntimeId(stripe1Material, stripe1Meta);
            } catch (NoSuchElementException e) {
                stripe1Meta = 0;
                this.getLogger().warning("Invalid block meta. Use the default value.");
            }
        } catch (NoSuchElementException e) {
            stripe1Material = Block.CONCRETE;
            stripe1Meta = DyeColor.BLACK.getWoolData();
            this.getLogger().warning("Invalid block ID. Use the default value.");
        }
        try {
            GlobalBlockPalette.getOrCreateRuntimeId(stripe2Material, 0);
            try {
                GlobalBlockPalette.getOrCreateRuntimeId(stripe2Material, stripe2Meta);
            } catch (NoSuchElementException e) {
                stripe2Meta = 0;
                this.getLogger().warning("Invalid block meta. Use the default value.");
            }
        } catch (NoSuchElementException e) {
            stripe2Material = Block.CONCRETE;
            stripe2Meta = DyeColor.YELLOW.getWoolData();
            this.getLogger().warning("Invalid block ID. Use the default value.");
        }

        this.settings = new StripesGeneratorSettings(stripe1Material, stripe1Meta, stripe2Material, stripe2Meta);

        Generator.addGenerator(StripesGenerator.class, "default", Generator.TYPE_INFINITE);
        Generator.addGenerator(StripesGenerator.class, "normal", Generator.TYPE_INFINITE);
    }

    public StripesGeneratorSettings getSettings() {
        return this.settings;
    }

    private void logConfigException(String node, Throwable t) {
        this.getLogger().alert("An error occurred while reading the configuration '" + node + "'. Use the default value.", t);
    }

    public static StripesGeneratorPlugin getInstance() {
        return INSTANCE;
    }
}
