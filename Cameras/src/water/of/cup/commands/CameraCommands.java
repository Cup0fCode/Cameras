package water.of.cup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import water.of.cup.Camera;
import water.of.cup.Picture;

import java.util.HashMap;

public class CameraCommands implements CommandExecutor {

    private Camera instance = Camera.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("takepicture")) {
            if(instance.getConfig().getBoolean("settings.camera.permissions")) {
                if(!p.hasPermission("cameras.command")) return false;
            }

            Picture.takePicture(p);
            return true;
        }

        return false;
    }

}
