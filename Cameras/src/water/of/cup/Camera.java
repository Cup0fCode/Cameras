package water.of.cup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import water.of.cup.commands.CameraCommands;

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
								for(int y = 0; y < 128; y++) {
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

		Utils.loadColors();
		getCommand("takePicture").setExecutor(new CameraCommands());
	}

	private void registerListeners(Listener... listeners) {
		Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
	}

	public static Camera getInstance() {
		return instance;
	}

	private void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		File mapDir = new File(getDataFolder(), "maps");
		if(!mapDir.exists()) {
			mapDir.mkdir();
		}
	}
}
