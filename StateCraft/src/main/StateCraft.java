package main;

import org.bukkit.plugin.java.JavaPlugin;

import eventlisteners.BlockBreakListener;
import eventlisteners.EntityChangeBlockListener;
import eventlisteners.EntityDeathListener;
import eventlisteners.JoinEventListener;
import eventlisteners.PlayerInteractListener;

public class StateCraft extends JavaPlugin {
	
	private static StateCraft instance;
	private RelationMap relations;
	
	@Override
	public void onEnable() {
		relations = new RelationMap();
		instance = this;
		getServer().getPluginManager().registerEvents(new JoinEventListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		getServer().getPluginManager().registerEvents(new EntityChangeBlockListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	/**
	 * Gets an instance of the Plugin StateCraft object
	 * @return StateCraft
	 */
	public static StateCraft getInstance() {
		return instance;
	}
	
	public RelationMap getRelations() {
		return relations;
	}
}