package fr.ikkino.mapsaver.renderer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import fr.ikkino.mapsaver.MapSaverPlugin;

public class CopyRenderer extends MapRenderer {
	
	public CopyRenderer(MapSaverPlugin plugin) {
		this.plugin = plugin;
		this.saved = false;
	}
	
	@Override
	public void render(MapView map, MapCanvas canvas, Player player) {
		
		if (this.saved)
			return;

		List<Byte> byteMap = new ArrayList<Byte>();
		
		for (int x = 0; x <= 127; x++) {
			for (int y = 0; y <= 127; y++)
				byteMap.add(canvas.getBasePixel(x, y));
		}
		
		this.plugin.getLogger().info("raw map: " + byteMap.toString());
		this.saved = true;
	}
	
	public MapSaverPlugin plugin;
	public boolean saved;
}
