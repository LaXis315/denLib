package denoflionsx.denLib.Mod.Items;

import denoflionsx.denLib.Mod.denLibMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.Icon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemMeta extends Item {

    protected ArrayList<ItemStack> stacks = new ArrayList();
    public HashMap<Integer, Icon> icons = new HashMap();
    protected HashMap<Integer, String> names = new HashMap();
    protected ArrayList<String> textures = new ArrayList();

    public ItemMeta(int par1) {
        super(par1);
    }

    public ItemMeta(String[] textures, int par1) {
        super(par1);
        this.textures.addAll(Arrays.asList(textures));
    }

    public ItemStack createItemEntry(int meta, String name) {
        ItemStack i = new ItemStack(this, 1, meta);
        stacks.add(i);
        names.put(meta, name);
        denLibMod.log("Setting up meta item: " + String.valueOf(this.itemID) + ":" + String.valueOf(meta) + " " + name);
        return i;
    }

    public ItemStack createItemEntry(int meta, String name, String texture) {
        ItemStack i = new ItemStack(this, 1, meta);
        stacks.add(i);
        names.put(meta, name);
        textures.add(meta, texture);
        denLibMod.log("Setting up meta item with texture: " + String.valueOf(this.itemID) + ":" + String.valueOf(meta) + " " + name + " " + texture);
        return i;
    }

    public ItemStack createItemEntry(int meta, String name, NBTTagCompound tag) {
        ItemStack i = new ItemStack(this, 1, meta);
        i.stackTagCompound = tag;
        stacks.add(i);
        names.put(meta, name);
        denLibMod.log("Setting up metaNBT item: " + String.valueOf(this.itemID) + ":" + String.valueOf(meta) + " " + name);
        return i;
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.addAll(stacks);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.hasKey("info")) {
                NBTTagCompound info = par1ItemStack.stackTagCompound.getCompoundTag("info");
                for (Object o : info.getTags()) {
                    NBTTagString t = (NBTTagString) o;
                    par3List.add(t.data);
                }
            }
        }
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for (String s : textures) {
            this.icons.put(textures.indexOf(s), par1IconRegister.registerIcon(s));
        }
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        if (icons.get(par1) != null) {
            return icons.get(par1);
        } else {
            return icons.get(0);
        }
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound != null) {
            if (par1ItemStack.stackTagCompound.hasKey("override")) {
                NBTTagCompound tag = par1ItemStack.stackTagCompound.getCompoundTag("override");
                return tag.getString("name");
            }
        }
        if (!this.names.containsKey(par1ItemStack.getItemDamage())) {
            return "Name error!";
        }
        return this.names.get(par1ItemStack.getItemDamage());
    }

    @Override
    public String getItemStackDisplayName(ItemStack par1ItemStack) {
        return getItemDisplayName(par1ItemStack);
    }

    public String getBaseName() {
        return names.get(0);
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }
}
