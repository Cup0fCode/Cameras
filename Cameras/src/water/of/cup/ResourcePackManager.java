package water.of.cup;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class ResourcePackManager {

    private File resourcePackFile;
    private HashMap<Material, BufferedImage> imageHashMap = new HashMap<>();

    public void initialize() {
        File dataFolder = Camera.getInstance().getDataFolder();
        File mapDir = new File(dataFolder, "resource-packs");
        if (!mapDir.exists()) {
            mapDir.mkdir();
        }

        File folder = new File(dataFolder + "/resource-packs");
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles.length == 0) {
            Bukkit.getLogger().info("No resource pack found, downloading...");
            this.downloadResourcePack();
        } else {
            this.resourcePackFile = listOfFiles[0];
        }

        this.initializeImageHashmap();
        Bukkit.getLogger().info("Using resource pack " + this.resourcePackFile.getName());
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

    private void initializeImageHashmap() {
        if(this.resourcePackFile == null) {
            Bukkit.getLogger().warning("Tried getting texture file but no resource path found.");
            return;
        }

        for (Material material : Material.values()) {
            File textureFile = this.getTextureByMaterial(material);
            if(textureFile != null) {
                try {
                    BufferedImage image = ImageIO.read(textureFile);
                    imageHashMap.put(material, image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Bukkit.getLogger().info("Loaded " + this.imageHashMap.size() + " textures from resource pack " + this.resourcePackFile.getName());
    }

    private void downloadResourcePack() {
        File destLocation = new File(Camera.getInstance().getDataFolder() + "/resource-packs/1_16_4/");
        File fileLocation = new File(Camera.getInstance().getDataFolder() + "/resource-packs/1_16_4.zip");
        try (BufferedInputStream in = new BufferedInputStream(new URL("https://github.com/Cup0fCode/resource-packs/raw/main/1_16_4.zip").openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(fileLocation)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            if (!destLocation.exists()) {
                destLocation.mkdir();
            }

            ZipUtils.unzip(fileLocation, Camera.getInstance().getDataFolder() + "/resource-packs/1_16_4/");
            fileLocation.delete();
            this.resourcePackFile = destLocation;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Material, BufferedImage> getImageHashMap() {
        return this.getImageHashMap();
    }

}
