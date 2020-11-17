package water.of.cup;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class Renderer extends MapRenderer {

	@Override
	public void render(MapView map, MapCanvas canvas, Player player) {
		if (map.isLocked()) {
			return;
		}
		Vector yshift = player.getEyeLocation().getDirection().rotateAroundY(.25 * 2 * 3.14); // .25 in radians

		for (int x = 0; x < 128; x++) {
			for (int y = 0; y < 128; y++) {
				RayTraceResult result = player.getWorld().rayTraceBlocks(player.getEyeLocation(),
						player.getEyeLocation().getDirection().rotateAroundY(((x + 1) * .9 / 128 - .45) * -1)
								.rotateAroundAxis(yshift, ((y + 1) * .9 / 128 - .45)), 
						256);
				

				if (result != null) {
					// System.out.println(result.getHitBlock().getType());
					canvas.setPixel(x, y, Utils.colorFromType(result.getHitBlock().getType(), result.getHitBlock().getLightFromBlocks() + result.getHitBlock().getLightFromSky()));
				} else {
					canvas.setPixel(x, y, MapPalette.PALE_BLUE);
				}
			}
		}

		// TODO: Make async
		byte[][] byteArr = toBytes(canvas);
		MapStorage.store(map.getId(), byteArr);

		map.setLocked(true);
	}

	private static byte[][] toBytes(MapCanvas canvas) {
		byte[][] bytes = new byte[128][128]; // According to doc map size is 128x128
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 0; j < bytes[i].length; j++) {
				bytes[i][j] = canvas.getPixel(i, j);
			}
		}
		return bytes;
	}
}
