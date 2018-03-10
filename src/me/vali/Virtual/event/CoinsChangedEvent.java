package me.vali.Virtual.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CoinsChangedEvent extends Event {

public static HandlerList handlers = new HandlerList();
	
	private Player p;
	private Integer o;
	private Integer a;
	
	public CoinsChangedEvent(Player p, Integer old, Integer amount) {
		this.p = p;
		this.a = amount;
		this.o = old;
	}

	public Integer getCoins() {
		return a;
	}

	public Integer getOldCoins() {
		return o;
	}
	
	public Player getPlayer() {
		return p;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList(){
        return handlers;
	}
	
}
