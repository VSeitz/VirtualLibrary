package me.vali.defaultProject.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(cmd.getName().equalsIgnoreCase("COMMAND")) {
			
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length >= 1) {
					
					if(args[0].equalsIgnoreCase("info")) {
						
					}else if(args[0].equalsIgnoreCase("author")) {
						start.lib.VirtualLogger.msg("Autor: DarkInstance", p);
					}
					
				}else {
					
				}
			}else {
				//CONSOLE
			}
			
		}
		return false;
	}

}
