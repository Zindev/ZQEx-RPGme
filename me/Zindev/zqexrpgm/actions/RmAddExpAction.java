package me.Zindev.zqexrpgm.actions;
import java.util.ArrayList;
import java.util.Arrays;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestAction;
import me.Zindev.zquest.objects.extension.QuestActionMark;
import net.flamedek.rpgme.RPGme;
import net.flamedek.rpgme.RPGmeAPI;
import net.flamedek.rpgme.skills.SkillType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@QuestActionMark(actionID ="RPGmeAddExp")
public class RmAddExpAction extends QuestAction{
	private static final long serialVersionUID = 1L;
	
	
	private Float amount;
	private String skillType;
	public RmAddExpAction() {
		skillType = SkillType.STAMINA.readableName();
		amount = 2.0f;
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&eGive some EXP to one of",
				"&ethe player's skills."
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
				",skillType:"+skillType+
				")";
	}

	@Override
	public void execute(Player p) {
		RPGmeAPI api = RPGme.getAPI();
		api.addExp(p,SkillType.getByAlias(skillType), amount);
}

	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow much exp do you",
										"&dwant to give ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amount", "&dExperience", 1, 9999999),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&4&lSkill Type", 
								new ArrayList<String>(Arrays.asList(
										"&cWhich skill do you want",
										"&cto give this EXP ?",
										"&4&lCurrently:&c<value>"
										))
								, (short)0)
						
						, null, null, "skillType", "&cSkill Type", 0, 0,
						new ArrayList<String>(SkillType.getEnabledNames())
						
					))));
		
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
}
