package me.momo.tntwars.events;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.region.RegionManager;
import me.momo.tntwars.region.WandLocation;
import me.momo.tntwars.util.Messages;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractListener implements Listener{
	
	public static TNTWars tnt;
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if (p.getItemInHand() != null){
			if (p.getItemInHand().getType() == Material.EMERALD){
				p.openInventory(tnt.getBlockShop());
			}
		}
		ItemStack hand = p.getItemInHand();
		if (hand!=null&&hand.getType()==Material.GOLD_AXE){
			if (e.getAction() == Action.LEFT_CLICK_BLOCK){
				Block b = e.getClickedBlock();
				if (!RegionManager.getWandStorage().containsKey(p.getName())){
					RegionManager.getWandStorage().put(p.getName(), new WandLocation(null, null));
					
				}
				
				RegionManager.getWandStorage().get(p.getName()).setP1(b.getLocation());
				e.setCancelled(true);
				p.sendMessage(Messages.REGION_POS1);
				
			}
			else if (e.getAction()==Action.RIGHT_CLICK_BLOCK){
				Block b = e.getClickedBlock();
				if (!RegionManager.getWandStorage().containsKey(p.getName())){
					RegionManager.getWandStorage().put(p.getName(), new WandLocation(null, null));
					
				}
				
				RegionManager.getWandStorage().get(p.getName()).setP2(b.getLocation());
				e.setCancelled(true);
				p.sendMessage(Messages.REGION_POS2);
			}
		}
	}
	@EventHandler
	public void onIventoryClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().contains("Block Shop")){
			e.setCancelled(true);
			if (e.getCurrentItem() != null){
				
				if (e.getCurrentItem().getType() == Material.IRON_HELMET || e.getCurrentItem().getType() == Material.IRON_CHESTPLATE || e.getCurrentItem().getType() == Material.IRON_LEGGINGS || e.getCurrentItem().getType() == Material.IRON_BOOTS || e.getCurrentItem().getType() == Material.IRON_PICKAXE || e.getCurrentItem().getType() == Material.IRON_AXE || e.getCurrentItem().getType() == Material.WATER_BUCKET){
					ItemStack i = e.getCurrentItem();
					i.setAmount(1);
					p.getInventory().addItem(i);
				}else{
					p.getInventory().addItem(new ItemStack(e.getCurrentItem().getType(), 64));
				}
				
				
				
			}
		}
	}
}
