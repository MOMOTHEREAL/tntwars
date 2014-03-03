package me.momo.tntwars.events;

import java.util.List;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.region.Region;
import me.momo.tntwars.region.RegionManager;
import me.momo.tntwars.util.Messages;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockListener implements Listener{
	
	private static Region getRegionHere(Location loc){
		
		List<Region> regionList = RegionManager.getRegions();
		for (Region rg : regionList){
			Location p1 = rg.getP1();
			Location p2 = rg.getP2();
			
			int minX = p1.getBlockX() < p2.getBlockX() ? p1.getBlockX() : p2.getBlockX();
			int minY = p1.getBlockY() < p2.getBlockY() ? p1.getBlockY() : p2.getBlockY();
			int minZ = p1.getBlockZ() < p2.getBlockZ() ? p1.getBlockZ() : p2.getBlockZ();
			
			
			int maxX = p1.getBlockX() > p2.getBlockX() ? p1.getBlockX() : p2.getBlockX();
			int maxY = p1.getBlockY() > p2.getBlockY() ? p1.getBlockY() : p2.getBlockY();
			int maxZ = p1.getBlockZ() > p2.getBlockZ() ? p1.getBlockZ() : p2.getBlockZ();
			
			if (loc.getBlockX() >= minX && loc.getBlockX() <= maxX){
				if (loc.getBlockY() >= minY && loc.getBlockY() <= maxY){
					if (loc.getBlockZ() >= minZ && loc.getBlockZ() <= maxZ){
						
						
						return rg;
					}
				}
			}
			
		}
		
		return null;
	}
	
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (p.getGameMode() != GameMode.CREATIVE){
			Region rg = getRegionHere(b.getLocation());
			if (rg==null){
				if (TNTWars.getPhase() != Phases.PRE){
					e.setCancelled(true);
					p.sendMessage(Messages.REGION_NOBUILD);
				}
			} else {
				if (TNTWars.getPhase() != Phases.PRE) {

					String name = rg.getName();
					String bluename = TNTWars.getBlueRegion().getName();
					String redname = TNTWars.getRedRegion().getName();
					if ((!(name.equalsIgnoreCase(bluename))) && (!(name.equalsIgnoreCase(redname)))) {
						e.setCancelled(true);
						p.sendMessage(Messages.REGION_NOBUILD);
					}
					
				}
			}
		}
	}
	
	
	
	
	
	@EventHandler
	public void onTNTExplodePreparation(EntityExplodeEvent e){
		if (e.getEntityType() == EntityType.PRIMED_TNT) {
			if (TNTWars.getPhase() == Phases.PREP) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onTNTDamagePreparation(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof TNTPrimed) {
			if (TNTWars.getPhase() == Phases.PREP) {
				e.setCancelled(true);
			}
		}
	}
	
}
