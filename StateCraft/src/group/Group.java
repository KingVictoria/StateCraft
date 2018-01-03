package group;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Group {
	
	private ArrayList<Member> members;
	
	public Group(Player player) {
		members = new ArrayList<Member>();
		members.add(new Member(player.getUniqueId()));
	}

}
