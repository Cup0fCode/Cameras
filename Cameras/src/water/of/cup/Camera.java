package water.of.cup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import water.of.cup.commands.CameraCommands;
import water.of.cup.listeners.CameraClick;
import water.of.cup.listeners.CameraPlace;

public class Camera extends JavaPlugin {

	private static Camera instance;

	@Override
	public void onEnable() {
		instance = this;

		loadConfig();

		File folder = new File(getDataFolder() + "/maps");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				int mapId = Integer.parseInt(file.getName().split("_")[1].split(Pattern.quote("."))[0]);
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String[] data = br.readLine().split(",");
					Bukkit.getLogger().info("Reading MapID: " + mapId);

					MapView mapView = Bukkit.getMap(Integer.valueOf(mapId));
					mapView.getRenderers().clear();
					mapView.addRenderer(new MapRenderer() {
						@Override
						public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
							int index = 0;
							for (int x = 0; x < 128; x++) {
								for (int y = 0; y < 128; y++) {
									mapCanvas.setPixel(x, y, Byte.parseByte(data[index]));
									index++;
								}
							}
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		addCameraRecipe();

		Utils.loadColors();
		getCommand("takePicture").setExecutor(new CameraCommands());
		registerListeners(new CameraClick(), new CameraPlace());
		
		
	}

	private void registerListeners(Listener... listeners) {
		Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
	}

	public static Camera getInstance() {
		return instance;
	}

	public void addCameraRecipe() {

		ItemStack camera = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta cameraMeta = (SkullMeta) camera.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), "");
		profile.getProperties().put("textures", new Property("textures",
				"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZiNWVlZTQwYzNkZDY2ODNjZWM4ZGQxYzZjM2ZjMWIxZjAxMzcxNzg2NjNkNzYxMDljZmUxMmVkN2JmMjc4ZSJ9fX0=="));
		Field profileField = null;
		cameraMeta.setDisplayName(ChatColor.DARK_BLUE + "Camera");
		try {
			profileField = cameraMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(cameraMeta, profile);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		camera.setItemMeta(cameraMeta);

		ShapedRecipe recipe = new ShapedRecipe(camera);
		recipe.shape("IGI", "ITI", "IRI");

		recipe.setIngredient('I', Material.IRON_INGOT);
		recipe.setIngredient('G', Material.GLASS_PANE);
		recipe.setIngredient('T', Material.GLOWSTONE_DUST);
		recipe.setIngredient('R', Material.REDSTONE);

		getServer().addRecipe(recipe);
	}

	private void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		File mapDir = new File(getDataFolder(), "maps");
		if (!mapDir.exists()) {
			mapDir.mkdir();
		}
	}
}
