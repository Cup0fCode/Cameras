package water.of.cup;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftBlock;
import org.bukkit.map.MapPalette;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_14_R1.MaterialMapColor;
import net.minecraft.server.v1_14_R1.MinecraftServer;

public class Utils {
	static Map<Material, Color> blocksMap = new HashMap<Material, Color>();

	public static void loadColors() {
		//Materials we don't want to use minecraft images for (could be because the image provides a poor color)
		blocksMap.put(Material.GRASS, new Color(49, 101, 25));
		blocksMap.put(Material.TALL_GRASS, new Color(49, 101, 25));
		blocksMap.put(Material.LARGE_FERN, new Color(49, 101, 25));
		blocksMap.put(Material.FERN, new Color(49, 101, 25));
		blocksMap.put(Material.COBBLESTONE, new Color(130, 130, 130));
		blocksMap.put(Material.COBBLESTONE_STAIRS, new Color(130, 130, 130));
		blocksMap.put(Material.COBBLESTONE_SLAB, new Color(130, 130, 130));
		blocksMap.put(Material.FURNACE, new Color(130, 130, 130));
		blocksMap.put(Material.STONE, new Color(117, 117, 117));
		blocksMap.put(Material.STONE_SLAB, new Color(117, 117, 117));
		blocksMap.put(Material.IRON_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.GOLD_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.REDSTONE_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.DIAMOND_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.COAL_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.EMERALD_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.LAPIS_ORE, new Color(117, 117, 117));
		blocksMap.put(Material.IRON_BLOCK, new Color(236, 236, 236));
		blocksMap.put(Material.GOLD_BLOCK, new Color(243, 223, 75));
		blocksMap.put(Material.REDSTONE_BLOCK, new Color(196, 25, 16));
		blocksMap.put(Material.DIAMOND_BLOCK, new Color(95, 233, 217));
		blocksMap.put(Material.COAL_BLOCK, new Color(19, 19, 19));
		blocksMap.put(Material.EMERALD_BLOCK, new Color(71, 213, 105));
		blocksMap.put(Material.LAPIS_BLOCK, new Color(42, 80, 139));
		blocksMap.put(Material.WATER, new Color(67, 101, 165));
		blocksMap.put(Material.SEAGRASS, new Color(67, 101, 165));
		blocksMap.put(Material.BUBBLE_COLUMN, new Color(67, 101, 165));
		blocksMap.put(Material.TALL_SEAGRASS, new Color(67, 101, 165));
		blocksMap.put(Material.KELP, new Color(67, 101, 165));
		blocksMap.put(Material.GRASS_BLOCK, new Color(82, 129, 69));
		blocksMap.put(Material.DIRT, new Color(168, 120, 83));
		blocksMap.put(Material.SAND, new Color(222, 215, 172));
		blocksMap.put(Material.SANDSTONE, new Color(213, 207, 162));
		blocksMap.put(Material.ACACIA_LEAVES, new Color(73, 181, 24));
		blocksMap.put(Material.BIRCH_LEAVES, new Color(114, 149, 76));
		blocksMap.put(Material.DARK_OAK_LEAVES, new Color(72, 186, 18));
		blocksMap.put(Material.JUNGLE_LEAVES, new Color(74, 185, 25));
		blocksMap.put(Material.OAK_LEAVES, new Color(73, 183, 24));
		blocksMap.put(Material.SPRUCE_LEAVES, new Color(55, 91, 56));
		blocksMap.put(Material.GRASS_PATH, new Color(170, 148, 89));
		blocksMap.put(Material.COARSE_DIRT, new Color(104, 75, 51));
		blocksMap.put(Material.ANDESITE, new Color(136, 136, 138));
		blocksMap.put(Material.DIORITE, new Color(181, 181, 181));
		blocksMap.put(Material.DEAD_BUSH, new Color(144, 97, 39));
		blocksMap.put(Material.CACTUS, new Color(76, 107, 35));
		blocksMap.put(Material.DANDELION, new Color(247, 229, 77));
		blocksMap.put(Material.POPPY, new Color(230, 47, 43));
		blocksMap.put(Material.CORNFLOWER, new Color(70, 106, 235));
		blocksMap.put(Material.AZURE_BLUET, new Color(210, 215, 223));
		blocksMap.put(Material.OXEYE_DAISY, new Color(187, 188, 189));
		blocksMap.put(Material.LAVA, new Color(211, 124, 40));
		blocksMap.put(Material.GRANITE, new Color(156, 111, 91));
		blocksMap.put(Material.REDSTONE_LAMP, new Color(123, 73, 33));
		blocksMap.put(Material.GRAVEL, new Color(139, 135, 134));
		blocksMap.put(Material.SPRUCE_LOG, new Color(48, 34, 25));
		blocksMap.put(Material.OAK_LOG, new Color(58, 35, 9));
		blocksMap.put(Material.BIRCH_LOG, new Color(196, 195, 193));
		blocksMap.put(Material.JUNGLE_LOG, new Color(89, 76, 37));
		blocksMap.put(Material.ACACIA_LOG, new Color(95, 95, 85));
		blocksMap.put(Material.DARK_OAK_LOG, new Color(35, 27, 16));
		blocksMap.put(Material.SPRUCE_PLANKS, new Color(100, 78, 47));
		blocksMap.put(Material.OAK_PLANKS, new Color(172, 140, 88));
		blocksMap.put(Material.BIRCH_PLANKS, new Color(202, 185, 131));
		blocksMap.put(Material.JUNGLE_PLANKS, new Color(172, 124, 89));
		blocksMap.put(Material.ACACIA_PLANKS, new Color(178, 102, 60));
		blocksMap.put(Material.DARK_OAK_PLANKS, new Color(62, 41, 18));
		blocksMap.put(Material.SPRUCE_FENCE, new Color(100, 78, 47));
		blocksMap.put(Material.OAK_FENCE, new Color(172, 140, 88));
		blocksMap.put(Material.BIRCH_FENCE, new Color(202, 185, 131));
		blocksMap.put(Material.JUNGLE_FENCE, new Color(172, 124, 89));
		blocksMap.put(Material.ACACIA_FENCE, new Color(178, 102, 60));
		blocksMap.put(Material.DARK_OAK_FENCE, new Color(62, 41, 18));
		blocksMap.put(Material.SPRUCE_STAIRS, new Color(100, 78, 47));
		blocksMap.put(Material.OAK_STAIRS, new Color(172, 140, 88));
		blocksMap.put(Material.BIRCH_STAIRS, new Color(202, 185, 131));
		blocksMap.put(Material.JUNGLE_STAIRS, new Color(172, 124, 89));
		blocksMap.put(Material.ACACIA_STAIRS, new Color(178, 102, 60));
		blocksMap.put(Material.DARK_OAK_STAIRS, new Color(62, 41, 18));
		blocksMap.put(Material.SPRUCE_SLAB, new Color(100, 78, 47));
		blocksMap.put(Material.OAK_SLAB, new Color(172, 140, 88));
		blocksMap.put(Material.BIRCH_SLAB, new Color(202, 185, 131));
		blocksMap.put(Material.JUNGLE_SLAB, new Color(172, 124, 89));
		blocksMap.put(Material.ACACIA_SLAB, new Color(178, 102, 60));
		blocksMap.put(Material.DARK_OAK_SLAB, new Color(62, 41, 18));
		blocksMap.put(Material.CRAFTING_TABLE, new Color(172, 140, 88));
		blocksMap.put(Material.BOOKSHELF, new Color(172, 140, 88));
		blocksMap.put(Material.SUGAR_CANE, new Color(71, 139, 42));
		blocksMap.put(Material.BEDROCK, new Color(47, 47, 47));
		blocksMap.put(Material.TORCH, new Color(206, 173, 26));
		blocksMap.put(Material.WALL_TORCH, new Color(206, 173, 26));
		blocksMap.put(Material.PUMPKIN, new Color(222, 141, 28));
		blocksMap.put(Material.CARVED_PUMPKIN, new Color(222, 141, 28));
		blocksMap.put(Material.JACK_O_LANTERN, new Color(222, 141, 28));
		blocksMap.put(Material.TNT, new Color(203, 49, 26));
		blocksMap.put(Material.BLACK_WOOL, new Color(6, 7, 12));
		blocksMap.put(Material.WHITE_WOOL, new Color(225, 226, 228));
		blocksMap.put(Material.BLUE_WOOL, new Color(45, 50, 145));
		blocksMap.put(Material.BROWN_WOOL, new Color(105, 70, 39));
		blocksMap.put(Material.CYAN_WOOL, new Color(21, 139, 145));
		blocksMap.put(Material.GRAY_WOOL, new Color(64, 67, 72));
		blocksMap.put(Material.GREEN_WOOL, new Color(83, 108, 20));
		blocksMap.put(Material.LIGHT_BLUE_WOOL, new Color(121, 148, 202));
		blocksMap.put(Material.LIGHT_GRAY_WOOL, new Color(164, 168, 169));
		blocksMap.put(Material.LIME_WOOL, new Color(122, 198, 38));
		blocksMap.put(Material.MAGENTA_WOOL, new Color(188, 66, 179));
		blocksMap.put(Material.ORANGE_WOOL, new Color(240, 125, 30));
		blocksMap.put(Material.PINK_WOOL, new Color(242, 148, 177));
		blocksMap.put(Material.PURPLE_WOOL, new Color(129, 65, 182));
		blocksMap.put(Material.RED_WOOL, new Color(155, 53, 49));
		blocksMap.put(Material.YELLOW_WOOL, new Color(195, 182, 47));
		blocksMap.put(Material.BLACK_BANNER, new Color(6, 7, 12));
		blocksMap.put(Material.WHITE_BANNER, new Color(225, 226, 228));
		blocksMap.put(Material.BLUE_BANNER, new Color(45, 50, 145));
		blocksMap.put(Material.BROWN_BANNER, new Color(105, 70, 39));
		blocksMap.put(Material.CYAN_BANNER, new Color(21, 139, 145));
		blocksMap.put(Material.GRAY_BANNER, new Color(64, 67, 72));
		blocksMap.put(Material.GREEN_BANNER, new Color(83, 108, 20));
		blocksMap.put(Material.LIGHT_BLUE_BANNER, new Color(121, 148, 202));
		blocksMap.put(Material.LIGHT_GRAY_BANNER, new Color(164, 168, 169));
		blocksMap.put(Material.LIME_BANNER, new Color(122, 198, 38));
		blocksMap.put(Material.MAGENTA_BANNER, new Color(188, 66, 179));
		blocksMap.put(Material.ORANGE_BANNER, new Color(240, 125, 30));
		blocksMap.put(Material.PINK_BANNER, new Color(242, 148, 177));
		blocksMap.put(Material.PURPLE_BANNER, new Color(129, 65, 182));
		blocksMap.put(Material.RED_BANNER, new Color(155, 53, 49));
		blocksMap.put(Material.YELLOW_BANNER, new Color(195, 182, 47));
		blocksMap.put(Material.BLACK_WALL_BANNER, new Color(6, 7, 12));
		blocksMap.put(Material.WHITE_WALL_BANNER, new Color(225, 226, 228));
		blocksMap.put(Material.BLUE_WALL_BANNER, new Color(45, 50, 145));
		blocksMap.put(Material.BROWN_WALL_BANNER, new Color(105, 70, 39));
		blocksMap.put(Material.CYAN_WALL_BANNER, new Color(21, 139, 145));
		blocksMap.put(Material.GRAY_WALL_BANNER, new Color(64, 67, 72));
		blocksMap.put(Material.GREEN_WALL_BANNER, new Color(83, 108, 20));
		blocksMap.put(Material.LIGHT_BLUE_WALL_BANNER, new Color(121, 148, 202));
		blocksMap.put(Material.LIGHT_GRAY_WALL_BANNER, new Color(164, 168, 169));
		blocksMap.put(Material.LIME_WALL_BANNER, new Color(122, 198, 38));
		blocksMap.put(Material.MAGENTA_WALL_BANNER, new Color(188, 66, 179));
		blocksMap.put(Material.ORANGE_WALL_BANNER, new Color(240, 125, 30));
		blocksMap.put(Material.PINK_WALL_BANNER, new Color(242, 148, 177));
		blocksMap.put(Material.PURPLE_WALL_BANNER, new Color(129, 65, 182));
		blocksMap.put(Material.RED_WALL_BANNER, new Color(155, 53, 49));
		blocksMap.put(Material.YELLOW_WALL_BANNER, new Color(195, 182, 47));
		blocksMap.put(Material.BLACK_CONCRETE, new Color(7, 9, 14));
		blocksMap.put(Material.WHITE_CONCRETE, new Color(199, 202, 207));
		blocksMap.put(Material.BLUE_CONCRETE, new Color(42, 44, 133));
		blocksMap.put(Material.BROWN_CONCRETE, new Color(91, 57, 30));
		blocksMap.put(Material.CYAN_CONCRETE, new Color(20, 113, 129));
		blocksMap.put(Material.GRAY_CONCRETE, new Color(118, 119, 110));
		blocksMap.put(Material.GREEN_CONCRETE, new Color(88, 156, 25));
		blocksMap.put(Material.LIGHT_BLUE_CONCRETE, new Color(33, 130, 190));
		blocksMap.put(Material.LIGHT_GRAY_CONCRETE, new Color(195, 203, 206));
		blocksMap.put(Material.LIME_CONCRETE, new Color(90, 162, 23));
		blocksMap.put(Material.MAGENTA_CONCRETE, new Color(162, 47, 152));
		blocksMap.put(Material.ORANGE_CONCRETE, new Color(207, 81, 1));
		blocksMap.put(Material.PINK_CONCRETE, new Color(205, 95, 138));
		blocksMap.put(Material.PURPLE_CONCRETE, new Color(155, 45, 145));
		blocksMap.put(Material.RED_CONCRETE, new Color(136, 30, 33));
		blocksMap.put(Material.YELLOW_CONCRETE, new Color(222, 162, 19));
		blocksMap.put(Material.SNOW, new Color(232, 240, 239));
		blocksMap.put(Material.SNOW_BLOCK, new Color(232, 240, 239));
		blocksMap.put(Material.GLASS, new Color(255,255,255));
		blocksMap.put(Material.WHITE_STAINED_GLASS, new Color(255,255,255));
		blocksMap.put(Material.GLASS_PANE, new Color(255,255,255));
		blocksMap.put(Material.WHITE_STAINED_GLASS_PANE, new Color(255,255,255));
	}

	@SuppressWarnings("deprecation")
	public static byte colorFromType(Block block, int light, BlockFace blockFace) {
		HashMap<Material, BufferedImage> imageMap = Camera.getInstance().getResourcePackManager().getImageHashMap();
		if (blocksMap.containsKey(block.getType())) { 
			//if blockMap has a color for the material, use that color
			Color color = blocksMap.get(block.getType());
			return MapPalette.matchColor(color);
		}
		if (imageMap.containsKey(block.getType())) {
			//if imageMap has a color for the material, use that color
			BufferedImage image = imageMap.get(block.getType());
			Color color = new Color(image.getRGB((int) (image.getWidth() / 1.5), (int) (image.getHeight() / 1.5))); //gets certain pixel in image to use as color TODO: Create a hashmap of colors so we don't need to access the image multiple times.
			return MapPalette.matchColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
		}
		return MapPalette.GRAY_2; //no color was found, use gray
		
	}
}
