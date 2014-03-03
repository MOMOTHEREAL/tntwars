package me.momo.tntwars.region;

import me.momo.tntwars.TNTWars;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Region {
	private Location p1;
	private Location p2;
	private String name;
	private String creator;
	
	

	public Region(String name, Location p1, Location p2, String creator) {
		this.name = name;
		this.p1 = p1;
		this.p2 = p2;
		this.creator = creator;
		
	}
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Location getP1() {
		return p1;
	}

	public void setP1(Location p1) {
		this.p1 = p1;
	}

	public Location getP2() {
		return p2;
	}

	public void setP2(Location p2) {
		this.p2 = p2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBind() {
		if (getName() == TNTWars.getRedRegion().getName()){
			return "Red";
		}
		else if (getName() == TNTWars.getBlueRegion().getName()){
			return "Blue";
		}
		
		return "None";
	}
	
	public String getP1ToString() {
		return "" + getP1().getBlockX() + ":" + getP1().getBlockY() + ":" + getP1().getBlockZ() + ", world " + getP1().getWorld().getName();
	}
	
	public String getP2ToString() {
		return "" + getP2().getBlockX() + ":" + getP2().getBlockY() + ":" + getP2().getBlockZ() + ", world " + getP2().getWorld().getName();
	}
	
	@Override
	public String toString() {
		String pos1 = getP1().getWorld().getName() + ":" + getP1().getBlockX() + ":" + getP1().getBlockY() + ":" + getP1().getBlockZ();
		String pos2 = getP2().getWorld().getName() + ":" + getP2().getBlockX() + ":" + getP2().getBlockY() + ":" + getP2().getBlockZ();
		String creator = getCreator();
		String name = getName();
		String sep = "!";
		return pos1 + sep + pos2 + sep + creator + sep + name;
	}
	
	public static Region toRegion(String par0){
		String[] data = par0.split("!");
		
		String pos1raw = data[0];
		String[] pos1cord = pos1raw.split(":");
		Location pos1 = new Location(Bukkit.getWorld(pos1cord[0]), Integer.parseInt(pos1cord[1]), Integer.parseInt(pos1cord[2]), Integer.parseInt(pos1cord[3]));
		
		String pos2raw = data[1];
		String[] pos2cord = pos2raw.split(":");
		Location pos2 = new Location(Bukkit.getWorld(pos2cord[0]), Integer.parseInt(pos2cord[1]), Integer.parseInt(pos2cord[2]), Integer.parseInt(pos2cord[3]));
		
		String creator = data[2];
		String name = data[3];
		
		Region region = new Region(name, pos1, pos2, creator);
		
		
		
		return region;
	}

	
	
	
	
	
}
