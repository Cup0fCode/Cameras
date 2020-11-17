package water.of.cup;

import org.apache.logging.log4j.core.util.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.map.MapRenderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class MapStorage {

    public static void store(int id, byte[][] data) {
        String serializedData = "";
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                serializedData += data[i][j] + ",";
            }
        }

        File file = new File(Camera.getInstance().getDataFolder(), "maps/map_" + id + ".txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e1) {
                Bukkit.getLogger().severe("Error creating map file for mapId: " + id);
                e1.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get(file.getPath()), serializedData.getBytes());
        } catch (IOException e) {
            Bukkit.getLogger().severe("Error writing to mapId: " + id);
            e.printStackTrace();
        }
    }

}
