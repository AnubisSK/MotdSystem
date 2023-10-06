package sk.anubissvk.motdsystem.variables;

import org.bukkit.entity.Player;

public class SvetovePremenne {
    public static String replaceVariables(String string, Player player) {
        String newString = string;
        if (newString.contains("{world_thunderduration}"))
            newString = newString.replace("{world_thunderduration}",
                    String.valueOf(player.getWorld().getThunderDuration()));
        if (newString.contains("{world_seed}"))
            newString = newString.replace("{world_seed}", String.valueOf(player.getWorld().getSeed()));
        if (newString.contains("{world_time}"))
            newString = newString.replace("{world_time}", String.valueOf(player.getWorld().getTime()));
        if (newString.contains("{world_fulltime}"))
            newString = newString.replace("{world_fulltime}", String.valueOf(player.getWorld().getFullTime()));
        if (newString.contains("{world_environment}"))
            newString = newString.replace("{world_environment}", String.valueOf(player.getWorld().getEnvironment()));
        if (newString.contains("{world_haspvp}"))
            newString = newString.replace("{world_haspvp}", String.valueOf(player.getWorld().getPVP()));
        if (newString.contains("{world_difficulty}"))
            newString = newString.replace("{world_difficulty}", String.valueOf(player.getWorld().getDifficulty()));
        if (newString.contains("{world_uuid}"))
            newString = newString.replace("{world_uuid}", String.valueOf(player.getWorld().getUID()));
        if (newString.contains("{world_allowanimals}"))
            newString = newString.replace("{world_allowanimals}", String.valueOf(player.getWorld().getAllowAnimals()));
        if (newString.contains("{world_allowmonsters}"))
            newString = newString.replace("{world_allowmonsters}",
                    String.valueOf(player.getWorld().getAllowMonsters()));
        if (newString.contains("{world_spawnlimit_animal}"))
            newString = newString.replace("{world_spawnlimit_animal}",
                    String.valueOf(player.getWorld().getAnimalSpawnLimit()));
        if (newString.contains("{world_spawnlimit_monster}"))
            newString = newString.replace("{world_spawnlimit_monster}",
                    String.valueOf(player.getWorld().getMonsterSpawnLimit()));
        if (newString.contains("{world_spawnlimit_water}"))
            newString = newString.replace("{world_spawnlimit_water}",
                    String.valueOf(player.getWorld().getWaterAnimalSpawnLimit()));
        if (newString.contains("{world_spawnlimit_ambient}"))
            newString = newString.replace("{world_spawnlimit_ambient}",
                    String.valueOf(player.getWorld().getAmbientSpawnLimit()));
        if (newString.contains("{world_spawn_x}"))
            newString = newString.replace("{world_spawn_x}",
                    String.valueOf(player.getWorld().getSpawnLocation().getX()));
        if (newString.contains("{world_spawn_y}"))
            newString = newString.replace("{world_spawn_y}",
                    String.valueOf(player.getWorld().getSpawnLocation().getY()));
        if (newString.contains("{world_spawn_z}"))
            newString = newString.replace("{world_spawn_z}",
                    String.valueOf(player.getWorld().getSpawnLocation().getZ()));
        if (newString.contains("{world_spawn_yaw}"))
            newString = newString.replace("{world_spawn_yaw}",
                    String.valueOf(player.getWorld().getSpawnLocation().getYaw()));
        if (newString.contains("{world_spawn_pitch}"))
            newString = newString.replace("{world_spawn_pitch}",
                    String.valueOf(player.getWorld().getSpawnLocation().getPitch()));
        if (newString.contains("{world_maxheight}"))
            newString = newString.replace("{world_maxheight}", String.valueOf(player.getWorld().getMaxHeight()));
        if (newString.contains("{world_type}"))
            newString = newString.replace("{world_type}", String.valueOf(player.getWorld().getWorldType()));
        if (newString.contains("{world_sealevel}"))
            newString = newString.replace("{world_sealevel}", String.valueOf(player.getWorld().getSeaLevel()));
        if (newString.contains("{world_loadedchunks}"))
            newString = newString.replace("{world_loadedchunks}",
                    String.valueOf((player.getWorld().getLoadedChunks()).length));
        return newString;
    }
}
