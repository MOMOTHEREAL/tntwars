package me.momo.tntwars.util;

public class Messages {
	public static String PREFIX = "§4[§7TNT§4]§r ";
	public static String DEATH_MSG(String par0) {
		String r = "";
		r = PREFIX + "§4§lDEATH!§r §aPlayer §l" + par0 + "§r§a is eleminated!";
		return r;
	}
	public static String SPEC_MSG = PREFIX + "You are now a spectator; please consider drinking invisibility potions and staying on your side.";
	public static String SCOREBOARD_TITLE = "§8--[§4§lTNTWars§r§8]--";
	public static String SCOREBOARD_TIME = "§5§lTime left";
	public static String SCOREBOARD_TEAM = "§5§lYour team";
	public static String SCOREBOARD_TEAM_BLUE = "§3Blue";
	public static String SCOREBOARD_TEAM_RED = "§cRed";
	public static String SCOREBOARD_RESET = PREFIX + "§aReseted all scoreboards.";
	public static String SCOREBOARD_HEALTH = "§5§lHealth";
	public static String SCOREBOARD_HUNGER = "§5§lHunger";
}
