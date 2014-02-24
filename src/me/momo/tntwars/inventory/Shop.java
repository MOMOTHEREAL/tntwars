package me.momo.tntwars.inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.momo.tntwars.TNTWars;

public class Shop {
	TNTWars tnt;
	public Shop(TNTWars tnt) {
		this.tnt = tnt;
	}
	
	public Inventory shopi = Bukkit.createInventory(null, 54, "Block Shop"); // The variable for the Block Shop.
	
	public void setupInventory() {
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
	}
	
	
}
