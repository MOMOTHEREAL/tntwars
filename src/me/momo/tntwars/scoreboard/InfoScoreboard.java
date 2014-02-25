package me.momo.tntwars.scoreboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.util.Messages;

public class InfoScoreboard {
	public static TNTWars plugin;
	public static HashMap<String, Scoreboard> getScoreboards = new HashMap<String, Scoreboard>();

	public static void updateScoreboard(Player p) {
		p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective info = sb.registerNewObjective("info", "dummy");
		info.setDisplayName(Messages.SCOREBOARD_TITLE);
		info.setDisplaySlot(DisplaySlot.SIDEBAR);
		if (TNTWars.getPhase() == Phases.PREP) {
			info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TIME)).setScore(0);
			info.getScore(Bukkit.getOfflinePlayer("§6" + TNTWars.getTimeLeft())).setScore(-1);
			info.getScore(Bukkit.getOfflinePlayer("")).setScore(-2);
			info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TEAM)).setScore(-3);
			if (TNTWars.getBlueTeam().contains(p.getName())) {
				info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TEAM_BLUE)).setScore(-4);
			} else if (TNTWars.getRedTeam().contains(p.getName())) {
				info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TEAM_RED)).setScore(-4);
			}
		}
		if (TNTWars.getPhase() == Phases.FIGHT) {
			info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TEAM)).setScore(0);
			if (TNTWars.getBlueTeam().contains(p.getName())) {
				info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TEAM_BLUE)).setScore(-1);
			} else if (TNTWars.getRedTeam().contains(p.getName())) {
				info.getScore(Bukkit.getOfflinePlayer(Messages.SCOREBOARD_TEAM_RED)).setScore(-1);
			}
		}
		p.setScoreboard(sb);

	}
}
