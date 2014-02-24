package me.momo.tntwars.events;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.util.Messages;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {
	public static TNTWars tnt;
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
	
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (TNTWars.getPhase() == Phases.FIGHT && (TNTWars.getBlueTeam().contains(p.getName()) || TNTWars.getRedTeam().contains(p.getName()))){
			e.setDeathMessage("");
			e.setDeathMessage(Messages.DEATH_MSG(p.getName()));
			
		}
		
		
	}
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		if (TNTWars.getPhase() == Phases.PREP || TNTWars.getPhase() == Phases.FIGHT) {
			if (TNTWars.getBlueTeam().contains(p.getName())){
				e.setRespawnLocation(TNTWars.getBlueSpawnpoint());
			}
			if (TNTWars.getRedTeam().contains(p.getName())){
				e.setRespawnLocation(TNTWars.getRedSpawnpoint());
			}
			if (TNTWars.getPhase() == Phases.FIGHT){
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage("You are now a spectator; please consider drinking invisibility potions and staying on your side."); //Now a spectator
			}
		}
	}
}
