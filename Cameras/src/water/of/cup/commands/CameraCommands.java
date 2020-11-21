package water.of.cup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import water.of.cup.Picture;

public class CameraCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("takepicture") && p.isOp()) {
            
            Picture.takePicture(p);
        }
        return true;
    }

}
