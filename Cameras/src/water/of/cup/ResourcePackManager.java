package water.of.cup;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.io.File;

public class ResourcePackManager {

    private File resourcePackFile;

    public void initialize() {
        File dataFolder = Camera.getInstance().getDataFolder();
        File mapDir = new File(dataFolder, "resource-packs");
        if (!mapDir.exists()) {
            mapDir.mkdir();
        }

        // TODO: Look for and download resource packs

        File folder = new File(dataFolder + "/resource-packs");
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles.length == 0) {
            Bukkit.getLogger().info("No resource pack found, downloading...");
        } else {
            this.resourcePackFile = listOfFiles[0];
            Bukkit.getLogger().info("Using resource pack " + resourcePackFile.getName());
        }
    }

    public File getTextureByMaterial(Material material) {
        if(this.resourcePackFile == null) {
            Bukkit.getLogger().warning("Tried getting texture file but no resource path found.");
            return null;
        }

        String textureName = material.toString().toLowerCase() + ".png";
        File[] listOfFiles = this.resourcePackFile.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                if(fileName.equalsIgnoreCase(textureName)) return file;
            }
        }

        return null;
    }

}
