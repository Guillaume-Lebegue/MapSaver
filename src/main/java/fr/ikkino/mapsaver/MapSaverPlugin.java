package fr.ikkino.mapsaver;

import org.bukkit.plugin.java.JavaPlugin;

import fr.ikkino.mapsaver.command.UserCommand;

public class MapSaverPlugin extends JavaPlugin {
	
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
	}
	
	@Override
	public void onEnable() {
		if (!this.isEnabled()) {
			this.getLogger().warning("Map Saver Plugin is not available or disabled!");
			return;
		}
		
		this.getLogger().info("Hello world!");
		
		this.getCommand("mapsa").setExecutor(new UserCommand(this));
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Bye world!");
	}
	
	public void setState(boolean enabled) {
		this.setEnabled(enabled);
	}

}
