package fr.ikkino.mapsaver.command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.ikkino.mapsaver.MapSaverPlugin;

public class UserCommand implements CommandExecutor {
	
	public UserCommand(MapSaverPlugin plugin) {
		this.plugin = plugin;
		
		this.subCmd = new HashMap<String, CommandExecutor>();
		
		this.registerSubCmd("save", new SaveMap(this.plugin));
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length >= 1) {
			for (Map.Entry<String, CommandExecutor> entry: this.subCmd.entrySet()) {
				
				if (entry.getKey().equals(args[0])) {
					String[] newArgs = new String[args.length - 1];
					
					for (int i = 1; i < args.length; i++)
						newArgs[i - 1] = args[i];
					return entry.getValue().onCommand(sender, command, entry.getKey(), newArgs);
				}
				
			};
		}
		return false;
	}
	
	public void registerSubCmd(String label, CommandExecutor executor) {
		this.subCmd.put(label, executor);
	}
	
	private MapSaverPlugin plugin;
	private Map<String, CommandExecutor> subCmd;

}
