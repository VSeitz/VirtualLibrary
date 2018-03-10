package me.vali.Virtual.util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class VoidWorld implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    if (cmd.getName().equalsIgnoreCase("voidworld")) {
	     if(sender.hasPermission("VirtualLib.VoidWorld")) {
	    	 sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.RED + "VoidWorld" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Help:");
		      sender.sendMessage(ChatColor.GOLD + "/voidworld " + ChatColor.WHITE + "| " + ChatColor.GREEN + "Shows this page!");
		      sender.sendMessage("---");
		      sender.sendMessage(ChatColor.GREEN + "You can create a world with Multiverse:");
		      sender.sendMessage(ChatColor.RED + "/mv create <WorldName> normal -g VirtualLibrary");
		      sender.sendMessage("---");
		      
		      return true;
	     }else {
	    	 
	     }
	    }
	    return true;
	  }
	  
	  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	  {
	    return new VoidWorldGenerator();
	  }
	  
	  public class VoidWorldGenerator
	    extends ChunkGenerator
	  {
	    public VoidWorldGenerator() {}
	    
	    public List<BlockPopulator> getDefaultPopulators(World world)
	    {
	      return Arrays.asList(new BlockPopulator[0]);
	    }
	    
	    public boolean canSpawn(World world, int x, int z)
	    {
	      return true;
	    }
	    
	    public byte[] generate(World world, Random rand, int chunkx, int chunkz)
	    {
	      return new byte[32768];
	    }
	    
	    public Location getFixedSpawnLocation(World world, Random random)
	    {
	      return new Location(world, 0.0D, 128.0D, 0.0D);
	    }
	  }
	
}
