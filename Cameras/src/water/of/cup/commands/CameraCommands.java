package water.of.cup.commands;

import net.minecraft.server.v1_14_R1.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.CraftServer;
import org.bukkit.entity.Player;

import water.of.cup.Picture;

public class CameraCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if(args.length > 0 && args[0].equalsIgnoreCase("nms")) {
            p.sendMessage(ChatColor.RED + "NMS debugging");
            MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
//            minecraftServer.
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("takepicture") && p.isOp()) {
            
            Picture.takePicture(p);
        }
        return true;
    }

}
