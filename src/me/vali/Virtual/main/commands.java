package me.vali.Virtual.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityTNTPrimed;

public class commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(cmd.getName().equalsIgnoreCase("vl") || cmd.getName().equalsIgnoreCase("virtual") || cmd.getName().equalsIgnoreCase("virtuallib")) {
			if(sender instanceof Player) {
				String[] a = {"security", "bc", "gm"};
				
				Player p = (Player) sender;
				if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("Security")) {
						if(p.hasPermission("VirtualLib.Securty.*")) {
							if(args.length > 1) {
								
							}else {
								String[] as = {"test", "check", "settings"};
								start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, as, p);
							}
						}
					}else if(args[0].equalsIgnoreCase("bc")) {
						
					}else if(args[0].equalsIgnoreCase("gm")) {//TODO TEST PERM !REMOVE!
						if(p.hasPermission("Minecraft.commands.gamemode.*")) {
							if(args.length > 1) {
								if(args[1] == "0" || args[1] == "s" || args[1] == "survival") {
									p.setGameMode(GameMode.SURVIVAL);
								}else if(args[1] == "1" || args[1] == "c" || args[1] == "creative") {
									p.setGameMode(GameMode.CREATIVE);
								}else if(args[1] == "2" || args[1] == "a" || args[1] == "adventure") {
									p.setGameMode(GameMode.ADVENTURE);
								}else if(args[1] == "3" || args[1] == "sp" || args[1] == "spectator") {
									p.setGameMode(GameMode.SPECTATOR);
								}else {
									String[] as = {"0","1","2","3"};
									start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, as, p);
								}
							}else {
								p.setGameMode(GameMode.CREATIVE);
							}
						}else {
							start.instance.defaultLib.VirtualLogger.noPerm(p);
						}
					}else if(args[0].equalsIgnoreCase("logger")) {
						
					}else if(args[0].equalsIgnoreCase("removeEntity")) {
						if(p.hasPermission("VirtualLib.removeEntity")) {
							for(Entity e : p.getWorld().getEntities()) {
								if(e instanceof EntityTNTPrimed) {
									e.remove();
								}
							}
						}
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else if(args[0].equalsIgnoreCase("NÖ")) {
						
					}else {
						start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, a, p);
					}
				}else {
					start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, a, p);
				}
			}else {
				
			}
		}else if(cmd.getName().equalsIgnoreCase("coins")) { // /coins set JoMinecrafter 112
			if(args.length >= 1) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					if(args.length >= 2) {
						if(args[0].equalsIgnoreCase("set")) {
							if(p.hasPermission("Coins.set")) {
								if(args.length == 3) {
									Player t = Bukkit.getPlayer(args[1]);
									Integer a = null;
									try {
										a = Integer.valueOf(args[2]);
									} catch (Exception e) {
										start.instance.defaultLib.VirtualLogger.err(args[2] + " ist keine gültige Zahl!", p);
									}
									if(t == null) {
										start.instance.defaultLib.VirtualLogger.err("Der Spieler " + args[1] + " ist nicht Online!", p);
									}else {
										start.coinsapi.set(t, a);
										start.instance.defaultLib.VirtualLogger.msg("Der Spieler " + t.getName() + " jetzt " + start.coinsapi.get(t) + " Coins!", p);
									}
								}else {
									String[] a = {"player", "Anzahl"};
									start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, "set", a, p);
								}
							}
						}else if(args[0].equalsIgnoreCase("remove")) {
							if(p.hasPermission("Coins.remove")) {
								if(args.length == 3) {
									Player t = Bukkit.getPlayer(args[1]);
									Integer a = null;
									try {
										a = Integer.valueOf(args[2]);
									} catch (Exception e) {
										start.instance.defaultLib.VirtualLogger.err(args[2] + " ist keine gültige Zahl!", p);
									}
									if(t == null) {
										start.instance.defaultLib.VirtualLogger.err("Der Spieler " + args[1] + " ist nicht Online!", p);
									}else {
										start.coinsapi.remove(t, a);
										start.instance.defaultLib.VirtualLogger.msg("Der Spieler " + t.getName() + " jetzt " + start.coinsapi.get(t) + " Coins!", p);
									}
								}else {
									String[] a = {"player", "Anzahl"};
									start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, "remove", a, p);
								}
							}
						}else if(args[0].equalsIgnoreCase("add")) {
							if(p.hasPermission("Coins.add")) {
								if(args.length == 3) {
									Player t = Bukkit.getPlayer(args[1]);
									Integer a = null;
									try {
										a = Integer.valueOf(args[2]);
									} catch (Exception e) {
										start.instance.defaultLib.VirtualLogger.err(args[2] + " ist keine gültige Zahl!", p);
									}
									if(t == null) {
										start.instance.defaultLib.VirtualLogger.err("Der Spieler " + args[1] + " ist nicht Online!", p);
									}else {
										start.coinsapi.add(t, a);
										start.instance.defaultLib.VirtualLogger.msg("Der Spieler " + t.getName() + " jetzt " + start.coinsapi.get(t) + " Coins!", p);
									}
								}else {
									String[] a = {"player", "Anzahl"};
									start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, "add", a, p);
								}
							}
						}else if(args[0].equalsIgnoreCase("clear")) {
							if(p.hasPermission("Coins.clear")) {
								if(args.length == 2) {
									Player t = Bukkit.getPlayer(args[1]);
									if(t == null) {
										start.instance.defaultLib.VirtualLogger.err("Der Spieler " + args[1] + " ist nicht Online!", p);
									}else {
										start.coinsapi.clear(t);
										start.instance.defaultLib.VirtualLogger.msg("Der Spieler " + t.getName() + " jetzt " + start.coinsapi.get(t) + " Coins!", p);
									}
								}else {
									String[] a = {"player"};
									start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, "clear", a, p);
								}
							}
						}else {
							String[] a = {"set","add","get","remove","clear"};
							start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, a, p);
						}
					}else {
						String[] a = {"set","add","get","remove","clear"};
						start.instance.defaultLib.VirtualLogger.cmdSyntax(cmd, a, p);
					}
				}
			}else {
				if(sender instanceof Player) {
					start.instance.defaultLib.VirtualLogger.coins("Du hast §6" + start.coinsapi.get((Player) sender) + " §eCoins!", (Player) sender);
				}
			}
		}
		return false;
	}

}
