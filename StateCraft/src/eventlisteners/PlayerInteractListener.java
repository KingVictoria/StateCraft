package eventlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import gui.GroupMenu;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
	
		// TEMPORARY LAUNCH FOR GROUP MENU
		if(e.getPlayer().isSneaking() ) {
			GroupMenu.primary(e.getPlayer());
		}
	}

}
