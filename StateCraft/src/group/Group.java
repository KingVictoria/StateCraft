package group;

import java.io.Serializable;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import main.RelationMap;
import main.StateCraft;

public class Group implements Serializable {

	private static final long serialVersionUID = 1352686046249094975L;
	
	private String name;
	
	public static RelationMap relations() {
		return StateCraft.getInstance().getRelations();
	}
	
	public Group(Player player, String name) {
		relations().addGroup(this);
		relations().addMember(this, relations().getMember(player.getUniqueId()));
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the ArrayList of Member objects
	 * @return ArrayList<Member>
	 */
	public ArrayList<Member> members() {
		return relations().listMembers(this);
	}

}
