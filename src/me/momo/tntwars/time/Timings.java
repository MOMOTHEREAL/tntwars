package me.momo.tntwars.time;


import java.util.Arrays;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.scoreboard.InfoScoreboard;
import me.momo.tntwars.util.Messages;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Timings {
	public static TNTWars tnt;
	
	public static void prepTimer(){
		TNTWars.setPhase(Phases.PREP);;
		TNTWars.setTimeLeft(300);
		for (Player p : Bukkit.getOnlinePlayers()){
			p.getInventory().clear();
			p.getInventory().setHelmet(new ItemStack(Material.AIR));
			p.getInventory().setChestplate(new ItemStack(Material.AIR));
			p.getInventory().setLeggings(new ItemStack(Material.AIR));
			p.getInventory().setBoots(new ItemStack(Material.AIR));
			p.getInventory().setItem(8, TNTWars.getBlockShopItem());
			p.getInventory().setItem(7, TNTWars.getRulesBook());
			p.setExp(0);
			if (TNTWars.getRedTeam().contains(p.getName())){
				if (!(TNTWars.getRedSpawnpoint() == null)){
					p.teleport(TNTWars.getRedSpawnpoint());
				}
			}
			if (TNTWars.getBlueTeam().contains(p.getName())){
				if (!(TNTWars.getBlueSpawnpoint() == null)){
					p.teleport(TNTWars.getBlueSpawnpoint());
				}
			}
			
		}
		tnt.getServer().getScheduler().scheduleSyncRepeatingTask(tnt, new Runnable() {
			public void run() {
				if (TNTWars.getPhase() == Phases.PREP) { 
					
					for (Player p : tnt.getServer().getOnlinePlayers()){
						p.setLevel(TNTWars.getTimeLeft());
						
					}
					
					if (TNTWars.getTimeLeft() == 300 || TNTWars.getTimeLeft() == 240 || TNTWars.getTimeLeft() == 180 || TNTWars.getTimeLeft() == 120 || TNTWars.getTimeLeft() == 60) { // Checks
						int minute = TNTWars.getTimeLeft() / 60;
						tnt.getServer().broadcastMessage(Messages.PREFIX + "§a" + minute + " minutes left until §6§lfire§r§a!");
					}
					if (TNTWars.getTimeLeft() == 30 || TNTWars.getTimeLeft() == 15 || TNTWars.getTimeLeft() == 10 || TNTWars.getTimeLeft() == 5 || TNTWars.getTimeLeft() == 4 || TNTWars.getTimeLeft() == 3 || TNTWars.getTimeLeft() == 2 || TNTWars.getTimeLeft() == 1) { // Checks
																																																																	// if // be
																																																																	// printed.
						tnt.getServer().broadcastMessage(Messages.PREFIX + "§a" + TNTWars.getTimeLeft() + " seconds left until §6§lfire§r§a!");
						for (Player p : tnt.getServer().getOnlinePlayers()) {
							p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1F, 0.7F);
						}
					}
					if (TNTWars.getTimeLeft() > 0) {
						TNTWars.removeSeconds(1);;
					}
					if (TNTWars.getTimeLeft() == 0) {
						TNTWars.setPhase(Phases.FIGHT);
						for (Player p : tnt.getServer().getOnlinePlayers()) {
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 0.7F);
							p.setLevel(0);
							if (TNTWars.getBlueTeam().contains(p.getName()) || TNTWars.getRedTeam().contains(p.getName())){
								InfoScoreboard.updateScoreboard(p);
							}
						}
						tnt.getServer().broadcastMessage(Messages.PREFIX + "§6§lFire in the hole§r§a!");
					}
				}
				for (Player p : tnt.getServer().getOnlinePlayers()){
					if (TNTWars.getBlueTeam().contains(p.getName()) || TNTWars.getRedTeam().contains(p.getName())){
						InfoScoreboard.updateScoreboard(p);
					}
				}
			}
		}, 0L, 20L);
	}
}
