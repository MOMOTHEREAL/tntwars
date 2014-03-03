package me.momo.tntwars.commands;

import java.util.List;

import me.momo.tntwars.TNTWars;
import me.momo.tntwars.region.Region;
import me.momo.tntwars.region.RegionManager;
import me.momo.tntwars.region.WandLocation;
import me.momo.tntwars.util.Messages;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RegionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

		if (args.length == 0) {
			s.sendMessage("§3§lRegion Help");
			s.sendMessage("§8> §e/region create §3<name>");
			s.sendMessage("§8> §e/region delete §3<name>");
			s.sendMessage("§8> §e/region info §3<name>");
			s.sendMessage("§8> §e/region bind §3<name> §2<red:blue>");
			s.sendMessage("§8> §e/region list");
			s.sendMessage("§8> §e/region wand");
		} else {

			String c = args[0];
			if (c.equalsIgnoreCase("create")) {
				if (args.length == 2) {
					String name = args[1];

					Region rg = RegionManager.getRegion(name);
					if (rg == null) {
						
						if (RegionManager.getWandStorage().containsKey(s.getName())){
							WandLocation wand = RegionManager.getWandStorage().get(s.getName());
							
							if (wand.getP1()!=null && wand.getP2()!=null){
								
								if (wand.getP1().getWorld().getName() == wand.getP2().getWorld().getName()){
									Region prg = new Region(name, wand.getP1(), wand.getP2(), s.getName());
									RegionManager.getRegions().add(prg);
									s.sendMessage(Messages.REGION_CREATE_SUCCESS);
									
								}
								else{
									s.sendMessage(Messages.REGION_CREATE_ERR_NOTSAMEWORLD);
								}
								
								return true;
							}
							
						}
						
					}
					else{
						s.sendMessage(Messages.REGION_CREATE_ERR_EXISTS);
					}

				} else {
					s.sendMessage(Messages.REGION_CREATE_ERR_NONAME);
				}
			} else if (c.equalsIgnoreCase("list")) {
				List<Region> list = RegionManager.getRegions();

				if (list.size() > 0) {
					s.sendMessage(Messages.REGION_PREFIX + "§aDefined regions:");
					for (Region r : list) {
						
						if (r.getName().equalsIgnoreCase(TNTWars.getRedRegion().getName())){
							s.sendMessage("§8> §e" + r.getName() + " (binded with Red team)");
						}else if (r.getName().equalsIgnoreCase(TNTWars.getBlueRegion().getName())){
							s.sendMessage("§8> §e" + r.getName() + " (binded with Blue team)");
						}else{
							s.sendMessage("§8> §e" + r.getName());
						}
						

					}
				} else {
					s.sendMessage(Messages.REGION_LIST_ERR_NOREGION);
				}

			} else if (c.equalsIgnoreCase("delete")) {

				if (args.length == 2) {
					String name = args[1];

					Region rg = RegionManager.getRegion(name);
					if (rg != null) {
						
						RegionManager.getRegions().remove(rg);
						s.sendMessage(Messages.REGION_DELETE_SUCCESS);
						
					}
					else{
						s.sendMessage(Messages.REGION_DELETE_ERR_EXISTS);
					}

				} else {
					s.sendMessage(Messages.REGION_DELETE_ERR_NONAME);
				}

			}
			else if (c.equalsIgnoreCase("wand")){
				Player p = (Player)s;
				ItemStack wand = new ItemStack(Material.GOLD_AXE, 1);
				ItemMeta meta = wand.getItemMeta();
				meta.setDisplayName("§r§bRegion Wand");
				wand.setItemMeta(meta);
				p.getInventory().addItem(wand);
				s.sendMessage(Messages.REGION_WAND_SUCCESS);
			}
			else if (c.equalsIgnoreCase("bind")){
				if (args.length == 3){
					String name = args[1];
					String bind = args[2];
					if (RegionManager.getRegion(name) != null){
						Region prg = RegionManager.getRegion(name);
						if (bind.equalsIgnoreCase("red")||bind.equalsIgnoreCase("blue")){
							if (bind.equalsIgnoreCase("red")){
								TNTWars.setRedRegion(prg);
								
							}else if (bind.equalsIgnoreCase("blue")){
								TNTWars.setBlueRegion(prg);
							}
							s.sendMessage(Messages.REGION_BIND_TEAM_SUCCESS);
						}else{
							s.sendMessage(Messages.REGION_BIND_ERR_BIND);
						}
					}else{
						s.sendMessage(Messages.REGION_BIND_ERR_EXISTS);
					}
				}
				
			}
			else if (c.equalsIgnoreCase("info")){
				if (args.length == 2){
					String name = args[1];
					if (RegionManager.getRegion(name) != null){
						Region rg = RegionManager.getRegion(name);
						
						String bind = rg.getBind();
						String p1 = rg.getP1ToString();
						String p2 = rg.getP2ToString();
						String creator = rg.getCreator();
					
						
						s.sendMessage(Messages.REGION_INFO_TITLE);
						s.sendMessage("§8> §3Name: §e" + name);
						s.sendMessage("§8> §3Position 1: §e" + p1);
						s.sendMessage("§8> §3Position 2: §e" + p2);
						s.sendMessage("§8> §3Bind: §e" + bind);
						s.sendMessage("§8> §3Created by: §e" + creator);
						
						
					}else{
						s.sendMessage(Messages.REGION_INFO_ERR_EXISTS);
					}
				}else{
					s.sendMessage(Messages.REGION_INFO_ERR_NONAME);
				}
			}
			else {
				s.sendMessage(Messages.REGION_UNKNOWN_COMMAND);
			}
		}

		return true;
	}

}
