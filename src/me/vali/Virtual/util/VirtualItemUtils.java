package me.vali.Virtual.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;

public class VirtualItemUtils {

	public ItemStack cItem(String name, Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack cItem(String name, Material material, Integer amount) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack cItem(String name, Material material, String[] description) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<>();
		for(String d : description) {
			lore.add(d);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack cItem(String name, Material material, String[] description, Integer amount) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<>();
		for(String d : description) {
			lore.add(d);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack reMeta(ItemStack item, ItemMeta meta) {
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack reAmount(ItemStack item, Integer amount) {
		item.setAmount(amount);
		return item;
	}
	
	public ItemStack reName(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack reDiscribe(ItemStack item, String[] description) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		for(String d : description) {
			lore.add(d);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack unEnchant(ItemStack item, Integer amount) {
		return item;
	}

	public ItemStack glow(ItemStack item, Integer amount) {
		return item;
	}
	
	public ItemStack skull(String player, String displayname) {
		ItemStack itemSkull = new ItemStack(Material.SKULL_ITEM,1 , (short)3);
        SkullMeta  itemSkullMeta = (SkullMeta) itemSkull.getItemMeta();
        itemSkullMeta.setOwner(player);
        itemSkullMeta.setDisplayName(displayname);
        itemSkull.setItemMeta(itemSkullMeta);
        return itemSkull;
	}

	public ItemStack book(String title, String author, List<String> pages) {
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        NBTTagList bp = new NBTTagList();
        for(String text : pages) {
            bp.add(new NBTTagString(text));
        }
        bd.set("pages", bp);
        nmsis.setTag(bd);
        is = CraftItemStack.asBukkitCopy(nmsis);
        return is;
    }
	
}
