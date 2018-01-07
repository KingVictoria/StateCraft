package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import group.Group;
import group.Member;

/**
 * A Serializable object which represents the relationships between all players and groups
 * @author KingVictoria
 */
public class RelationMap implements Serializable {
	
	private static final long serialVersionUID = 4890142365609441126L;
	
	private HashMap<Group, ArrayList<Member>> relations;
	private ArrayList<Member> ungrouped;
	
	/**
	 * Creates a RelationMap
	 */
	public RelationMap() {
		relations = new HashMap<Group, ArrayList<Member>>();
		ungrouped = new ArrayList<>();
	}
	
	/**
	 * Adds a new group to the RelationMap
	 * @param group
	 */
	public void addGroup(Group group) {
		if(!relations.keySet().contains(group)) relations.put(group, new ArrayList<Member>());
	}
	
	/**
	 * Removes a group from the RelationMap
	 * @param group
	 */
	public void removeGroup(Group group) {
		if(relations.keySet().contains(group)) relations.remove(group);
	}
	
	/**
	 * Adds a Member to a Group in the RelationMap
	 * @param group
	 * @param member
	 */
	public void addMember(Group group, Member member) {
		if(!relations.get(group).contains(member)) relations.get(group).add(member);
		if(ungrouped.contains(member)) ungrouped.remove(member);
	}
	
	/**
	 * Removes a Member from a Group in the RelationMap
	 * @param group
	 * @param member
	 */
	public void removeMember(Group group, Member member) {
		if(relations.get(group).contains(member)) relations.get(group).remove(member);
		if(member.getGroups().size() == 0) ungrouped.add(member);
	}
	
	/**
	 * Returns true of the specified Group as that Member
	 * @param group
	 * @param member
	 * @return
	 */
	public boolean hasMember(Group group, Member member) {
		return relations.get(group).contains(member);
	}
	
	public boolean isMember(Player player) {
		if(listAllMembers().contains(new Member(player.getUniqueId()))) return true;
		return false;
	}
	
	/**
	 * Makes a member object for a player -- used upon first join
	 * @param player
	 */
	public void makeMember(Player player) {
		ungrouped.add(new Member(player.getUniqueId()));
	}
	
	/**
	 * Lists all Groups a specified Member is in
	 * @param member
	 * @return ArrayList<Group>
	 */
	public ArrayList<Group> listGroups(Member member) {
		ArrayList<Group> groups = new ArrayList<Group>();
		for(Group group: listAllGroups())
			if(relations.get(group).contains(member)) groups.add(group);
		return groups;
	}
	
	/**
	 * Lists all Groups
	 * @return ArrayList<Group>
	 */
	public ArrayList<Group> listAllGroups() {
		ArrayList<Group> groups = new ArrayList<Group>(relations.keySet());
		Collections.sort(groups, (lhs, rhs) -> {
			return lhs.getName().compareTo(rhs.getName());
		});
		return groups;
	}
	
	/**
	 * Returns an ArrayList of all Members in a Group
	 * @param group
	 * @return
	 */
	public ArrayList<Member> listMembers(Group group) {
		return relations.get(group);
	}
	
	/**
	 * Returns an ArrayList of all Members not in any Groups
	 * @return
	 */
	public ArrayList<Member> listUngrouped() {
		return ungrouped;
	}
	
	/**
	 * Returns a list of All Members (all players, effectively)
	 * @return
	 */
	public ArrayList<Member> listAllMembers() {
		ArrayList<Member> members = new ArrayList<Member>();
		for(Group group: relations.keySet())
			for(Member member: relations.get(group))
				if(!members.contains(member)) members.add(member);
		for(Member member: ungrouped) members.add(member);
		return members;
	}
	
	/**
	 * Gets the Member object of a Player
	 * @param uuid
	 * @return
	 */
	public Member getMember(UUID uuid) {
		for(Member member: listAllMembers())
			if(member.getUUID().equals(uuid)) return member;
		for(Member member: ungrouped)
			if(member.getUUID().equals(uuid)) return member;
		return new Member(uuid);
	}

}
