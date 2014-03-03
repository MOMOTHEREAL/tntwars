package me.momo.tntwars.util;

public class Messages {
	public static String PREFIX = "§4[§7TNT§4]§r ";
	public static String DEATH_MSG(String par0) {
		String r = "";
		r = PREFIX + "§4§lDEATH!§r §aPlayer §l" + par0 + "§r§a is eleminated!";
		return r;
	}
	public static String PLAYER_NOT_ONLINE = PREFIX + "§cThat player connot be found.";
	public static String UNKNOWN_TEAM = PREFIX + "§cUnknown team; use Blue or Red.";
	public static String SPEC_MSG = PREFIX + "§6You are now a spectator; please consider drinking invisibility potions and staying on your side.";
	public static String SCOREBOARD_TITLE = "§8--[§4§lTNTWars§r§8]--";
	public static String SCOREBOARD_TIME = "§5§lTime left";
	public static String SCOREBOARD_TEAM = "§5§lYour team";
	public static String SCOREBOARD_TEAM_BLUE = "§3Blue";
	public static String SCOREBOARD_TEAM_RED = "§cRed";
	public static String SCOREBOARD_RESET = PREFIX + "§aReseted all scoreboards.";
	public static String SCOREBOARD_HEALTH = "§5§lHealth";
	public static String SCOREBOARD_HUNGER = "§5§lHunger";
	public static String ERR_SPEC_INTERACT = PREFIX + "§4You cannot do that while being a spectator!";
	
	//Region stuff
	public static String REGION_PREFIX = "§8[§3Regions§8]§r ";
	public static String REGION_NOBUILD = REGION_PREFIX + "§cYou cannot build here!";
	public static String REGION_POS1 = REGION_PREFIX + "§6Position 1 set!";
	public static String REGION_POS2 = REGION_PREFIX + "§6Position 2 set!";
	public static String REGION_CREATE_SUCCESS = REGION_PREFIX + "§aRegion created!";
	public static String REGION_CREATE_ERR_NOTSAMEWORLD = REGION_PREFIX + "§4Could not create region:§c Wand points are not in the same world.";
	public static String REGION_CREATE_ERR_EXISTS = REGION_PREFIX + "§4Could not create region:§c Region already exists.";
	public static String REGION_CREATE_ERR_NONAME = REGION_PREFIX + "§4Could not create region:§c Please specify a name.";
	public static String REGION_LIST_ERR_NOREGION = REGION_PREFIX + "§4Could not list regions:§c No regions found.";
	public static String REGION_DELETE_ERR_EXISTS = REGION_PREFIX + "§4Could not delete region:§c Unknown region.";
	public static String REGION_DELETE_SUCCESS = REGION_PREFIX + "§aRegion deleted!";
	public static String REGION_DELETE_ERR_NONAME = REGION_PREFIX + "§4Could not delete region:§c Please specify a name.";
	public static String REGION_WAND_SUCCESS = REGION_PREFIX + "§aYou recieved a wand!";
	public static String REGION_BIND_TEAM_SUCCESS = REGION_PREFIX + "§aSuccessfully binded region!";
	public static String REGION_BIND_ERR_BIND = REGION_PREFIX + "§4Could not bind region:§c Unknown bind.";
	public static String REGION_BIND_ERR_EXISTS = REGION_PREFIX + "§4Could not bind region:§c Unknown region.";
	public static String REGION_UNKNOWN_COMMAND = REGION_PREFIX + "§cUnknown command. Type /region for help.";
	public static String REGION_INFO_TITLE = REGION_PREFIX + "§3Showing info of region:";
	public static String REGION_INFO_ERR_EXISTS = REGION_PREFIX + "§4Could not show info of region:§c Unknown region.";
	public static String REGION_INFO_ERR_NONAME = REGION_PREFIX + "§4Could not show info of region:§c Please specify a name.";
	
	
	
	
}
