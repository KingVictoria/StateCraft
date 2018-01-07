package group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;

import main.StateCraft;

public class Member implements Serializable {
	
	private static final long serialVersionUID = 4989121208122118478L;
	
	private UUID id;
	
	/**
	 * Creates a Member object
	 * @param uuid UUID of Player
	 */
	public Member(UUID uuid) {
		id = uuid;
	}
	
	/**
	 * Gets the Member's name
	 * @return String name
	 */
	public String getName() {
		return Bukkit.getPlayer(id).getName();
	}
	
	/**
	 * Gets the Member's UUID
	 * @return
	 */
	public UUID getUUID() {
		return id;
	}
	
	/**
	 * Gets the groups this Member is in
	 * @return
	 */
	public ArrayList<Group> getGroups() {
		return StateCraft.getInstance().getRelations().listGroups(this);
	}
	
	/**
	 * Determines whether two Member objects have the same UUID
	 * @param member Member object
	 * @return true if objects have the same UUID
	 */
	public boolean equals(Member member) {
		if(member.id.equals(id)) return true;
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Member)) return false;
		Member m = (Member) o;
		if(m.getUUID().equals(id)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}

}
