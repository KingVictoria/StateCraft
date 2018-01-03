package group;

import java.util.UUID;

import org.bukkit.Bukkit;

public class Member {
	
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
	 * Determines whether two Member objects have the same UUID
	 * @param member Member object
	 * @return true if objects have the same UUID
	 */
	public boolean equals(Member member) {
		if(member.id.equals(id)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return id.toString();
	}

}
