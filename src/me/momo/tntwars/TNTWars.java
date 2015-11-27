package me.momo.tntwars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.momo.tntwars.commands.CommandListener;
import me.momo.tntwars.commands.RegionCommand;
import me.momo.tntwars.events.BlockListener;
import me.momo.tntwars.events.InteractListener;
import me.momo.tntwars.events.PlayerListener;
import me.momo.tntwars.inventory.Shop;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.region.Region;
import me.momo.tntwars.region.WandLocation;
import me.momo.tntwars.scoreboard.InfoScoreboard;
import me.momo.tntwars.time.Timings;
import me.momo.tntwars.util.Rules;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TNTWars extends JavaPlugin {
	Shop s = new Shop(this);
	private Inventory shopi = s.shopi;
	private static Phases gamePhase = Phases.PRE; // The variable for the current phase.
	private static int maxHeight = 93;
	private static int gameTimer;
	private static ItemStack rules = new ItemStack(Material.WRITTEN_BOOK); // The variable for the rulesbook item.
	private static ItemStack shop = new ItemStack(Material.EMERALD); // The variable for the shop opener item.
	private static Location blue_spawn = null;
	private static Location red_spawn = null;
	private static Location lobby = null;
	private static List<String> blue_team = new ArrayList<String>();
	private static List<String> red_team = new ArrayList<String>();
	private static List<String> spectators = new ArrayList<String>();
	private static Region redRegion = new Region(null, null, null, null);
	private static Region blueRegion = new Region(null, null, null, null);
	
	
	
	
	public static Region getRedRegion() {
		return redRegion;
	}

	public static void setRedRegion(Region redRegion) {
		TNTWars.redRegion = redRegion;
	}

	public static Region getBlueRegion() {
		return blueRegion;
	}

	public static void setBlueRegion(Region blueRegion) {
		TNTWars.blueRegion = blueRegion;
	}

	/**
	 * @return an Integer that represents the current time left to a phase.
	 */
	public static int getTimeLeft() {
		return gameTimer;
	}

	/**
	 * @return a Phases that represents the current phase.
	 */
	public static Phases getPhase() {
		return gamePhase;
	}

	/**
	 * Sets the time left to the current phase.
	 */
	public static void setTimeLeft(int par0) {
		gameTimer = par0;
	}

	/**
	 * Removes time from the time countdown.
	 */
	public static void removeSeconds(int par0) {
		gameTimer = gameTimer - par0;
	}

	/**
	 * Sets the current phase.
	 */
	public static void setPhase(Phases par0) {
		gamePhase = par0;
	}

	/**
	 * @return the Inventory representing the Block Shop.
	 */
	public Inventory getBlockShop() {
		return shopi;
	}

	/**
	 * @return the ItemStack representing the key to the Block Shop.
	 */
	public static ItemStack getBlockShopItem() {
		return shop;
	}

	/**
	 * @return the ItemStack representing the rulesbook.
	 */
	public static ItemStack getRulesBook() {
		return rules;
	}

	/**
	 * @return an Integer that represents the limit of height the player can build.
	 */
	public static int getMaxHeight() {
		return maxHeight;
	}
	
	/**
	 * 
	 * @return the Location representing the Blue team Spawnpoint.
	 */
	public static Location getBlueSpawnpoint() {
		return blue_spawn;
	}
	
	/**
	 * 
	 * @return the Location representing the Red team Spawnpoint.
	 */
	public static Location getRedSpawnpoint() {
		return red_spawn;
	}
	
	public static Location getLobby() {
		return lobby;
	}
	
	public void setLobby(Location par0) {
		lobby = par0;
		getConfig().set("data.lobby", locToString(par0));
		saveConfig();
	}
	
	public void setRedSpawnpoint(Location par0){
		red_spawn = par0;
		getConfig().set("data.redspawn", locToString(par0));
		saveConfig();
	}
	
	public void setBlueSpawnpoint(Location par0){
		blue_spawn = par0;
		getConfig().set("data.bluespawn", locToString(par0));
		saveConfig();
	}
	
	/**
	 * 
	 * @return a list of all the Blue team's players' names.
	 */
	public static List<String> getBlueTeam() {
		return blue_team;
	}
	
	/**
	 * 
	 * @return a list of all the Red team's players' names.
	 */
	public static List<String> getRedTeam() {
		return red_team;
	}
	
	public static List<String> getSpectators() {
		return spectators;
	}
	

	public void onEnable() {
		InteractListener.tnt = this;
		Timings.tnt = this;
		CommandListener.tnt = this;
		PlayerListener.tnt = this;
		InfoScoreboard.plugin = this;
		this.getCommand("tntstart").setExecutor(new CommandListener());
		this.getCommand("tntshop").setExecutor(new CommandListener());
		this.getCommand("tntrules").setExecutor(new CommandListener());
		this.getCommand("tntteam").setExecutor(new CommandListener());
		this.getCommand("tntjoin").setExecutor(new CommandListener());
		this.getCommand("tntsetlobby").setExecutor(new CommandListener());
		this.getCommand("region").setExecutor(new RegionCommand());
		this.getServer().getPluginManager().registerEvents(new InteractListener(), this);
		this.getServer().getPluginManager().registerEvents(new BlockListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);

		if (!(this.getConfig().getString("data.redspawn") == null)) {
			red_spawn = stringToLoc(this.getConfig().getString("data.redspawn"));
		}
		if (!(this.getConfig().getString("data.bluespawn") == null)) {
			blue_spawn = stringToLoc(this.getConfig().getString("data.bluespawn"));
		}
		if (!(this.getConfig().getString("data.lobby") == null)) {
			lobby = stringToLoc(this.getConfig().getString("data.lobby"));
		}

		{
			ItemMeta meta = shop.getItemMeta();
			meta.setDisplayName("§r§aOpen block shop");
			shop.setItemMeta(meta);
		}
		{
			BookMeta meta = (BookMeta) rules.getItemMeta();
			meta.setTitle(Rules.TITLE);
			meta.setAuthor(Rules.AUTHOR);
			meta.setPages(Rules.PAGES);
			rules.setItemMeta(meta);
		}
		s.setupInventory();
		

		this.getLogger().info("TNTWars has been enabled.");
	}

	public void onDisable() {

		this.getLogger().info("TNTWars has been enabled.");
	}

	// Credits: xTrollxDudex
	public static String locToString(Location l) {
		return l.getWorld().getName() + ":" + l.getBlockX() + ":" + l.getBlockY() + ":" + l.getBlockZ();
	}

	// Credits: xTrollxDudex
	public Location stringToLoc(String s) {
		String[] st = s.split(":");
		return new Location(Bukkit.getServer().getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
	}

}
