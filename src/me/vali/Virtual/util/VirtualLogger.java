package me.vali.Virtual.util;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.vali.Virtual.main.VirtualLibrary;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class VirtualLogger {

	private String prefix;
	private String consolenprefix;
	private String ph = "§m================================";
	private String noperm = "§4Dazu hast du keine Rechte!";
	private String color;
	
	private VirtualLibrary lib;
	
	public VirtualLogger(VirtualLibrary lib) {
		this.setLib(lib);
		
		color = lib.color;
		prefix = lib.prefix;
		consolenprefix = lib.consolenprefix;
	}
	
	public void msg(String msg, Player p) {
		p.sendMessage(prefix + msg);
	}
	
	public void info(String msg, Player p) {
		p.sendMessage(prefix + " §2[§a!§2]§7 " + msg);
	}
	
	public void info(String msg) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(prefix + " §2[§a!§2]§7 " + msg);
		}
	}
	
	public void msg(String msg) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(prefix + msg);
		}
	}

	public void err(String msg, Player p) {
		p.sendMessage(prefix + msg);
	}
	
	public void cmdSyntax(Command cmd, String sub, String[] aliases, Player p) {
		String s = prefix + "/" + cmd.getName() + " " + sub;
		for(String a : aliases) {
			s = s + " <" + a + ">";
		}
		p.sendMessage(ph);
		p.sendMessage(s);
		if(cmd.getDescription() != null)
			p.sendMessage(cmd.getDescription());
		p.sendMessage(ph);
	}
	
	public void cmdSyntax(Command cmd, String[] aliases, Player p) {
		String s = prefix + "/" + cmd.getName();
		for(String a : aliases) {
			s = s + " <" + a + ">";
		}
		p.sendMessage(ph);
		p.sendMessage(s);
		if(cmd.getDescription() != null)
			p.sendMessage(cmd.getDescription());
		p.sendMessage(ph);
	}
	
	public void noPerm(Player p) {
		p.sendMessage(noperm);
	}
	
	public void console(String msg, Boolean error) {
		if(!error) {
			System.out.println(consolenprefix + msg);
		}else {
			System.err.println(consolenprefix + msg);
		}
	}
	
	public void bcmsg(String msg1, String msg2, String msg3, String msg4, String color, String colorph) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(this.color + getPH(colorph));
			p.sendMessage(" ");
			if(msg1 != null) p.sendMessage(color + msg1);
			if(msg2 != null) p.sendMessage(color + msg2);
			if(msg3 != null) p.sendMessage(color + msg3);
			if(msg4 != null) p.sendMessage(color + msg4);
			p.sendMessage(" ");
			p.sendMessage(this.color + getPH(colorph));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void title(String msg1, String msg2, Player p) {
		p.sendTitle(msg1, msg2);
	}

	public String getPH(String color) {
		return color + ph;
	}
	
	public void list(String msg, Player p) {
		p.sendMessage(" §7- §7" + msg);
	}
	
	public void msgwop(String msg, Player p) {
		p.sendMessage(msg);
	}
	
	public void d(String msg) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getName() == "vali10e") {
				p.sendMessage("§2Debug: §a" + msg);
			}
		}
	}
	
	public void debug(String msg) {
		this.d(msg);
	}

	public void coins(String msg, Player p) {
		p.sendMessage("§6[Coins] §e" + msg);
	}

	 public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
         PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
         PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
         connection.sendPacket(packetPlayOutTimes);
         if (subtitle != null)
         {
           subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
           subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
           IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
           PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
           connection.sendPacket(packetPlayOutSubTitle);
         }
         if (title != null)
         {
           title = title.replaceAll("%player%", player.getDisplayName());
           title = ChatColor.translateAlternateColorCodes('&', title);
           IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
           PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
           connection.sendPacket(packetPlayOutTitle);
         }
            
     }
    
     public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
         PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
         PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
         connection.sendPacket(packetPlayOutTimes);
         if (title != null)
         {
           title = title.replaceAll("%player%", player.getDisplayName());
           title = ChatColor.translateAlternateColorCodes('&', title);
           IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
           PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
           connection.sendPacket(packetPlayOutTitle);
         }
            
     }
    
       public static void sendTabTitle(Player player, String header, String footer)
       {
         if (header == null) {
           header = "";
         }
         header = ChatColor.translateAlternateColorCodes('&', header);
         if (footer == null) {
           footer = "";
         }
         footer = ChatColor.translateAlternateColorCodes('&', footer);
        
         header = header.replaceAll("%player%", player.getDisplayName());
         footer = footer.replaceAll("%player%", player.getDisplayName());
        
         PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
         IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \"" + header + "\"}");
         IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
         PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
         try
         {
           Field field = headerPacket.getClass().getDeclaredField("b");
           field.setAccessible(true);
           field.set(headerPacket, tabFoot);
         }
         catch (Exception e)
         {
           e.printStackTrace();
         }
         finally
         {
           connection.sendPacket(headerPacket);
         }
       }
       
       public static void sendPlayerAnnouncement(Player p, String msg) {
   	    String s = ChatColor.translateAlternateColorCodes('&', msg);
   	    IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + 
   	      "\"}");
   	    PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
   	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
   	}

	public VirtualLibrary getLib() {
		return lib;
	}

	public void setLib(VirtualLibrary lib) {
		this.lib = lib;
	}
	
}
