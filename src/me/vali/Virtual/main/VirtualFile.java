package me.vali.Virtual.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class VirtualFile extends YamlConfiguration {

	private File file;
	private VirtualLibrary lib;
	
	public VirtualFile(VirtualLibrary lib, String name) {
		if(!lib.pl.getDataFolder().exists()) {
			lib.pl.getDataFolder().mkdir();
		}
		this.setLib(lib);
		file = new File(lib.pl.getDataFolder().getAbsolutePath() + "/" + name + ".yml");
		load();
	}
	
	public void c() {
		if (!file.exists()) {  
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
				start.instance.defaultLib.VirtualLogger.console("Error while creating file " + file.getName(), true);
			}
		}
	}
	
	public void save() {
		c();
		
		try {
			save(file);
		} catch (IOException e) {
			e.printStackTrace();
			start.instance.defaultLib.VirtualLogger.console("Error while saving file " + file.getName(), true);
		}
	}
	
	public void load() {
		c();
		
		try {
            load(file);
            options().header("Virtual Files by vali10e! # \n copyright vali10e");
            options().copyDefaults(true);
            save();
        } catch (Exception ex) {
            ex.printStackTrace();
            start.instance.defaultLib.VirtualLogger.console("VirtualFile " + file.getName() + " konnte nicht geladen werden!", true);
        }
	}

	public VirtualLibrary getLib() {
		return lib;
	}

	public void setLib(VirtualLibrary lib) {
		this.lib = lib;
	}
	
}
