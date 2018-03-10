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
import org.bukkit.event.player.PlayerJoinEvent;

import me.vali.Virtual.event.CoinsChangedEvent;
import me.vali.Virtual.main.start;

public class StatsAPI {

	private static Connection conn;
	private static String host;
	private static int port;
	private static String user;
	private static String pass;
	private static String database;
	public static StatsAPI instance;
	private static String game;
	
	
	public StatsAPI(String game) {
		StatsAPI.game = game;
		host = "localhost";
		port = 3306;
		database = "Stats";
		user = "Stats";//ERSTELLEN
		pass = "ICHUNDDUANDISKUHKARINASESELDASBISTDU";
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
		if(instance == null) instance = new StatsAPI(game);
	}
	
	public static void setHost(String host) {
		StatsAPI.host = host;
	}
	
	public static void setPort(int port) {
		StatsAPI.port = port;
	}
	
	public static void setDatabase(String name) {
		StatsAPI.database = name;
	}
	
	public static void setUser(String username) {
		StatsAPI.user = username;
	}
	
	public static void setPassword(String password) {
		StatsAPI.pass = password;
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
			state.executeUpdate("CREATE TABLE IF NOT EXISTS `knockstats` (`player_name` VARCHAR(20) ,`player_uuid` VARCHAR(36), `kills` INT, `deaths` INT, `points` INT)");
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
	   
	public static StatsAPI getInstance() {
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
			ResultSet rs = Query("SELECT `knockstats` FROM `points` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				exist = Boolean.valueOf(true).booleanValue();
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		
		if(!exist) {
			Update("INSERT INTO `knockstats` (`player_name`, `player_uuid`, `kills`, `deaths`, `points`) VALUES ('" + p.getName() + "', '" + p.getUniqueId() + "', '" + 0 + "', '" + 0 + "', '" + 0 + "')");
		}
		
		if (exist) {
			Update("UPDATE `knockstats` SET `player_name`='" + p.getName() + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		}
	}
	 
	   //////////////////////////////////////////////////////////////////////////////////////////////////
	   
	public Integer getKills(Player p) {
		int c = 0;
		try {
			ResultSet rs = this.Query("SELECT `knockstats` FROM `kills` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		return c;
	}
	
	public Integer getDeaths(Player p) {
		int c = 0;
		try {
			ResultSet rs = this.Query("SELECT `knockstats` FROM `deaths` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		return c;
	}
	
	public Integer getPoints(Player p) {
		int c = 0;
		try {
			ResultSet rs = this.Query("SELECT `knockstats` FROM `points` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		return c;
	}
	
	public void addKill(Player p, int v) {
		int c = getKills(p);
		Integer old = c;
		c += v;
		this.Update("UPDATE `knockstats` SET `kills`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void addDeath(Player p, int v) {
		int c = getDeaths(p);
		Integer old = c;
		c += v;
		this.Update("UPDATE `knockstats` SET `deaths`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void addPunkte(Player p, int v) {
		int c = getPoints(p);
		Integer old = c;
		c += v;
		this.Update("UPDATE `knockstats` SET `points`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void removePunkte(Player p, int v) {
		int c = getPoints(p);
		Integer old = c;
		c -= v;
		this.Update("UPDATE `knockstats` SET `points`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
	public void setPunkte(Player p, int v) {
		int c = v;
		Integer old = c;
		this.Update("UPDATE `knockstats` SET `points`='" + c + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		Bukkit.getPluginManager().callEvent(new CoinsChangedEvent(p, old, c));
	}
	
}
