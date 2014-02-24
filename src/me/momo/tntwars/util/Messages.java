package me.momo.tntwars.util;

public class Messages {
	public static String PREFIX = "§4[§7TNT§4]§r ";
	public static String DEATH_MSG(String par0) {
		String r = "";
		r = PREFIX + "§4§lDEATH!§r §aPlayer §l" + par0 + "§r§a is eleminated!";
		return r;
	}
}
