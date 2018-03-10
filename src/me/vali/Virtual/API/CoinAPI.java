package me.vali.Virtual.API;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.vali.Virtual.event.CoinsChangedEvent;
import me.vali.Virtual.main.start;

public class CoinAPI implements Listener  {

	private static Connection conn;
	private static String host;
	private static int port;
	private static String user;
	private static String pass;
	private static String database;
	public static CoinAPI instance;
	
	
	public CoinAPI() {
		Bukkit.getPluginManager().registerEvents(this, start.instance);
		host = "localhost";
		port = 3306;
		database = "coins";
		user = "coinsuser";
		pass = "ONOFsiofensoifspOFsnef2839ts89fsioeIFpsnpsogie";
		try {
			start.instance.defaultLib.VirtualLogger.console("opening MySQL connection...", false);
			try {
				conn = openConnection();
			} catch (Exception ex) {
				start.instance.defaultLib.VirtualLogger.console("Couldn't open Connection: " + ex.getMessage(), true);
				instance = null;
				return;
			}
			
			try {
				start.instance.defaultLib.VirtualLogger.console("erstelle Tabellen...", false);
				createTable();
			} catch (Exception ex) {
				start.instance.defaultLib.VirtualLogger.console("Couldn't create Table: " + ex.getMessage(), true);
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			start.instance.defaultLib.VirtualLogger.console("Couldn't open Connection: " + ex.getMessage(), true);
			ex.printStackTrace();
		}
		
		instance = this;
	}
	
	public static void load() {
		if(instance == null) instance = new CoinAPI();
	}
	
	public static void setHost(String host) {
		CoinAPI.host = host;
	}
	
	public static void setPort(int port) {
		CoinAPI.port = port;
	}
	
	public static void setDatabase(String name) {
		CoinAPI.database = name;
	}
	
	public static void setUser(String username) {
		CoinAPI.user = username;
	}
	
	public static void setPassword(String password) {
		CoinAPI.pass = password;
	}
	
	public Connection openConnection() throws ConnectException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.err.println(ex);
			ex.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, pass);
			start.instance.defaultLib.VirtualLogger.console("§2connected with Database", false);
			return conn;
		} catch (SQLException ex) {
			start.instance.defaultLib.VirtualLogger.console("§2Database got NO connection!", true);
			ex.printStackTrace();
			throw new ConnectException();
		}
		
	}
	   
	private void createTable() {
		try {
			Statement state = conn.createStatement();
			state.executeUpdate("CREATE TABLE IF NOT EXISTS `players_coins` (`player_name` VARCHAR(20) ,`player_uuid` VARCHAR(36), `coins` INT)");
		} catch (SQLException ex) {
			start.instance.defaultLib.VirtualLogger.console("The table could not be created", true);
			System.err.println(ex);
			ex.printStackTrace();
		}
	}
	
	private void Update(String qry) {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(qry);
			
			stmt.close();
		} catch (Exception ex) {
			try {
				openConnection();
			} catch (ConnectException ex2) {  }
			System.err.println(ex);
		}
	}
	   
	public static CoinAPI getInstance() {
		return instance;
	}
	   
	public static Connection getConnection() {
		return conn;
	}
	
	private ResultSet Query(String qry) {
		ResultSet rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(qry);
		} catch (Exception ex) {
			try {
				openConnection();
			} catch (ConnectException ex2) {  }
			System.err.println(ex);
		}
		
		return rs;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		boolean exist = false;
		try {
			ResultSet rs = Query("SELECT `coins` FROM `players_coins` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				exist = Boolean.valueOf(true).booleanValue();
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		
		if(!exist) {
			Update("INSERT INTO `players_coins` (`player_name`, `player_uuid`, `coins`) VALUES ('" + p.getName() + "', '" + p.getUniqueId() + "', '" + 10000 + "')");
		}
		
		if (exist) {
			Update("UPDATE `players_coins` SET `player_name`='" + p.getName() + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		}
	}
	 
	   //////////////////////////////////////////////////////////////////////////////////////////////////
	   
	public Integer get(Player p) {
		int c = 0;
		try {
			ResultSet rs = this.Query("SELECT `coins` FROM `players_coins` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		
		return c;
	}
	
	public void add(Player p, int coins) {
		int c = get(p);
		Integer old = c;
		c += coins;
		this.Update("UPDATE `players_coins` SET `coins`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void remove(Player p, int coins) {
		int c = get(p);
		Integer old = c;
		c -= coins;
		this.Update("UPDATE `players_coins` SET `coins`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void clear(Player p) {
		int c = 0;
		Integer old = c;
		this.Update("UPDATE `players_coins` SET `coins`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void set(Player p, int coins) {
		int c = coins;
		Integer old = c;
		this.Update("UPDATE `players_coins` SET `coins`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
}
