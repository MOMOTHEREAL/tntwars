package me.momo.tntwars.region;

import org.bukkit.Location;

public class WandLocation {
	private Location p1;
	private Location p2;
	
	public WandLocation(Location p1, Location p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Location getP1() {
		return this.p1;
	}
	
	public void setP1(Location p1) {
		this.p1 = p1;
	}
	
	public Location getP2() {
		return this.p2;
	}
	
	public void setP2(Location p2) {
		this.p2 = p2;
	}
	
}
