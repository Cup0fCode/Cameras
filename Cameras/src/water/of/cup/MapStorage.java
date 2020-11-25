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
//        String serializedData = serializeMapData(data);
        String serializedData = serializeMapDataSimple(data);

        Bukkit.getLogger().info("Serialized Data: " + serializedData);

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

    public static String serializeMapData(byte[][] data) {
        String current = String.format("%s", data[0][0]);
        StringBuilder builder = new StringBuilder();
        int currentCount = 0;

        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                byte color = data[i][j];

                String colorString = String.format("%s", color);

                if(i == data.length-1 && j == data[i].length-1) {
                    if(current.equals(colorString)) currentCount++;
                    builder.append(colorString).append("_").append(currentCount).append(",");
                    continue;
                }

                if(current.equals(colorString)) {
                    currentCount++;
                    continue;
                }

                current = colorString;
                builder.append(colorString).append("_").append(currentCount).append(",");
                currentCount = 1;
            }
        }

        return builder.toString();
    }

    public static String serializeMapDataSimple(byte[][] data) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                byte color = data[i][j];

                String colorString = String.format("%s", color);
                builder.append(colorString).append("_").append(1).append(",");
            }
        }
        return builder.toString();
    }
}
