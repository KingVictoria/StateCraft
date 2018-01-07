package eventlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {
	
	@EventHandler
	void onEntityDeath(EntityDeathEvent e) {
		e.getDrops().clear();
		e.setDroppedExp(0);
	}

}
