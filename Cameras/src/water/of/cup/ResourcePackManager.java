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
	private boolean isLoaded;

    public void initialize() {
        File dataFolder = Camera.getInstance().getDataFolder();
        File mapDir = new File(dataFolder, "resource-packs");
        if (!mapDir.exists()) {
            mapDir.mkdir();
        }

        if(mapDir.listFiles().length == 0) {
            Bukkit.getLogger().info("No resource pack found, downloading... (this may take a while)");
            this.downloadResourcePack();
        }

        for(File file : mapDir.listFiles()) {
        	if(!file.getName().endsWith(".zip")) {
				this.resourcePackFile = file;
			} else {
        		file.delete();
			}
		}

		if(this.resourcePackFile == null) {
			Bukkit.getLogger().warning("No resource pack found. Please restart.");
			return;
		}

		Bukkit.getLogger().info("Loading in resource pack (this may take a while)");
		Bukkit.getScheduler().runTaskAsynchronously(Camera.getInstance(), () -> this.initializeImageHashmap());
    }

	public File getTextureByMaterial(Material material) {
		if (this.resourcePackFile == null) {
			Bukkit.getLogger().warning("Tried getting texture file but no resource path found.");
			return null;
		}

		String textureName = material.toString().toLowerCase();
		File[] listOfFiles = this.resourcePackFile.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String fileName = file.getName();

				if (fileName.toLowerCase().contains(textureName))
					return file;
				while (textureName.contains("_")) {
					textureName = textureName.substring(0, textureName.lastIndexOf('_'));
					if (fileName.toLowerCase().contains(textureName)) 
						return file;
				} 
			}
		}

		return null;
	}

	private void initializeImageHashmap() {
		if (this.resourcePackFile == null) {
			Bukkit.getLogger().warning("Tried getting texture file but no resource path found.");
			return;
		}

		for (Material material : Material.values()) {
			File textureFile = this.getTextureByMaterial(material);
			if (textureFile != null) {
				try {
					BufferedImage image = ImageIO.read(textureFile);
					imageHashMap.put(material, image);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

		Bukkit.getLogger().info("Loaded " + this.imageHashMap.size() + " textures from resource pack "
				+ this.resourcePackFile.getName());
		this.isLoaded = true;
	}

	public HashMap<Material, BufferedImage> getImageHashMap() {
		return this.imageHashMap;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public boolean isLoaded() {
		return this.isLoaded;
	}
}
