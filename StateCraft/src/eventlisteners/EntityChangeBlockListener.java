package eventlisteners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlockListener implements Listener {
	
	@EventHandler
	void onEntityChangeBlock(EntityChangeBlockEvent e) {
		if(e.getBlock().getType() == Material.SAND) {
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			Location loc = e.getBlock().getLocation();
			loc.add(0.5, 0, 0.5);
			e.getBlock().getWorld().spawnFallingBlock(loc, new MaterialData(Material.SAND)).setDropItem(false);
		}
		if(e.getBlock().getType() == Material.GRAVEL) {
			e.setCancelled(true);
			e.getBlock().setType(Material.AIR);
			Location loc = e.getBlock().getLocation();
			loc.add(0.5, 0, 0.5);
			e.getBlock().getWorld().spawnFallingBlock(loc, new MaterialData(Material.GRAVEL)).setDropItem(false);
		}
	}

}
