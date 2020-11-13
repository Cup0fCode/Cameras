package water.of.cup;

import java.util.Arrays;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import water.of.cup.commands.CameraCommands;
import water.of.cup.listeners.PlayerInteract;

public class Camera extends JavaPlugin {

	@Override
	public void onEnable() {
		Utils.loadColors();
		// getLogger().info("Loaded PokeCraft");
		getCommand("takePicture").setExecutor(new CameraCommands());

		registerListeners(new PlayerInteract());
	}

	private void registerListeners(Listener... listeners) {
		Arrays.stream(listeners).forEach(listener -> getServer().getPluginManager().registerEvents(listener, this));
	}
}
