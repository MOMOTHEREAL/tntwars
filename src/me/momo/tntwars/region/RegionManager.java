package me.momo.tntwars.region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionManager {
	
	private static List<Region> regions = new ArrayList<Region>();
	private static Map<String, WandLocation> wandStorage = new HashMap<String, WandLocation>();
	
	
	public static Map<String, WandLocation> getWandStorage() {
		return wandStorage;
	}

	public static void setWandStorage(Map<String, WandLocation> wandStorage) {
		RegionManager.wandStorage = wandStorage;
	}

	public static Region getRegion(String name) {
		for (Region region : getRegions()){
			if (region.getName().equalsIgnoreCase(name)){
				return region;
			}
		}
		return null;
	}
	
	public static List<Region> getRegions() {
		return regions;
	}

	public static void setRegions(List<Region> regions) {
		RegionManager.regions = regions;
	}
	
	
}
