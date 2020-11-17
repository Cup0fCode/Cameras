package water.of.cup;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.util.Vector;

public class Picture {
	public static boolean takePicture(Player p) {

		ItemStack itemStack = new ItemStack(Material.FILLED_MAP); // requires api-version: 1.13 in plugin.yml
        MapMeta mapMeta = (MapMeta) itemStack.getItemMeta();
        MapView mapView = Bukkit.createMap(p.getWorld()); // or Bukkit.getMap(int id); or another existing MapView
        //mapView.setCenterX(p.getLocation().getBlockX());
        //mapView.setCenterZ(p.getLocation().getBlockZ());
        //mapView.setScale(Scale.NORMAL);
        mapView.setTrackingPosition(false);
        //MapCanvas mapCanvas = new MapCanvas();
        for(MapRenderer renderer : mapView.getRenderers())
            mapView.removeRenderer(renderer);

        Renderer customRenderer = new Renderer();

        mapView.addRenderer(customRenderer);

		mapMeta.setMapView(mapView);
		
//		itemStack.setDurability((short) mapView.getId());
        // mapMeta.set...
        itemStack.setItemMeta(mapMeta);
        p.getInventory().addItem(itemStack);
		
		
		return true;
	}
}
