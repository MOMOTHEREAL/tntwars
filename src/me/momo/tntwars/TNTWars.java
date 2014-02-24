package me.momo.tntwars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.momo.tntwars.commands.CommandListener;
import me.momo.tntwars.events.BlockListener;
import me.momo.tntwars.events.InteractListener;
import me.momo.tntwars.events.PlayerListener;
import me.momo.tntwars.phase.Phases;
import me.momo.tntwars.time.Timings;
import me.momo.tntwars.util.Rules;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class TNTWars extends JavaPlugin {
	private static Phases gamePhase = Phases.PRE; // The variable for the current phase.
	private static int maxHeight = 93;
	private static int gameTimer; // The variable for the time left.
	private static ItemStack rules = new ItemStack(Material.WRITTEN_BOOK); // The variable for the rulesbook item.
	private static ItemStack shop = new ItemStack(Material.EMERALD); // The variable for the shop opener item.
	private static Inventory shopi = Bukkit.createInventory(null, 54, "Block Shop"); // The variable for the Block Shop.
	private static Location blue_spawn = null;
	private static Location red_spawn = null;
	private static Location lobby = null;
	private static List<String> blue_team = new ArrayList<String>();
	private static List<String> red_team = new ArrayList<String>();

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
	@Deprecated
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
	public static Inventory getBlockShop() {
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
	

	public void onEnable() {

		Timings.tnt = this;
		CommandListener.tnt = this;
		this.getCommand("tntstart").setExecutor(new CommandListener());
		this.getCommand("tntshop").setExecutor(new CommandListener());
		this.getCommand("tntrules").setExecutor(new CommandListener());
		this.getCommand("tntteam").setExecutor(new CommandListener());
		this.getCommand("tntjoin").setExecutor(new CommandListener());
		this.getCommand("tntsetlobby").setExecutor(new CommandListener());
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
		{
			ItemStack item = new ItemStack(Material.IRON_HELMET);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Protective Equipment");
			List<String> lore = Arrays.asList("§6§oProtects you from explosives");
			meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(0, item);
		}
		{
			ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Protective Equipment");
			List<String> lore = Arrays.asList("§6§oProtects you from explosives");
			meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(9, item);
		}
		{
			ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Protective Equipment");
			List<String> lore = Arrays.asList("§6§oProtects you from explosives");
			meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, true);
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(18, item);
		}
		{
			ItemStack item = new ItemStack(Material.IRON_BOOTS);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Protective Equipment");
			List<String> lore = Arrays.asList("§6§oProtects you from fall damage");
			meta.addEnchant(Enchantment.PROTECTION_FALL, 2, true);
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(27, item);
		}
		{
			ItemStack item = new ItemStack(Material.IRON_PICKAXE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Pickaxe");
			List<String> lore = Arrays.asList("§6§oBreaks stuff");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(36, item);
		}
		{
			ItemStack item = new ItemStack(Material.IRON_AXE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Axe");
			List<String> lore = Arrays.asList("§6§oChops wood");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(45, item);
		}
		{
			ItemStack item = new ItemStack(Material.TNT, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Explosives");
			List<String> lore = Arrays.asList("§6§oExplodes stuff");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(8, item);
		}
		{
			ItemStack item = new ItemStack(Material.COOKED_BEEF, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Food");
			List<String> lore = Arrays.asList("§6§oReduces hunger");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(25, item);
		}
		{
			ItemStack item = new ItemStack(Material.REDSTONE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Circuitry");
			List<String> lore = Arrays.asList("§6§oConnects and powers stuff");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(17, item);
		}
		{
			ItemStack item = new ItemStack(Material.STONE_BUTTON, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Button");
			List<String> lore = Arrays.asList("§6§oClick to power circuitry and explosives");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(26, item);
		}
		{
			ItemStack item = new ItemStack(Material.STONE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Building Blocks");
			List<String> lore = Arrays.asList("§6§oUse to build");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(35, item);
		}
		{
			ItemStack item = new ItemStack(Material.DIODE, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Diode");
			List<String> lore = Arrays.asList("§6§oDoes complicated stuff");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(44, item);
		}
		{
			ItemStack item = new ItemStack(Material.LEVER, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Switch");
			List<String> lore = Arrays.asList("§6§oSwitches the power of circuitry");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(53, item);
		}
		{
			ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Water");
			List<String> lore = Arrays.asList("§6§oA bucket of pure water from the Atlantic");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(7, item);
		}
		{
			ItemStack item = new ItemStack(Material.DISPENSER, 1);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName("§r§3Thrower");
			List<String> lore = Arrays.asList("§6§oThrows and ignites explosives");
			meta.setLore(lore);
			item.setItemMeta(meta);
			shopi.setItem(16, item);
		}

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
