package me.vali.Virtual.main;

import org.bukkit.Location;
import org.bukkit.block.Sign;

public class VirtualSign {

	Location loc;
	Sign s;
	
	public VirtualSign(Location loc) {
		this.s = (Sign) loc.getBlock().getState();
		this.loc = loc;
	}

	public void set(Integer line, String text) {
		s.setLine(line, text);
		update();
	}
	
	public void online(Boolean online) {
		if(online) {
			s.setLine(3, "&a&lOnline");
			update();
		}else {
			s.setLine(3, "&c&lOffline");
			update();
		}
	}
	
	private void update() {
		s.update(true);
	}

	public void setMode(String name) {
		s.setLine(0, "§2[§a" + name + "§2]");
		update();
	}
	
	public void setGame(String name) {
		s.setLine(1, "§a" + name);
		update();
	}
	
	public Location getLoc() {
		return loc;
	}

	public Sign getS() {
		return s;
	}

	public void setOnline(Integer on, Integer max) {
		s.setLine(2, "§2" + on + "§a/§2" + max);
		update();
	}
	
}
