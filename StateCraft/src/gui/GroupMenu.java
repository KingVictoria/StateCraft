package gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import group.Group;
import main.RelationMap;
import main.StateCraft;
import prompt.SingleStringPrompt;

public class GroupMenu {
	
	/**
	 * Primary Group IconMenu
	 * @param player
	 */
	public static void primary(Player player) {
		IconMenu menu = new IconMenu("Groups", 9, e -> {
			if(e.getName().equals("Shortcut")) {
				e.setWillClose(false);
				// Group Shortcut
			} else if(e.getName().equals("List")) {
				e.setWillClose(false);
				list(player);
			} else if(e.getName().equals("Create")) {
				create(player);
			} else if(e.getName().equals("Info")) {
				// Group Information
			} else if(e.getName().equals("Exit")) {
				e.setWillClose(true);
			}
			e.setWillDestroy(true);
		}, StateCraft.getInstance());
		
		menu.setOption(0, new ItemStack(Material.ARROW), "Shortcut", "Shortcut Group")
			.setOption(2, new ItemStack(Material.COMPASS), "List", "List of all Groups")
			.setOption(4, new ItemStack(Material.ANVIL), "Create", "Create a Group")
			.setOption(6, new ItemStack(Material.BOOK), "Info", "Groups Information Booklet")
			.setOption(8, new ItemStack(Material.ACACIA_DOOR_ITEM), "Exit", "Closes the Menu");
		
		menu.open(player);
	}
	
	/**
	 * Creates a group, first prompting the player for a Group Name
	 * @param player
	 */
	public static void create(Player player) {
		SingleStringPrompt.doConversation("Enter a Name for the Group:", player, a -> {
			Group newGroup = new Group(player, a);	// Create New Group
			specific(player, newGroup);				// Open the Specific GroupMenu
		});
	}
	
	/**
	 * Specific Group IconMenu
	 * @param player
	 * @param group
	 */
	public static void specific(Player player, Group group) {
		IconMenu menu = new IconMenu(group.getName(), 54, e -> {
			
			e.setWillDestroy(true);
		}, StateCraft.getInstance())
				.setOption(0, new ItemStack(Material.CAULDRON_ITEM), "Group Options", "Group Options Menu")
				.setOption(1, new ItemStack(Material.ARROW), "Set as Shortcut", "Sets this Group to be your Main Menu Shortcut");
	
		for(int i = 0; i < group.members().size(); i++)
			menu.setOption(9 + i, new ItemStack(Material.IRON_CHESTPLATE), group.members().get(i).getName(), "Le Group Member");
		
		menu.open(player);
	}
	
	/**
	 * Group List IconMenu
	 * @param player
	 */
	public static void list(Player player) {
		IconMenu menu = new IconMenu("List", 54, e -> {
			if(relations().hasMember(relations().listAllGroups().get(e.getPosition()), relations().getMember(player.getUniqueId()))) {
				e.setWillClose(false);
				specific(player, relations().listAllGroups().get(e.getPosition()));
			}
			
			e.setWillDestroy(true);
		}, StateCraft.getInstance());
		
		for(int i = 0; i < relations().listAllGroups().size(); i++)
			menu.setOption(i, new ItemStack(Material.BANNER), relations().listAllGroups().get(i).getName(), "Le Group");
		
		menu.open(player);
	}
	
	public static RelationMap relations() {
		return StateCraft.getInstance().getRelations();
	}

}
