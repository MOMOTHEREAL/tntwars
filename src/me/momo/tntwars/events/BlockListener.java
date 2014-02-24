package me.momo.tntwars.events;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.util.Messages;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class BlockListener implements Listener{
	
	@EventHandler
	public void onOverMaxHeight(BlockPlaceEvent e){
		Block b = e.getBlock();
		Location l = b.getLocation();
		Player p = e.getPlayer();
		if (!(p.getGameMode() == GameMode.CREATIVE)){
			if (l.getBlockY() > TNTWars.getMaxHeight()) {
				e.setCancelled(true);
				p.sendMessage(Messages.PREFIX + "§cYou cannot build above the wall!");
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
	
}
