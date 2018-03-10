package me.vali.Virtual.main;

import org.bukkit.plugin.Plugin;

import me.vali.Virtual.API.CoinAPI;
import me.vali.Virtual.util.VirtualInventoryUtils;
import me.vali.Virtual.util.VirtualItemUtils;
import me.vali.Virtual.util.VirtualJavaUtils;
import me.vali.Virtual.util.VirtualLogger;
import me.vali.Virtual.util.VirtualMathUtils;
import me.vali.Virtual.util.VirtualPlayerUtils;

public class VirtualLibrary {

	public VirtualItemUtils VirtualItemUtils;
	public VirtualInventoryUtils VirtualInventoryUtils;
	public VirtualMathUtils VirtualMathUtils;
	public VirtualPlayerUtils VirtualPlayerUtils;
	public VirtualJavaUtils VirtualJavaUtils;
	
	public VirtualLogger VirtualLogger;
	
	public CoinAPI coinsapi;
	
	public String prefix;
	public String color;
	public String consolenprefix;
	
	public Plugin pl;
	
	public VirtualLibrary(String pluginname, Plugin main, String prefix, String color){
		this.pl = main;
		
		this.color = color;
		this.prefix = prefix;
		
		VirtualItemUtils = new VirtualItemUtils();
		VirtualInventoryUtils = new VirtualInventoryUtils();
		VirtualMathUtils = new VirtualMathUtils();
		VirtualPlayerUtils = new VirtualPlayerUtils();
		VirtualJavaUtils = new VirtualJavaUtils();
		
		VirtualLogger = new VirtualLogger(this);
		
		this.coinsapi = start.coinsapi;
	}
	
}
