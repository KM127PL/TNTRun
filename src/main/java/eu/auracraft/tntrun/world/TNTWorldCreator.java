package eu.auracraft.tntrun.world;

import eu.auracraft.tntrun.Main;
import eu.auracraft.tntrun.utils.ConfigUtils;
import eu.auracraft.tntrun.utils.Logger;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.io.File;
import java.util.Random;

public class TNTWorldCreator extends ChunkGenerator {

    // pressure_plate, sand/red sand, tnt
    // 60, 59, 58

    public static void createWorld(String name) {
        if(doesWorldExist(name)) {
            Logger.log("TNTRun world already exists.");
            return;
        }

        long start = System.currentTimeMillis();

        Logger.log("Creating " + name + " world as a TNTRun world.");
        WorldCreator wc = new WorldCreator(name);
        wc.generator(new TNTWorld());
        wc.type(WorldType.FLAT);

        wc.createWorld();

        long end = System.currentTimeMillis();
        Logger.log("World created! (In " + (end - start) + "ms)");
    }

    public static void setBorder(String name, double size) {
        WorldBorder border = getWorld(name).getWorldBorder();
        border.setCenter(new Location(getWorld(name), 0, 0, 0));
        border.setSize(size);
    }

    public static void prepareWorld(String name) {
        World w = getWorld(name);
        w.setGameRule(GameRule.DO_TILE_DROPS, false);
        w.setGameRule(GameRule.DISABLE_RAIDS, true);
        w.setGameRule(GameRule.DO_ENTITY_DROPS, false);
        w.setGameRule(GameRule.DO_FIRE_TICK, false);
        w.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        w.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        w.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
        w.setGameRule(GameRule.DO_INSOMNIA, false);
        w.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
        w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        w.setGameRule(GameRule.DO_MOB_LOOT, false);
        w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        w.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        w.setGameRule(GameRule.FALL_DAMAGE, false);
        w.setAutoSave(false);
        w.setSpawnLocation(0, 60, 0);
        w.setTime(3000);
        w.strikeLightning(new Location(w, 0, 0, 0)); // preload the chunk at 0, 0
        w.loadChunk(0, 0); // load the actual chunk
    }

    public static void deleteWorld(String name) {
        if(doesWorldExist(name)) return;
        World w = getWorld(name);
        Bukkit.unloadWorld(w, false);
        deleteDirectory(new File(Main.getPlugin(Main.class).getServer().getWorldContainer().getAbsolutePath() + "../" + name));
    }

    // Credits: https://www.baeldung.com/java-delete-directory
    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public static boolean doesWorldExist(String name) {
        if (Bukkit.getWorld(name) != null) return true;
        return false;
    }

    public static World getWorld(String name) {
        return Bukkit.getWorld(name);
    }

    private static class TNTWorld extends ChunkGenerator {
        @Override
        public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
            int currentHeight = 60;
            ChunkData chunk = createChunkData(world);

            for (int X = 0; X < 16; X++)
                for (int Z = 0; Z < 16; Z++) {

                    chunk.setBlock(X, 50, Z,  Material.valueOf(new ConfigUtils().getString("plugin.world.blocks.pressure-plate")));
                    chunk.setBlock(X, 49, Z, Material.SAND);
                    chunk.setBlock(X, 48, Z, Material.valueOf(new ConfigUtils().getString("plugin.world.blocks.tnt")));
                }
            return chunk;
        }
    }
}
