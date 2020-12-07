package water.of.cup.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import water.of.cup.Camera;

public class PrepareItemCraft implements Listener {

    private Camera instance = Camera.getInstance();

    @EventHandler
    public void prepareItemCraft(PrepareItemCraftEvent event) {
        if(event.getRecipe() != null) {
            if(event.getRecipe().getResult().getItemMeta().getDisplayName().equals(ChatColor.DARK_BLUE + "Camera")) {
                if(instance.getConfig().getBoolean("settings.camera.permissions")) {
                    for(HumanEntity he : event.getViewers()) {
                        if(he instanceof Player) {
                            if(!he.hasPermission("cameras.craft")) {
                                event.getInventory().setResult(new ItemStack(Material.AIR));
                            }
                        }
                    }

                }
            }
        }
    }

}
