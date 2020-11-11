package fr.ikkino.mapsaver.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

import fr.ikkino.mapsaver.MapSaverPlugin;
import fr.ikkino.mapsaver.renderer.CopyRenderer;

public class SaveMap implements CommandExecutor {
	
	public SaveMap(MapSaverPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Command for player only");
			return true;
		}
		
		Player player = (Player) sender;
		ItemStack stack = player.getInventory().getItemInMainHand();
		
		if (stack.getType() != Material.FILLED_MAP) {
			sender.sendMessage("You need to have a filled map in your hand");
			return true;
		}
		
		if (!(stack.getItemMeta() instanceof MapMeta)) {
			sender.sendMessage("Error getting map Meta");
			this.plugin.getLogger().warning("Player: " + player.getName() + " tried to save " + stack.getType().toString() + " which was considered a map, but itemMeta is not valid!");
			return true;
		}
		
		MapMeta mapMeta = (MapMeta) stack.getItemMeta();
		MapView mapView = mapMeta.getMapView();
		
		mapView.addRenderer(new CopyRenderer(this.plugin));
		return true;
	}
	
	public MapSaverPlugin plugin;

}
