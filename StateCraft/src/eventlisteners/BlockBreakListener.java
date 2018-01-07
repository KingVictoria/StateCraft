package eventlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
	
	@EventHandler
	void onBlockBreak(BlockBreakEvent e) {
		e.setDropItems(false);
	}

}
