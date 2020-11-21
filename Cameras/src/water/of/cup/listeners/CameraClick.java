package water.of.cup.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import water.of.cup.Picture;

public class CameraClick implements Listener {
	@EventHandler
	public void cameraClicked(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& e.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "Camera")) {
			if (p.getInventory().firstEmpty() == -1) {
				p.sendMessage("You can not take a picture with a full inventory");
				return;
			}
			if (p.getInventory().contains(Material.PAPER)) {
				Map<Integer, ? extends ItemStack> paperHash = p.getInventory().all(Material.PAPER);
				for (ItemStack item : paperHash.values()) {
					item.setAmount(item.getAmount() - 1);
					break;
				}
				Picture.takePicture(p);
			} else {
				p.sendMessage("You must have paper in order to take a picture");
			}
			
		}
	}
}
