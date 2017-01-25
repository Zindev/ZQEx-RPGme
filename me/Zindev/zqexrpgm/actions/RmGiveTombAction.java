package me.Zindev.zqexrpgm.actions;
import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestAction;
import me.Zindev.zquest.objects.extension.QuestActionMark;
import net.flamedek.rpgme.RPGme;
import net.flamedek.rpgme.RPGmeAPI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@QuestActionMark(actionID ="RPGmeGiveTomb")
public class RmGiveTombAction extends QuestAction{
	private static final long serialVersionUID = 1L;
	
	
	private Integer amount;
	private Integer minLevel;
	private Integer giveAmount;
	public RmGiveTombAction() {
		amount = 50;
		minLevel = 0;
		giveAmount = 1;
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&eGive one or more exp",
				"&etombs to the player."
				));
	}

	@Override
	public String getWikiName() {
		return "&6"+getID();
	}
	@Override
	public Material getWikiMaterial() {
		return Material.IRON_SWORD;
	}
	@Override
	public String buildString() {
		return "(amount:"+amount+
				",giveAmount:"+giveAmount+
				",minLevel:"+minLevel+
				")";
	}

	@Override
	public void execute(Player p) {
		RPGmeAPI api = RPGme.getAPI();
		ItemStack tomb = api.createExpTomb(amount, minLevel);
		for(int i = 0;i<giveAmount;i++)Gerekli.verEsya(tomb, p, true);
	}

	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lEXP Amount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow much exp must be",
										"&din the tomb ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amount", "&dExperience", 1, 9999999),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lRequired Level", 
								new ArrayList<String>(Arrays.asList(
										"&dPlayer needs to be",
										"&datleast this level",
										"&dto use this tomb.",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "minLevel", "&dMin Level", 1, 9999999),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow many tombs do",
										"&dwant to give ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "giveAmount", "&dTomb Amount", 1, 9999999)
				)));
		
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getMinLevel() {
		return minLevel;
	}
	public void setMinLevel(Integer minLevel) {
		this.minLevel = minLevel;
	}
	public Integer getGiveAmount() {
		return giveAmount;
	}
	public void setGiveAmount(Integer giveAmount) {
		this.giveAmount = giveAmount;
	}
}
