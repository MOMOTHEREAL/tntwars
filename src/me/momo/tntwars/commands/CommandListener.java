package me.momo.tntwars.commands;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.time.Timings;
import me.momo.tntwars.util.Messages;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class CommandListener implements CommandExecutor {

	public static TNTWars tnt;

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		if (c.getName().equalsIgnoreCase("tntstart")) {
			Timings.prepTimer();
		}
		if (c.getName().equalsIgnoreCase("tntshop")) {
			Player p = (Player) s;
			p.getInventory().addItem(TNTWars.getBlockShopItem());
		}
		if (c.getName().equalsIgnoreCase("tntrules")) {
			Player p = (Player) s;
			p.getInventory().addItem(TNTWars.getRulesBook());
		}
		if (c.getName().equalsIgnoreCase("tntsetlobby")) {
			Player p = (Player) s;
			tnt.setLobby(p.getLocation());
			p.sendMessage(Messages.PREFIX + "§aLobby location has been saved to your current location.");
		}
		if (c.getName().equalsIgnoreCase("tntjoin")) {
			Player p = (Player) s;

			if (!(TNTWars.getLobby() == null)) {
				p.teleport(TNTWars.getLobby());
				p.sendMessage(Messages.PREFIX + "§aTeleported you to the lobby.");
			}

		}
		if (c.getName().equalsIgnoreCase("tntteam")) {
			Player p = (Player) s;
			if (p.isOp()) {
				if (a.length > 0) {
					if (a.length == 1) {
						if (a[0].equalsIgnoreCase("reset")) {
							TNTWars.getRedTeam().clear();
							TNTWars.getBlueTeam().clear();
							TNTWars.getSpectators().clear();
							for (Player players : Bukkit.getOnlinePlayers()) {
								players.setPlayerListName(players.getName());
								p.sendMessage(Messages.PREFIX + "§aReseted " + players.getName() + "'s team.");
								players.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
								p.sendMessage(Messages.SCOREBOARD_RESET);
							}
						}
					} else {
						if (a.length == 3) {
							if (a[0].equalsIgnoreCase("join")) {
								if (a[1].equalsIgnoreCase("red")) {
									if (Bukkit.getPlayer(a[2]) != null) {
										TNTWars.getRedTeam().add(a[2]);
										Bukkit.getPlayer(a[2]).setPlayerListName("§c" + Bukkit.getPlayer(a[2]).getName());
										p.sendMessage(Messages.PREFIX + "§aAdded player to §cRed§a team.");
									} else {
										p.sendMessage(Messages.PLAYER_NOT_ONLINE);
									}
								} else if (a[1].equalsIgnoreCase("blue")) {
									if (Bukkit.getPlayer(a[2]) != null) {
										TNTWars.getBlueTeam().add(a[2]);
										Bukkit.getPlayer(a[2]).setPlayerListName("§b" + Bukkit.getPlayer(a[2]).getName());
										p.sendMessage(Messages.PREFIX + "§aAdded player to §bBlue§a team.");
									} else {
										p.sendMessage(Messages.PLAYER_NOT_ONLINE);
									}
								} else {
									p.sendMessage(Messages.UNKNOWN_TEAM);
								}
							}
						} else {
							if (a.length == 2) {
								if (a[0].equalsIgnoreCase("setspawn")) {
									if (a[1].equalsIgnoreCase("blue")) {
										tnt.setBlueSpawnpoint(p.getLocation());
										p.sendMessage(Messages.PREFIX + "§aSuccessfully set the §bBlue§a spawnpoint to your location.");
									} else if (a[1].equalsIgnoreCase("red")) {
										tnt.setRedSpawnpoint(p.getLocation());
										p.sendMessage(Messages.PREFIX + "§aSuccessfully set the §cRed§a spawnpoint to your location.");
									} else {
										p.sendMessage(Messages.UNKNOWN_TEAM);
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

}
