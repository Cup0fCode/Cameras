package water.of.cup.cameras.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import water.of.cup.cameras.Camera;

public class PlayerJoin implements Listener {

    private Camera instance = Camera.getInstance();

    /* Add recipe to new players */
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        if(instance.getConfig().getBoolean("settings.camera.recipe.enabled"))
            event.getPlayer().discoverRecipe(new NamespacedKey(instance, "camera"));
    }

}
