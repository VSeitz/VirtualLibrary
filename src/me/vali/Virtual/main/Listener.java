package me.vali.Virtual.main;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class Listener implements org.bukkit.event.Listener {

	public Listener() {
		Bukkit.getPluginManager().registerEvents(this, start.instance);
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onCommandProceed(PlayerCommandPreprocessEvent e) {
		//if(e.getPlayer().isOp() || e.getPlayer().hasPermission("*")) {
			String cmd = e.getMessage();
			String[] disable = {"/pl","/?","/plugins","/bukkit","/me"};
			for(String s : disable) {
				if(cmd.startsWith(s + " ")) {
					if(!e.getPlayer().hasPermission("*") && !e.getPlayer().isOp() && !e.getPlayer().hasPermission("VirtualLib.command" + s.replaceFirst("/", ""))) {
						e.setCancelled(true);
						e.getPlayer().sendMessage("§4Bouiders.net | §ckeine Rechte!");
					}
				}
			}
		//}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if(e.getPlayer().isOp() || e.getPlayer().hasPermission("VirtualLib.ChatColor")) {
			if(!e.getMessage().startsWith("/") ) {
				e.getMessage().replaceAll("&", "§");
			}
		}
	}
	
	//@EventHandler(priority=EventPriority.HIGHEST)
	  public void onPlayerLogin(PlayerLoginEvent event)
	  {
	    if (!event.getAddress().getHostAddress().equals("127.0.0.1")) {
	      event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§2You joined threw the Proxy!");
	    }
	  }
	
}
