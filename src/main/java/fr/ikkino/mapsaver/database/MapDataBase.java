package fr.ikkino.mapsaver.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;

import fr.ikkino.mapsaver.MapSaverPlugin;

public class MapDataBase {
	
	public MapDataBase(MapSaverPlugin plugin, String host, String database, String username, String password, int port) {
		this.plugin = plugin;
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	
	public void connect() throws SQLException {
		if ((connection != null) && (!connection.isClosed()))
			return;
		
		synchronized (this) {
			if ((connection != null) && (!connection.isClosed()))
				return;
			
			this.connection = DriverManager.getConnection("jdbc:mariadb://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
		}
	}
	
	private void updating(String sqlUpdate) {
		BukkitRunnable runnable = new BukkitRunnable() {
			
			@Override
			public void run() {
				try {
					connect();
					Statement statement = connection.createStatement();
					statement.executeUpdate(sqlUpdate);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		
		runnable.runTaskAsynchronously(this.plugin);
	}
	
	public boolean addNewMap(String username, String mapName, List<Byte> rawMap) {
		this.updating("INSERT INTO SavedMap (PLAYERNAME, MAPNAME, RAWMAP) VALUES ('" + username + "', '" + mapName + "', '" + rawMap.toString() + "');");
		return true;
	}
	
	private MapSaverPlugin plugin;

	private Connection connection = null;
	
	private String host;
	private String database;
	private String username;
	private String password;
	private int port;
	
}
