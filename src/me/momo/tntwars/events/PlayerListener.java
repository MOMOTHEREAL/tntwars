package me.momo.tntwars.events;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onAttackPlayer(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player d = (Player) e.getDamager();
			Player p = (Player) e.getEntity();
			if (TNTWars.getPhase() == Phases.PREP){
				e.setCancelled(true);
			}
		}
	}
}
