package eventlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import main.StateCraft;
import net.md_5.bungee.api.ChatColor;

public class JoinEventListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(!StateCraft.getInstance().getRelations().isMember(e.getPlayer())) {
			e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "You awaken...");
			StateCraft.getInstance().getRelations().makeMember(e.getPlayer());
		}
	}
}
