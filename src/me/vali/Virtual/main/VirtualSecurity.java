package me.vali.Virtual.main;

import org.bukkit.plugin.Plugin;

public class VirtualSecurity {
	
	public VirtualSecurity(Plugin p) {
		enable(p);
	}
	
	private Boolean ExceptionHandler = false;
	
	public void enable(Plugin p) {
		System.out.println("************* Enabeling Virtual Security System *************");
		
		ExceptionHandler = true;
		System.out.println(" - ExceptionHandler [" + ExceptionHandler + "]");
		
		System.out.println("**************** Enabled Virtual Security System ****************");
	}
	
	public void disable() {
		System.out.println("************* Disabeling Virtual Security System *************");
		System.out.println("************** Disabled Virtual Security System **************");
	}
	
	public void mainException(Exception ex, ExceptionHandle howtohandle) {
		switch (howtohandle) {
		case Safe:
			
			break;
		case Error:
			
			break;
		case Normal:
	
			break;
		case Reset:
	
			break;
		case Retry:
	
			break;
		default:
			break;
		}
	}
	
	public enum ExceptionHandle {
		Safe,Error,Normal,Reset,Retry;
	}
	
}
