package me.momo.tntwars.events;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.util.Messages;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
	public static TNTWars tnt;
	@EventHandler
	public void onAttackPlayer(EntityDamageByEntityEvent e){
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			if (!(TNTWars.getPhase() == Phases.PRE)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerDie(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (TNTWars.getPhase() == Phases.FIGHT && (TNTWars.getBlueTeam().contains(p.getName()) || TNTWars.getRedTeam().contains(p.getName())) && (!TNTWars.getSpectators().contains(p.getName()))){
			e.setDeathMessage("");
			e.setDeathMessage(Messages.DEATH_MSG(p.getName()));
			TNTWars.getSpectators().add(p.getName());
		}
		if (TNTWars.getPhase() == Phases.PREP) {
			e.setDeathMessage("");
		}
		
		
	}
	
	@EventHandler
	public void onBlockBreakSpectator(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (TNTWars.getSpectators().contains(p.getName())) {
			e.setCancelled(true);
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
				p.sendMessage(Messages.SPEC_MSG); //Now a spectator
				p.getInventory().clear();
				p.getInventory().getHelmet().setType(Material.AIR);
				p.getInventory().getChestplate().setType(Material.AIR);
				p.getInventory().getLeggings().setType(Material.AIR);
				p.getInventory().getBoots().setType(Material.AIR);
				p.getInventory().setItem(0, new ItemStack(Material.POTION, 1, (byte)8270)); //Invis potion
			}
		}
	
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		if (TNTWars.getSpectators().contains(p.getName())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Action a = e.getAction();
		if (a == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (TNTWars.getSpectators().contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
	
}
