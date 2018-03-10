package me.vali.Virtual.main;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.vali.Virtual.API.CoinAPI;
import me.vali.Virtual.API.UserAPI;
import me.vali.Virtual.util.VoidWorld;

public class start extends JavaPlugin {

	public static start instance;
	
	public VirtualLibrary defaultLib;
	public Listener listener;
	
	public static CoinAPI coinsapi;
	public static VirtualSecurity virtualsecurity;
	public static UserAPI userapi;
	
	@Override
	public void onEnable() {
		instance = this;
		
		defaultLib = new VirtualLibrary("VirtualLibrary", this, "§2[§aVirtualLibrary§2]§a", "§a");
		
		listener = new Listener();
		
		virtualsecurity = new VirtualSecurity(this);
		coinsapi = new CoinAPI();
		userapi = new UserAPI();
		
		cmd();
		
		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---- Enabled VirtualLibrary Plugin by vali10e ----");
	}
	
	public void cmd() {
		this.getCommand("voidworld").setExecutor(new VoidWorld());
		this.getCommand("vl").setExecutor(new commands());
		this.getCommand("virtual").setExecutor(new commands());
		this.getCommand("virtuallib").setExecutor(new commands());
		this.getCommand("coins").setExecutor(new commands());
	}
	
	@Override
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "---- Disabled VirtualLibrary Plugin by vali10e ----");
	}
	
}
