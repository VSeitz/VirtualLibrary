package me.vali.defaultProject.main;

public class Virtual {

	public Virtual(start s) {
		start.instance = s;
		enable();
		check();
	}
	
	private void check() {
		start.instance.getServer().getConsoleSender().sendMessage("§asearching for VirtualLib");
		checkforUpdates();
	}

	public void checkforUpdates() {
		start.instance.getServer().getConsoleSender().sendMessage("§achecking for §2VirtualLib §aUpdates...");
		install();
	}
	
	public void install() {
		start.instance.getServer().getConsoleSender().sendMessage("§aInstalling new Version of Virtual!");
	}

	public void enable() {
		start.instance.getServer().getConsoleSender().sendMessage("§a**** §2Enabeling §a" + start.instance.getName() + " §2(VirtualPlugin) by vali10e §a****");
	}
	
	public void disable() {
		start.instance.getServer().getConsoleSender().sendMessage("§c**** §4Disabeling §c" + start.instance.getName() + " §4(VirtualPlugin) by vali10e §4****");
	}
	
}
