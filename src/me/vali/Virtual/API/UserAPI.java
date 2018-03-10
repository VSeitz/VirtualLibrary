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
import org.bukkit.event.player.PlayerLoginEvent;

import me.vali.Virtual.main.start;

public class UserAPI implements Listener {

	private static Connection conn;
	private static String host;
	private static int port;
	private static String user;
	private static String pass;
	private static String database;
	public static UserAPI instance;
	
	
	public UserAPI() {
		Bukkit.getPluginManager().registerEvents(this, start.instance);
		host = "localhost";
		port = 3306;
		database = "Stats";
		user = "Stats";
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
		if(instance == null) instance = new UserAPI();
	}
	
	public static void setHost(String host) {
		UserAPI.host = host;
	}
	
	public static void setPort(int port) {
		UserAPI.port = port;
	}
	
	public static void setDatabase(String name) {
		UserAPI.database = name;
	}
	
	public static void setUser(String username) {
		UserAPI.user = username;
	}
	
	public static void setPassword(String password) {
		UserAPI.pass = password;
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
			//TODO IP == PROXY !BUNGEE API
			state.executeUpdate("CREATE TABLE IF NOT EXISTS `users` (`player_name` VARCHAR(20) ,`player_uuid` VARCHAR(36), `last_ip` VARCHAR(16), `last_login` MEDIUMTEXT, `connections` INT)");
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
	   
	public static UserAPI getInstance() {
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
	public void onJoin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		boolean exist = false;
		try {
			ResultSet rs = Query("SELECT `last_login` FROM `users` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				exist = Boolean.valueOf(true).booleanValue();
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		
		if(!exist) {
			//TODO IP == PROXY !BUNGEE API
			Update("INSERT INTO `users` (`player_name`, `player_uuid`, `last_ip`, `last_login`, `connections`) VALUES ('" + p.getName() + "', '" + p.getUniqueId() + "', '" + e.getAddress() + "', '" + System.currentTimeMillis() + "', '" + 1 + "')");
		}
		
		if (exist) {
			//TODO IP == PROXY !BUNGEE API
			//TODO add connection!
			Update("UPDATE `users` SET `player_name`='" + p.getName() + "' WHERE `player_uuid`='" + p.getUniqueId() + "'");
		}
	}
	 
	   //////////////////////////////////////////////////////////////////////////////////////////////////
	   
	public Integer getlastIP(Player p) {
		int c = 0;
		try {
			ResultSet rs = this.Query("SELECT `last_ip` FROM `users` WHERE `player_uuid`='" + p.getUniqueId() + "'");
			while (rs.next()) {
				c = rs.getInt(1);
			}
		} catch (Exception err) {
			System.err.println(err);
			err.printStackTrace();
		}
		
		return c;
	}

	
}
