package water.of.cup.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class CameraPlace implements Listener {
	@EventHandler
	public void cameraPlaced(BlockPlaceEvent e) {
		//Prevent players from placing Cameras

		ItemStack item = e.getItemInHand();
		if(!item.hasItemMeta()) return;
		if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "Camera")) {
			e.setCancelled(true);
		}
	}
}
