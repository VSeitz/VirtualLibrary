package me.vali.defaultProject.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.vali.Virtual.main.VirtualLibrary;
import me.vali.Virtual.util.VirtualItemUtils;
import me.vali.Virtual.util.VirtualJavaUtils;
import me.vali.Virtual.util.VirtualLogger;
import me.vali.Virtual.util.VirtualMathUtils;
import me.vali.Virtual.util.VirtualPlayerUtils;

public class start extends JavaPlugin implements Listener {

	public static start instance;
	public static VirtualLibrary lib;
	public static VirtualLogger logger;
	public static VirtualItemUtils itemutils;
	public static VirtualPlayerUtils playerutils;
	public static VirtualJavaUtils javautils;
	public static VirtualMathUtils mathutils;
	
	public static items items;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		
		instance = this;
		
		lib = new VirtualLibrary("BeispielPlugin", this, "§aBeispielPrefix §2", "§2");
		logger = lib.VirtualLogger;
		itemutils = lib.VirtualItemUtils;
		playerutils = lib.VirtualPlayerUtils;
		javautils = lib.VirtualJavaUtils;
		mathutils = lib.VirtualMathUtils;
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
