package me.vali.Virtual.util;

import org.bukkit.Location;

public class VirtualPlayerUtils {

	public Location get8PointYaw(Location loc) {
		Integer yaw = null;
		Integer pitch = null;
		
		Float y = loc.getYaw() + 180;
		
		
		if(y > -22.5 && y <= 22.5) {// 0
			yaw = 0;
		}else if(y > 22.5 && y <= 45 + 22.5) {// 45
			yaw = 45;
		}else if(y > 45 + 22.5 && y <= 90 + 22.5) {// 90
			yaw = 90;
		}else if(y > 90 + 22.5 && y <= 90 + 45 + 22.5) {// 90 + 45
			yaw = 90 + 45;
		}else if(y > -22.5 + 180 && y <= 22.5 + 180) {// 0
			yaw = 0 + 180;
		}else if(y > 22.5 + 180 && y <= 45 + 22.5 + 180) {// 45
			yaw = 45 + 180;
		}else if(y > 45 + 22.5 + 180 && y <= 90 + 22.5 + 180) {// 90
			yaw = 90 + 180;
		}else if(y > 90 + 22.5 + 180 && y <= 90 + 45 + 22.5 + 180) {// 90 + 45
			yaw = 90 + 45 + 180;
		}else {
			yaw = 0;
		}
		
		pitch = 0;
		
		System.out.println(yaw);
		System.out.println(pitch);
		
		Location end = new Location(loc.getWorld(), loc.getBlockX() + 0.50, loc.getBlockY() + 0.50, loc.getBlockZ() + 0.50, yaw, pitch);
		return end;
	}
	
	public Location middleBlock(Location loc) {
		Location end = new Location(loc.getWorld(), loc.getBlockX() + 0.5, loc.getBlockY() + 0.5, loc.getBlockZ() + 0.5, loc.getYaw(), loc.getPitch());
		return end;
	}
	
	public Location middle8Yaw(Location loc) {
		return get8PointYaw(middleBlock(loc));
	}
	
}
