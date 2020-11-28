package water.of.cup;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

		// TODO: Look for and download resource packs

		File folder = new File(dataFolder + "/resource-packs");
		File[] listOfFiles = folder.listFiles();

		if (listOfFiles.length == 0) {
			Bukkit.getLogger().info("No resource pack found, downloading...");
		} else {
			this.resourcePackFile = listOfFiles[0];
			this.initializeImageHashmap();
			Bukkit.getLogger().info("Using resource pack " + resourcePackFile.getName());
		}
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
	}

	public HashMap<Material, BufferedImage> getImageHashMap() {
		return this.imageHashMap;
	}

}
