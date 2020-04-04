package cn.wode490390.nukkit.stripesgen;

import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.biome.EnumBiome;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

import java.util.Collections;
import java.util.Map;

public class StripesGenerator extends Generator {

    protected ChunkManager level;
    protected StripesGeneratorSettings settings;

    public StripesGenerator() {
        this(null);
    }

    public StripesGenerator(Map<String, Object> options) {
        this.settings = StripesGeneratorPlugin.getInstance().getSettings();
    }

    @Override
    public int getId() {
        return TYPE_INFINITE;
    }

    @Override
    public String getName() {
        return "normal";
    }

    @Override
    public ChunkManager getChunkManager() {
        return this.level;
    }

    @Override
    public Map<String, Object> getSettings() {
        return Collections.emptyMap();
    }

    @Override
    public void init(ChunkManager level, NukkitRandom random) {
        this.level = level;
    }

    @Override
    public void generateChunk(int chunkX, int chunkZ) {
        BaseFullChunk chunk = this.level.getChunk(chunkX, chunkZ);

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int xz = x + z;
                for (int y = 0; y < 16; y++) {
                    if (((xz + y) & 2) == 0) {
                        chunk.setBlock(x, y, z, this.settings.getStripe1Id(), this.settings.getStripe1Meta());
                    } else {
                        chunk.setBlock(x, y, z, this.settings.getStripe2Id(), this.settings.getStripe2Meta());
                    }
                }
                chunk.setBiome(x, z, EnumBiome.PLAINS.biome);
            }
        }
    }

    @Override
    public void populateChunk(int chunkX, int chunkZ) {

    }

    @Override
    public Vector3 getSpawn() {
        return new Vector3(0.5, 16.5, 0.5);
    }
}
